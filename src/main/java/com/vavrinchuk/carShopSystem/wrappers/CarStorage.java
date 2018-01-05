package main.java.com.vavrinchuk.carShopSystem.wrappers;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple6;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.2.0.
 */
public class CarStorage extends Contract {
    private static final String BINARY = "0x6060604052336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550610fae806100536000396000f3006060604052600436106100fc576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680630e02e5b8146101015780632c6300521461014357806341c0e1b51461017a578063514a6ec71461018f5780635b863e52146101d65780635c622a0e146102a25780635f2d813f146102df57806384687f7a146103215780638da5cb5b1461035c578063a1ab9c16146103b1578063a6f9dae1146103dd578063aa6be3031461042e578063de8fa4311461047b578063e7572230146104a4578063ee140236146104db578063f340c0d014610528578063f3f4370314610575578063f7746e36146105c2575b600080fd5b341561010c57600080fd5b610141600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091908035906020019091905050610732565b005b341561014e57600080fd5b610164600480803590602001909190505061077a565b6040518082815260200191505060405180910390f35b341561018557600080fd5b61018d6107a4565b005b341561019a57600080fd5b6101bc600480803590602001909190803560ff16906020019091905050610839565b604051808215151515815260200191505060405180910390f35b34156101e157600080fd5b610288600480803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509190803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919080351515906020019091908035906020019091905050610894565b604051808215151515815260200191505060405180910390f35b34156102ad57600080fd5b6102c360048080359060200190919050506109fb565b604051808260ff1660ff16815260200191505060405180910390f35b34156102ea57600080fd5b61031f600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091908035906020019091905050610a3d565b005b341561032c57600080fd5b6103426004808035906020019091905050610a85565b604051808215151515815260200191505060405180910390f35b341561036757600080fd5b61036f610abc565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34156103bc57600080fd5b6103db6004808035906020019091908035906020019091905050610ae1565b005b34156103e857600080fd5b610414600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610b0c565b604051808215151515815260200191505060405180910390f35b341561043957600080fd5b610465600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610c90565b6040518082815260200191505060405180910390f35b341561048657600080fd5b61048e610ca8565b6040518082815260200191505060405180910390f35b34156104af57600080fd5b6104c56004808035906020019091905050610cb5565b6040518082815260200191505060405180910390f35b34156104e657600080fd5b610512600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610cdf565b6040518082815260200191505060405180910390f35b341561053357600080fd5b61055f600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610d28565b6040518082815260200191505060405180910390f35b341561058057600080fd5b6105ac600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610d71565b6040518082815260200191505060405180910390f35b34156105cd57600080fd5b6105e36004808035906020019091905050610d89565b6040518080602001806020018715151515815260200186815260200185600481111561060b57fe5b60ff16815260200184815260200183810383528981815460018160011615610100020316600290048152602001915080546001816001161561010002031660029004801561069a5780601f1061066f5761010080835404028352916020019161069a565b820191906000526020600020905b81548152906001019060200180831161067d57829003601f168201915b505083810382528881815460018160011615610100020316600290048152602001915080546001816001161561010002031660029004801561071d5780601f106106f25761010080835404028352916020019161071d565b820191906000526020600020905b81548152906001019060200180831161070057829003601f168201915b50509850505050505050505060405180910390f35b80600260008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055505050565b600060038281548110151561078b57fe5b9060005260206000209060060201600501549050919050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415156107ff57600080fd5b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16ff5b60008160ff16600481111561084a57fe5b60038481548110151561085957fe5b906000526020600020906006020160040160006101000a81548160ff0219169083600481111561088557fe5b02179055506001905092915050565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415156108f157600080fd5b600380548060010182816109059190610dec565b9160005260206000209060060201600060c060405190810160405280898152602001888152602001871515815260200186815260200160048081111561094757fe5b81526020014281525090919091506000820151816000019080519060200190610971929190610e1e565b50602082015181600101908051906020019061098e929190610e1e565b5060408201518160020160006101000a81548160ff0219169083151502179055506060820151816003015560808201518160040160006101000a81548160ff021916908360048111156109dd57fe5b021790555060a0820151816005015550505060019050949350505050565b6000600382815481101515610a0c57fe5b906000526020600020906006020160040160009054906101000a900460ff166004811115610a3657fe5b9050919050565b80600160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055505050565b6000600382815481101515610a9657fe5b906000526020600020906006020160020160009054906101000a900460ff169050919050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b804201600383815481101515610af357fe5b9060005260206000209060060201600501819055505050565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610b6957600080fd5b60008273ffffffffffffffffffffffffffffffffffffffff1614151515610b8f57600080fd5b7f96b36bedce75759b139551b10b3d2e1e863dbbfbdc30f9f9e374bb24431d5da26000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1683604051808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019250505060405180910390a1816000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060019050919050565b60026020528060005260406000206000915090505481565b6000600380549050905090565b6000600382815481101515610cc657fe5b9060005260206000209060060201600301549050919050565b6000600260008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020549050919050565b6000600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020549050919050565b60016020528060005260406000206000915090505481565b600381815481101515610d9857fe5b9060005260206000209060060201600091509050806000019080600101908060020160009054906101000a900460ff16908060030154908060040160009054906101000a900460ff16908060050154905086565b815481835581811511610e1957600602816006028360005260206000209182019101610e189190610e9e565b5b505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610e5f57805160ff1916838001178555610e8d565b82800160010185558215610e8d579182015b82811115610e8c578251825591602001919060010190610e71565b5b509050610e9a9190610f15565b5090565b610f1291905b80821115610f0e5760008082016000610ebd9190610f3a565b600182016000610ecd9190610f3a565b6002820160006101000a81549060ff021916905560038201600090556004820160006101000a81549060ff0219169055600582016000905550600601610ea4565b5090565b90565b610f3791905b80821115610f33576000816000905550600101610f1b565b5090565b90565b50805460018160011615610100020316600290046000825580601f10610f605750610f7f565b601f016020900490600052602060002090810190610f7e9190610f15565b5b505600a165627a7a7230582002b512556760a653003c1a1ab7efbc4c6813f0e7699e8b4a3c943bd521a347300029";

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<>();
    }

    protected CarStorage(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected CarStorage(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<LogChangeOwnerEventResponse> getLogChangeOwnerEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("LogChangeOwner", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<LogChangeOwnerEventResponse> responses = new ArrayList<LogChangeOwnerEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            LogChangeOwnerEventResponse typedResponse = new LogChangeOwnerEventResponse();
            typedResponse.oldOwner = (Address) eventValues.getNonIndexedValues().get(0);
            typedResponse.newOwner = (Address) eventValues.getNonIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<LogChangeOwnerEventResponse> logChangeOwnerEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("LogChangeOwner", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, LogChangeOwnerEventResponse>() {
            @Override
            public LogChangeOwnerEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                LogChangeOwnerEventResponse typedResponse = new LogChangeOwnerEventResponse();
                typedResponse.oldOwner = (Address) eventValues.getNonIndexedValues().get(0);
                typedResponse.newOwner = (Address) eventValues.getNonIndexedValues().get(1);
                return typedResponse;
            }
        });
    }

    public RemoteCall<TransactionReceipt> setDebtor(Address _sender, Uint256 amount) {
        Function function = new Function(
                "setDebtor", 
                Arrays.<Type>asList(_sender, amount), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Uint256> getLastRentDay(Uint256 id) {
        Function function = new Function("getLastRentDay", 
                Arrays.<Type>asList(id), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> kill() {
        Function function = new Function(
                "kill", 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> updateCarStatus(Uint256 id, Uint8 _status) {
        Function function = new Function(
                "updateCarStatus", 
                Arrays.<Type>asList(id, _status), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> addCar(Utf8String _brand, Utf8String _producer, Bool _isRentable, Uint256 _price) {
        Function function = new Function(
                "addCar", 
                Arrays.<Type>asList(_brand, _producer, _isRentable, _price), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Uint8> getStatus(Uint256 id) {
        Function function = new Function("getStatus", 
                Arrays.<Type>asList(id), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> setPendingWithdrawals(Address _sender, Uint256 _sum) {
        Function function = new Function(
                "setPendingWithdrawals", 
                Arrays.<Type>asList(_sender, _sum), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Bool> getIsRentable(Uint256 id) {
        Function function = new Function("getIsRentable", 
                Arrays.<Type>asList(id), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Address> owner() {
        Function function = new Function("owner", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> setLastRentDay(Uint256 id, Uint256 term) {
        Function function = new Function(
                "setLastRentDay", 
                Arrays.<Type>asList(id, term), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> changeOwner(Address newOwner) {
        Function function = new Function(
                "changeOwner", 
                Arrays.<Type>asList(newOwner), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Uint256> debtors(Address param0) {
        Function function = new Function("debtors", 
                Arrays.<Type>asList(param0), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint256> getSize() {
        Function function = new Function("getSize", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint256> getPrice(Uint256 id) {
        Function function = new Function("getPrice", 
                Arrays.<Type>asList(id), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint256> getDebtor(Address _sender) {
        Function function = new Function("getDebtor", 
                Arrays.<Type>asList(_sender), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint256> getPendingWithdrawals(Address _sender) {
        Function function = new Function("getPendingWithdrawals", 
                Arrays.<Type>asList(_sender), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint256> pendingWithdrawals(Address param0) {
        Function function = new Function("pendingWithdrawals", 
                Arrays.<Type>asList(param0), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Tuple6<Utf8String, Utf8String, Bool, Uint256, Uint8, Uint256>> cars(Uint256 param0) {
        final Function function = new Function("cars", 
                Arrays.<Type>asList(param0), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Bool>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint8>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple6<Utf8String, Utf8String, Bool, Uint256, Uint8, Uint256>>(
                new Callable<Tuple6<Utf8String, Utf8String, Bool, Uint256, Uint8, Uint256>>() {
                    @Override
                    public Tuple6<Utf8String, Utf8String, Bool, Uint256, Uint8, Uint256> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);;
                        return new Tuple6<Utf8String, Utf8String, Bool, Uint256, Uint8, Uint256>(
                                (Utf8String) results.get(0), 
                                (Utf8String) results.get(1), 
                                (Bool) results.get(2), 
                                (Uint256) results.get(3), 
                                (Uint8) results.get(4), 
                                (Uint256) results.get(5));
                    }
                });
    }

    public static RemoteCall<CarStorage> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(CarStorage.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<CarStorage> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(CarStorage.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static CarStorage load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new CarStorage(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static CarStorage load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new CarStorage(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static class LogChangeOwnerEventResponse {
        public Address oldOwner;

        public Address newOwner;
    }
}
