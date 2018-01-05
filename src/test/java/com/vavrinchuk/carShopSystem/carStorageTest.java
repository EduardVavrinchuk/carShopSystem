package test.java.com.vavrinchuk.carShopSystem;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

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
import org.web3j.tuples.generated.Tuple6;

import main.java.com.vavrinchuk.carShopSystem.wrappers.CarStorage;

public class carStorageTest {
	
	private static final String MAIN_PRIVATE_KEY = "edf0f78ffde86e2a2618690cf2b40832bc390d2c43d52d932a7e1602f5d78d4f";
	private static final String SECOND_PRIVATE_KEY = "8139da35f3c834aa3b1dbee82b7fdda2a250ec7dfc2ed75c0650ae973c4eeec1";
	private static final String BALANCE = "100000000000000000000";
	private static final String CAR_BRAND_1 = "Simple brand 1";
	private static final String CAR_PRODUCER_1 = "Simple producer 1";
	private static final boolean CAR_RENTED_1 = true;
	private static final long CAR_PRICE_1 = 90000;
	private static final String CAR_BRAND_2 = "Simple brand 2";
	private static final String CAR_PRODUCER_2 = "Simple producer 2";
	private static final boolean CAR_RENTED_2 = false;
	private static final long CAR_PRICE_2 = 48000;
	private static final byte DEFAULT_CAR_STATUS = 4;
	private static final byte RENTED_CAR_STATUS = 0;
	private static final byte BUYED_CAR_STATUS = 1;
	private static final long NUMBER_OF_CARS = 2;
	private static final int PENDING_WITHDRAWALS_SUM = 200;
	private static final int DEBTORS_SUM = 300;
	
	private static Process testrpc;
	
	private Web3j web3j;
	private Credentials credentials;
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
	public static void destroyTestRpc() {
		testrpc.destroyForcibly();
	}
	
	@Before
	public void init() throws Exception {
		web3j = Web3j.build(new HttpService());
		credentials = Credentials.create(MAIN_PRIVATE_KEY);
		carStorage = CarStorage.deploy(web3j, credentials, CarStorage.GAS_PRICE, CarStorage.GAS_LIMIT).send();
		
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
	public void testDeploy() throws IOException {
		assertThat(carStorage.isValid()).as("Should be deployed and valid").isTrue();
	}
	
	@Test
	public void testChangeOwner() throws Exception {
		Address owner = carStorage.owner().send();
		
		Credentials secondCredentials = Credentials.create(SECOND_PRIVATE_KEY);
		carStorage.changeOwner(new Address(secondCredentials.getAddress())).send();
		
		Address newOwner = carStorage.owner().send();
		
		assertThat(owner.getValue().toString()).as("Should have correct owner address before changing").
			isEqualTo(credentials.getAddress());
		assertThat(newOwner.getValue().toString()).as("Should have correct newOwner address after changing").
			isEqualTo(secondCredentials.getAddress());
	}
	
	@Test(expected=RuntimeException.class)
	public void testChangeOwnerAccessException() throws Exception {
		Credentials secondCredentials = Credentials.create(SECOND_PRIVATE_KEY);
		
		carStorage.changeOwner(new Address(secondCredentials.getAddress())).send();
		carStorage.changeOwner(new Address(credentials.getAddress())).send();
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testKill() throws Exception {
		carStorage.kill().send();
		
		carStorage.cars(new Uint256(0)).send();
	}
	
	@Test(expected=RuntimeException.class)
	public void testKillAccessException() throws Exception {
		Credentials secondCredentials = Credentials.create(SECOND_PRIVATE_KEY);
		carStorage.changeOwner(new Address(secondCredentials.getAddress())).send();
		
		carStorage.kill().send();
	}
	
	@Test
	public void testAddBook() throws Exception {		
		Tuple6<Utf8String, Utf8String, Bool, Uint256, Uint8, Uint256> firstCar = carStorage.cars(new Uint256(0)).send();
		Tuple6<Utf8String, Utf8String, Bool, Uint256, Uint8, Uint256> secondCar = carStorage.cars(new Uint256(1)).send();
		
		assertThat(firstCar.getValue1().getValue().toString()).as("Should have correct car brand").isEqualTo(CAR_BRAND_1);
		assertThat(firstCar.getValue2().getValue().toString()).as("Should have correct car producer").isEqualTo(CAR_PRODUCER_1);
		assertThat(firstCar.getValue3().getValue().booleanValue()).as("Should have correct isRented field value").isTrue();
		assertThat(firstCar.getValue4().getValue().longValue()).as("Should have correct price").isEqualTo(CAR_PRICE_1);
		assertThat(firstCar.getValue5().getValue().byteValue()).as("Should have correct status").isEqualTo(DEFAULT_CAR_STATUS);
		
		assertThat(secondCar.getValue1().getValue().toString()).as("Should have correct car brand").isEqualTo(CAR_BRAND_2);
		assertThat(secondCar.getValue2().getValue().toString()).as("Should have correct car producer").isEqualTo(CAR_PRODUCER_2);
		assertThat(secondCar.getValue3().getValue().booleanValue()).as("Should have correct isRented field value").isFalse();
		assertThat(secondCar.getValue4().getValue().longValue()).as("Should have correct price").isEqualTo(CAR_PRICE_2);
		assertThat(secondCar.getValue5().getValue().byteValue()).as("Should have correct status").isEqualTo(DEFAULT_CAR_STATUS);

	}
	
	@Test(expected=RuntimeException.class)
	public void testAddCarAccessException() throws Exception {
		Credentials secondCredentials = Credentials.create(SECOND_PRIVATE_KEY);
		carStorage.changeOwner(new Address(secondCredentials.getAddress())).send();
		
		carStorage.addCar(
				new Utf8String(CAR_BRAND_1),
				new Utf8String(CAR_PRODUCER_1), 
				new Bool(CAR_RENTED_1), 
				new Uint256(CAR_PRICE_1)
			).send();
	}
	
	@Test
	public void testUpdateCarStatus() throws Exception {
		carStorage.updateCarStatus(
				new Uint256(0), 
				new Uint8(RENTED_CAR_STATUS)
			).send();
		
		carStorage.updateCarStatus(
				new Uint256(1), 
				new Uint8(BUYED_CAR_STATUS)
			).send();
		
		Uint8 firstCarStatus = carStorage.getStatus(new Uint256(0)).send();
		Uint8 secondCarStatus = carStorage.getStatus(new Uint256(1)).send();
		
		assertThat(firstCarStatus.getValue().byteValue()).as("Should have correct status").isEqualTo(RENTED_CAR_STATUS);
		assertThat(secondCarStatus.getValue().byteValue()).as("Should have correct status").isEqualTo(BUYED_CAR_STATUS);
	}
	
	@Test
	public void testGetSize() throws Exception {
		Uint256 sizeOfArray = carStorage.getSize().send();
		assertThat(sizeOfArray.getValue().longValue()).as("Should have correct number of books").isEqualTo(NUMBER_OF_CARS);
	}
	
	@Test
	public void testGetPrice() throws Exception {
		Uint256 firstCarPrice = carStorage.getPrice(new Uint256(0)).send();
		Uint256 secondCarPrice = carStorage.getPrice(new Uint256(1)).send();
		
		assertThat(firstCarPrice.getValue().longValue()).as("Should have correct price").isEqualTo(CAR_PRICE_1);
		assertThat(secondCarPrice.getValue().longValue()).as("Should have correct price").isEqualTo(CAR_PRICE_2);
	}
	
	@Test
	public void testPendingWithdrawals() throws Exception {
		carStorage.setPendingWithdrawals(
				new Address(credentials.getAddress()),
				new Uint256(PENDING_WITHDRAWALS_SUM)
			).send();
		
		Uint256 pendingWithdrawalSum = carStorage.getPendingWithdrawals(new Address(credentials.getAddress())).send();
		assertThat(pendingWithdrawalSum.getValue().longValue()).as("Should have correct withdrawal sum")
			.isEqualTo(PENDING_WITHDRAWALS_SUM);
	}
	
	@Test
	public void testDebtors() throws Exception {
		carStorage.setDebtor(
				new Address(credentials.getAddress()), 
				new Uint256(DEBTORS_SUM)
			).send();
		
		Uint256 debtorSum = carStorage.getDebtor(new Address(credentials.getAddress())).send();
		assertThat(debtorSum.getValue().longValue()).as("Should have correct debt sum").isEqualTo(DEBTORS_SUM);
	}
	
	
	
	
	
	
	
	
}
