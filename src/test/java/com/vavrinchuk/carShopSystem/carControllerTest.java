package test.java.com.vavrinchuk.carShopSystem;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.math.BigInteger;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import main.java.com.vavrinchuk.carShopSystem.wrappers.CarController;
import main.java.com.vavrinchuk.carShopSystem.wrappers.CarStorage;

public class carControllerTest {
	private static final String MAIN_PRIVATE_KEY = "edf0f78ffde86e2a2618690cf2b40832bc390d2c43d52d932a7e1602f5d78d4f";
	private static final String SECOND_PRIVATE_KEY = "8139da35f3c834aa3b1dbee82b7fdda2a250ec7dfc2ed75c0650ae973c4eeec1";
	private static final String BALANCE = "100000000000000000000";
	private static final long CAR_INDEX_1 = 0;
	private static final String CAR_BRAND_1 = "Simple brand 1";
	private static final String CAR_PRODUCER_1 = "Simple producer 1";
	private static final boolean CAR_RENTED_1 = true;
	private static final long CAR_PRICE_1 = 90000;
	private static final long CAR_INDEX_2 = 1;
	private static final String CAR_BRAND_2 = "Simple brand 2";
	private static final String CAR_PRODUCER_2 = "Simple producer 2";
	private static final boolean CAR_RENTED_2 = false;
	private static final long CAR_PRICE_2 = 48000;
	private static final byte RENTED_CAR_STATUS = 0;
	private static final byte BUYED_CAR_STATUS = 1;
	private static final byte RETURNED_CAR_STATUS = 2;
	private static final byte EARLY_RETURN_CAR_STATUS = 3;
	private static final long RENT_PERIOD = 1209600;
	private static final long LOW_RENT_PERIOD = 864000;
	private static final long RENT_AMOUNT = 1000;
	private static final long DEBT_AMOUNT = 5;
	private static final long LEFT_AMOUNT = 0;
	private static final long NON_EXISTING_CAR_INDEX = 2;
	
	private static Process testrpc;
	
	private Web3j web3j;
	private Credentials credentials;
	private CarController carController;
	private CarStorage carStorage;
	
	@BeforeClass
	public static void initTestRpc() throws IOException {
		testrpc = new ProcessBuilder(
				"testrpc",
				"--account=0x"+MAIN_PRIVATE_KEY+","+BALANCE,
				"--account=0x"+SECOND_PRIVATE_KEY+","+BALANCE
				).start();
	}
	
	@AfterClass
	public static void shutDownTestrpc() {
		testrpc.destroyForcibly();
	}
	
	@Before
	public void init() throws Exception {
		web3j = Web3j.build(new HttpService());
		credentials = Credentials.create(MAIN_PRIVATE_KEY);
		carStorage = CarStorage.deploy(web3j, credentials, CarStorage.GAS_PRICE, CarStorage.GAS_LIMIT).send();
		
		carController = CarController.deploy(web3j, credentials, CarController.GAS_PRICE, 
				CarController.GAS_LIMIT, new Address(carStorage.getContractAddress())).send();
		
		carStorage.addCar(new Utf8String(CAR_BRAND_1), 
				new Utf8String(CAR_PRODUCER_1), 
				new Bool(CAR_RENTED_1),
				new Uint256(CAR_PRICE_1)).send();
		
		carStorage.addCar(new Utf8String(CAR_BRAND_2), 
				new Utf8String(CAR_PRODUCER_2), 
				new Bool(CAR_RENTED_2),
				new Uint256(CAR_PRICE_2)).send();
	}
	
	@Test
	public void testChangeOwner() throws Exception {
		Address owner = carController.owner().send();
		
		Credentials secondCredentials = Credentials.create(SECOND_PRIVATE_KEY);
		carController.changeOwner(new Address(secondCredentials.getAddress())).send();
		
		Address newOwner = carController.owner().send();
		
		assertThat(owner.getValue().toString()).as("Should have correct owner address before changing")
			.isEqualTo(credentials.getAddress());
		assertThat(newOwner.getValue().toString()).as("Should have correct owner address after changing")
			.isEqualTo(secondCredentials.getAddress());
	}
	
	@Test(expected=RuntimeException.class)
	public void testChangeOwnerException() throws Exception {
		Credentials secondCredentials = Credentials.create(SECOND_PRIVATE_KEY);
		carController.changeOwner(new Address(secondCredentials.getAddress())).send();

		carController.changeOwner(new Address(credentials.getAddress())).send();
	}
	
	@Test(expected=NullPointerException.class)
	public void testKill() throws Exception {
		carController.kill().send();
		
		Address carStorageAddress = carController.carStorage().send();
		assertThat(carStorageAddress.getValue().toString()).isNull();
	}
	
	@Test(expected=RuntimeException.class)
	public void testKillAccessException() throws Exception {
		Credentials secondCredentials = Credentials.create(SECOND_PRIVATE_KEY);
		carController.changeOwner(new Address(secondCredentials.getAddress())).send();

		carController.kill().send();
	}
	
	@Test
	public void testChangeCarStorage() throws Exception {
		Address carStorageAddress = carController.carStorage().send();
		
		CarStorage secondCarStorage = CarStorage.deploy(
				web3j, 
				credentials, 
				CarStorage.GAS_PRICE, 
				CarStorage.GAS_LIMIT
			).send();		
		
		carController.changeCarStorage(new Address(secondCarStorage.getContractAddress())).send();
		Address newCarStorageAddress = carController.carStorage().send();
		
		assertThat(carStorageAddress.getValue().toString()).as("Should have correct address before changing")
			.isEqualTo(carStorage.getContractAddress());
		assertThat(newCarStorageAddress.getValue().toString()).as("Should have correct address after changing")
			.isEqualTo(secondCarStorage.getContractAddress());
	}
	
	@Test(expected=RuntimeException.class)
	public void testChangeCarStorageAccessException() throws Exception {
		Credentials secondCredentials = Credentials.create(SECOND_PRIVATE_KEY);
		carController.changeOwner(new Address(secondCredentials.getAddress())).send();
		
		CarStorage secondCarStorage = CarStorage.deploy(
				web3j, 
				credentials, 
				CarStorage.GAS_PRICE, 
				CarStorage.GAS_LIMIT
			).send();

		carController.changeCarStorage(new Address(secondCarStorage.getContractAddress())).send();
	}
	
	@Test(expected=RuntimeException.class)
	public void testChangeCarStorageIncorrectAddressException() throws Exception {
		carController.changeCarStorage(new Address("0x0")).send();
	}
	
	@Test
	public void testBuyCar() throws Exception {
		carController.buyCar(
				new Uint256(CAR_INDEX_1), 
				BigInteger.valueOf(CAR_PRICE_1)
			).send();
		
		Uint8 carStatus = carStorage.getStatus(new Uint256(0)).send();
		assertThat(carStatus.getValue().byteValue()).as("Should have status 'BUYED'").isEqualTo(BUYED_CAR_STATUS);
	}
	
	@Test(expected=RuntimeException.class)
	public void testBuyCarSenderIsDebtorException() throws Exception {
		carStorage.setDebtor(
				new Address(credentials.getAddress()), 
				new Uint256(DEBT_AMOUNT)
			).send();

		carController.buyCar(
				new Uint256(CAR_INDEX_1), 
				BigInteger.valueOf(CAR_PRICE_1)
			).send();
	}
	
	@Test(expected=RuntimeException.class)
	public void testBuyCarIsNotExistsException() throws Exception {
		carController.buyCar(
				new Uint256(NON_EXISTING_CAR_INDEX), 
				BigInteger.valueOf(CAR_PRICE_1)
			).send();
	}
	
	@Test(expected=RuntimeException.class)
	public void testBuyCarIsNotAvailabelException() throws Exception {
		carStorage.updateCarStatus(
				new Uint256(CAR_INDEX_1), 
				new Uint8(RENTED_CAR_STATUS)
			).send();

		carController.buyCar(
				new Uint256(CAR_INDEX_1), 
				BigInteger.valueOf(CAR_PRICE_1)
			).send();
	}
	
	@Test
	public void testRentCar() throws Exception {
		carController.rentCar(
				new Uint256(CAR_INDEX_1), 
				new Uint256(RENT_PERIOD),
				BigInteger.valueOf(10000)
			).send();
		
		Uint8 carStatus = carStorage.getStatus(new Uint256(0)).send();
		assertThat(carStatus.getValue().byteValue()).as("Should have status 'RENTED'").isEqualTo(RENTED_CAR_STATUS);
	}
	
	@Test(expected=RuntimeException.class)
	public void testRentCarSenderIsDebtorException() throws Exception {
		carStorage.setDebtor(
				new Address(credentials.getAddress()), 
				new Uint256(DEBT_AMOUNT)
			).send();

		carController.rentCar(
				new Uint256(CAR_INDEX_1), 
				new Uint256(RENT_PERIOD), 
				BigInteger.valueOf(RENT_AMOUNT)
			).send();
	}
	
	@Test(expected=RuntimeException.class)
	public void testRentCarIsNotExistsException() throws Exception {
		carController.rentCar(
				new Uint256(NON_EXISTING_CAR_INDEX), 
				new Uint256(RENT_PERIOD), 
				BigInteger.valueOf(RENT_AMOUNT)
			).send();
	}
	
	@Test(expected=RuntimeException.class)
	public void testRentCarIsNotAvailabelException() throws Exception {
		carStorage.updateCarStatus(
				new Uint256(CAR_INDEX_1), 
				new Uint8(BUYED_CAR_STATUS)
			).send();

		carController.rentCar(
				new Uint256(CAR_INDEX_1), 
				new Uint256(RENT_PERIOD), 
				BigInteger.valueOf(RENT_AMOUNT)
			).send();
	}
	
	@Test(expected=RuntimeException.class)
	public void testRentCarCannotBeRentedException() throws Exception {
		carController.rentCar(
				new Uint256(CAR_INDEX_2), 
				new Uint256(RENT_PERIOD), 
				BigInteger.valueOf(RENT_AMOUNT)
			).send();
	}
	
	@Test(expected=RuntimeException.class)
	public void testRentCarLowRentPeriodException() throws Exception {
		carController.rentCar(
				new Uint256(CAR_INDEX_1), 
				new Uint256(LOW_RENT_PERIOD), 
				BigInteger.valueOf(RENT_AMOUNT)
			).send();
	}
	
	@Test
	public void testCarEarlyReturn() throws Exception {
		carController.rentCar(
				new Uint256(CAR_INDEX_1), 
				new Uint256(RENT_PERIOD),
				BigInteger.valueOf(10000)
			).send();
		
		carController.carEarlyReturn(new Uint256(0)).send();
		
		Uint8 carStatus = carStorage.getStatus(new Uint256(0)).send();
		assertThat(carStatus.getValue().byteValue()).as("Should have status 'EARLY_RETURN'").isEqualTo(EARLY_RETURN_CAR_STATUS);
	}
	
	@Test(expected=RuntimeException.class)
	public void testCarEarlyReturnCarIsNotExistsException() throws Exception {
		carController.carEarlyReturn(new Uint256(NON_EXISTING_CAR_INDEX)).send();
	}
	
	@Test(expected=RuntimeException.class)
	public void testCarEarlyReturnCarIsNotRentedException() throws Exception {
		carController.carEarlyReturn(new Uint256(CAR_INDEX_1)).send();
	}
	
	@Test
	public void testCarReturn() throws Exception {
		carStorage.updateCarStatus(
				new Uint256(CAR_INDEX_1), 
				new Uint8(RENTED_CAR_STATUS)
			).send();
		
		carStorage.setLastRentDay(
				new Uint256(CAR_INDEX_1), 
				new Uint256(0)
			).send();
		
		carController.carReturn(new Uint256(0)).send();
		
		Uint8 carStatus = carStorage.getStatus(new Uint256(0)).send();
		assertThat(carStatus.getValue().byteValue()).as("Should have status 'RETURNED'").isEqualTo(RETURNED_CAR_STATUS);
	}
	
	@Test(expected=RuntimeException.class)
	public void testCarReturnIsNotExistsException() throws Exception {
		carController.carReturn(new Uint256(NON_EXISTING_CAR_INDEX)).send();
	}
	
	@Test(expected=RuntimeException.class)
	public void testCarReturnIsNotRentedException() throws Exception {
		carController.carReturn(new Uint256(CAR_INDEX_1)).send();
	}
	
	@Test
	public void testPayDebt() throws Exception {
		carStorage.setDebtor(
				new Address(credentials.getAddress()), 
				new Uint256(DEBT_AMOUNT)
			).send();
		
		Uint256 debtAmount = carStorage.getDebtor(new Address(credentials.getAddress())).send();
		carController.payDebt(BigInteger.valueOf(DEBT_AMOUNT)).send();
		
		Uint256 leftDebtAmount = carStorage.getDebtor(new Address(credentials.getAddress())).send();
		
		assertThat(debtAmount.getValue().longValue()).as("Should have a debt before transaction").isEqualTo(DEBT_AMOUNT);
		assertThat(leftDebtAmount.getValue().longValue()).as("Should have zero sum of debt").isEqualTo(LEFT_AMOUNT);
	}
	
	@Test(expected=RuntimeException.class)
	public void testPayDebtSenderIsNotDebtorException() throws Exception {
		carController.payDebt(BigInteger.valueOf(DEBT_AMOUNT)).send();
	}
}
