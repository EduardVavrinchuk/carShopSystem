package test.java.com.vavrinchuk.carShopSystem;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.web3j.abi.datatypes.Address;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import main.java.com.vavrinchuk.carShopSystem.wrappers.CarController;
import main.java.com.vavrinchuk.carShopSystem.wrappers.CarStorage;
import main.java.com.vavrinchuk.carShopSystem.wrappers.ShopProxy;

public class carShopProxyTest {
	private static final String MAIN_PRIVATE_KEY = "edf0f78ffde86e2a2618690cf2b40832bc390d2c43d52d932a7e1602f5d78d4f";
	private static final String SECOND_PRIVATE_KEY = "8139da35f3c834aa3b1dbee82b7fdda2a250ec7dfc2ed75c0650ae973c4eeec1";
	private static final String BALANCE = "1000000000000000000000";
	
	private static Process testrpc;
	private static String secondContractAddress;
	
	private Web3j web3j;
	private Credentials credentials;
	private ShopProxy proxy;
	private CarStorage carStorage;
	private CarController carController;
	
	@BeforeClass
	public static void setUpTestrpc() throws IOException {
		testrpc = new ProcessBuilder(
				"testrpc",
				"--account=0x" + MAIN_PRIVATE_KEY + "," + BALANCE,
				"--account=0x" + SECOND_PRIVATE_KEY + "," + BALANCE
			).start();
	}
	
	@AfterClass
	public static void shutDownTestrpc() {
		testrpc.destroyForcibly();
	}
	
	@Before
	public void setUp() throws Exception {
		web3j = Web3j.build(new HttpService());
		credentials = Credentials.create(MAIN_PRIVATE_KEY);
		
		carStorage = CarStorage.deploy(
				web3j, 
				credentials, 
				CarStorage.GAS_PRICE, 
				CarStorage.GAS_LIMIT
			).send();
		
		carController = CarController.deploy(
				web3j, 
				credentials, 
				CarController.GAS_PRICE, 
				CarController.GAS_LIMIT,
				new Address(carStorage.getContractAddress())
			).send();
		
		proxy = ShopProxy.deploy(
				web3j, 
				credentials, 
				ShopProxy.GAS_PRICE,
				ShopProxy.GAS_LIMIT
			).send();
		
		secondContractAddress = CarController.deploy(
				web3j, 
				credentials, 
				CarController.GAS_PRICE, 
				CarController.GAS_LIMIT, 
				new Address(carStorage.getContractAddress())
			).send().getContractAddress();
		
		proxy.updateContractAddress(new Address(carController.getContractAddress())).send();
	}
	
	@Test
	public void testChangeOwner() throws Exception {
		Address owner = proxy.owner().send();
		
		Credentials secondCredentials = Credentials.create(SECOND_PRIVATE_KEY);
		proxy.changeOwner(new Address(secondCredentials.getAddress())).send();
		
		Address newOwner = proxy.owner().send();
		
		assertThat(owner.getValue().toString()).as("Should have correct address").isEqualTo(credentials.getAddress());
		assertThat(newOwner.getValue().toString()).as("Should have correct address").isEqualTo(secondCredentials.getAddress());
	}
	
	@Test(expected=RuntimeException.class)
	public void testChangeOwnerAccessException() throws Exception {
		Credentials secondCredentials = Credentials.create(SECOND_PRIVATE_KEY);
		proxy.changeOwner(new Address(secondCredentials.getAddress())).send();
		
		proxy.changeOwner(new Address(credentials.getAddress())).send();
	}
	
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testKill() throws Exception {
		proxy.kill().send();
		
		Address contract = proxy.activeContract().send();
		assertThat(contract.getValue().toString()).isNullOrEmpty();
	}
	
	@Test(expected=RuntimeException.class)
	public void testKillAccessException() throws Exception {
		Credentials secondCredentials = Credentials.create(SECOND_PRIVATE_KEY);
		proxy.changeOwner(new Address(secondCredentials.getAddress())).send();
		
		proxy.kill().send();
	}
	
	@Test
	public void testUpdateContractAddress() throws Exception {
		Address contractAddress = proxy.activeContract().send();		
		
		proxy.updateContractAddress(new Address(secondContractAddress)).send();
		
		Address newContractAddress = proxy.activeContract().send();
		
		assertThat(contractAddress.getValue().toString()).as("Should have correct contract address")
			.isEqualTo(carController.getContractAddress());
		assertThat(newContractAddress.getValue().toString()).as("Should have correct contract address")
			.isEqualTo(newContractAddress.getValue().toString());
	}
	
	@Test(expected=RuntimeException.class)
	public void testUpdateContractAddressAccessException() throws Exception {
		Credentials secondCredentials = Credentials.create(SECOND_PRIVATE_KEY);
		proxy.changeOwner(new Address(secondCredentials.getAddress())).send();
		
		proxy.updateContractAddress(new Address(secondContractAddress)).send();
	}
	
	@Test(expected=RuntimeException.class)
	public void testUpdateContractAddressIncorrectAddressException() throws Exception {
		proxy.updateContractAddress(new Address("0x0")).send();
	}
}
