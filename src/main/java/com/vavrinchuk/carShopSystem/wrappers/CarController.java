package main.java.com.vavrinchuk.carShopSystem.wrappers;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
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
public class CarController extends Contract {
    private static final String BINARY = "0x6060604052341561000f57600080fd5b60405160208061325183398101604052808051906020019091905050336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060008173ffffffffffffffffffffffffffffffffffffffff161415151561009157600080fd5b80600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505061316f806100e26000396000f3006060604052600436106100af576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806312ee5057146100ff57806315bfd6ae146101215780633ccfd60b1461017257806341c0e1b51461019f5780636951173f146101b45780636c231060146101ef5780638da5cb5b1461021f5780639ca0fc0d14610274578063a6f9dae1146102ad578063ac0a8b14146102fe578063b389107414610339575b3373ffffffffffffffffffffffffffffffffffffffff167f8cd787b71532f662d581f9ea765379c7759950cd7dedb590bc8f6f7b454bd5ed346040518082815260200191505060405180910390a2005b61010761038e565b604051808215151515815260200191505060405180910390f35b341561012c57600080fd5b610158600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610834565b604051808215151515815260200191505060405180910390f35b341561017d57600080fd5b6101856109ba565b604051808215151515815260200191505060405180910390f35b34156101aa57600080fd5b6101b2610bbc565b005b34156101bf57600080fd5b6101d56004808035906020019091905050610c51565b604051808215151515815260200191505060405180910390f35b610205600480803590602001909190505061135e565b604051808215151515815260200191505060405180910390f35b341561022a57600080fd5b610232611b52565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b6102936004808035906020019091908035906020019091905050611b77565b604051808215151515815260200191505060405180910390f35b34156102b857600080fd5b6102e4600480803573ffffffffffffffffffffffffffffffffffffffff1690602001909190505061253e565b604051808215151515815260200191505060405180910390f35b341561030957600080fd5b61031f60048080359060200190919050506126c2565b604051808215151515815260200191505060405180910390f35b341561034457600080fd5b61034c613090565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b6000806000806000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663ee140236336000604051602001526040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b151561045b57600080fd5b6102c65a03f1151561046c57600080fd5b5050506040518051905011151561048257600080fd5b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663ee140236336000604051602001526040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b151561054757600080fd5b6102c65a03f1151561055857600080fd5b505050604051805190509250823410151561073c5761058083346130b690919063ffffffff16565b9150600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16630e02e5b83360006040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050600060405180830381600087803b151561064757600080fd5b6102c65a03f1151561065857600080fd5b505050600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16635f2d813f33846040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050600060405180830381600087803b151561071f57600080fd5b6102c65a03f1151561073057600080fd5b5050506001935061082e565b61074f34846130b690919063ffffffff16565b9050600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16630e02e5b833836040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050600060405180830381600087803b151561081557600080fd5b6102c65a03f1151561082657600080fd5b505050600093505b50505090565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561089157600080fd5b60008273ffffffffffffffffffffffffffffffffffffffff16141515156108b757600080fd5b7f3426ae14465e9e01154fad6ca4b6c5ef590045a58896286eb99dc7d992d088ca600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1683604051808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019250505060405180910390a181600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060019050919050565b600080600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663f340c0d0336000604051602001526040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b1515610a8257600080fd5b6102c65a03f11515610a9357600080fd5b505050604051805190509050600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16635f2d813f3360006040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050600060405180830381600087803b1515610b6457600080fd5b6102c65a03f11515610b7557600080fd5b5050503373ffffffffffffffffffffffffffffffffffffffff166108fc829081150290604051600060405180830381858888f193505050501515610bb857600080fd5b5090565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610c1757600080fd5b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16ff5b60008060008060008086610c6f6001826130cf90919063ffffffff16565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663de8fa4316000604051602001526040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1515610cfd57600080fd5b6102c65a03f11515610d0e57600080fd5b5050506040518051905010151515610d2557600080fd5b876000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16635c622a0e836000604051602001526040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b1515610dc157600080fd5b6102c65a03f11515610dd257600080fd5b5050506040518051905060ff16141515610deb57600080fd5b42600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16632c6300528b6000604051602001526040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b1515610e8557600080fd5b6102c65a03f11515610e9657600080fd5b5050506040518051905010151515610ead57600080fd5b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663514a6ec78a60036000604051602001526040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808381526020018260ff16815260200192505050602060405180830381600087803b1515610f5257600080fd5b6102c65a03f11515610f6357600080fd5b5050506040518051905050600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16632c6300528a6000604051602001526040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b151561100757600080fd5b6102c65a03f1151561101857600080fd5b50505060405180519050965061104c6201518061103e428a6130b690919063ffffffff16565b6130ed90919063ffffffff16565b9550611127866111196096600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663e75722308e6000604051602001526040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b15156110f057600080fd5b6102c65a03f1151561110157600080fd5b505050604051805190506130ed90919063ffffffff16565b61310890919063ffffffff16565b9450600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663f340c0d0336000604051602001526040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b15156111ee57600080fd5b6102c65a03f115156111ff57600080fd5b50505060405180519050935061121e85856130cf90919063ffffffff16565b9250600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16635f2d813f33856040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050600060405180830381600087803b15156112e457600080fd5b6102c65a03f115156112f557600080fd5b5050503373ffffffffffffffffffffffffffffffffffffffff167f918daaa662e70235c7c741b5b0b21054a59ffc9c56471c698bb40959a36cd07a8a42604051808381526020018281526020019250505060405180910390a26001975050505050505050919050565b600080600080600080600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663ee140236336000604051602001526040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b151561142c57600080fd5b6102c65a03f1151561143d57600080fd5b5050506040518051905014151561145357600080fd5b856114686001826130cf90919063ffffffff16565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663de8fa4316000604051602001526040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15156114f657600080fd5b6102c65a03f1151561150757600080fd5b505050604051805190501015151561151e57600080fd5b866000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16635c622a0e836000604051602001526040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b15156115ba57600080fd5b6102c65a03f115156115cb57600080fd5b5050506040518051905060ff161415801561169c575060018060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16635c622a0e836000604051602001526040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b151561167b57600080fd5b6102c65a03f1151561168c57600080fd5b5050506040518051905060ff1614155b15156116a757600080fd5b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663e7572230896000604051602001526040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b151561174057600080fd5b6102c65a03f1151561175157600080fd5b505050604051805190509550600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663f340c0d0336000604051602001526040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b151561182257600080fd5b6102c65a03f1151561183357600080fd5b50505060405180519050945061185285346130cf90919063ffffffff16565b93508584101515611a6a57600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663514a6ec78960016000604051602001526040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808381526020018260ff16815260200192505050602060405180830381600087803b151561190257600080fd5b6102c65a03f1151561191357600080fd5b505050604051805190505061193186856130b690919063ffffffff16565b9250600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16635f2d813f33856040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050600060405180830381600087803b15156119f757600080fd5b6102c65a03f11515611a0857600080fd5b5050503373ffffffffffffffffffffffffffffffffffffffff167fe078701ce67070897a2ca4b2e986ea3603e964986dd8427a86b0209df056d50d8988604051808381526020018281526020019250505060405180910390a260019650611b47565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16635f2d813f33866040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050600060405180830381600087803b1515611b2e57600080fd5b6102c65a03f11515611b3f57600080fd5b505050600096505b505050505050919050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6000806000806000806000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663ee140236336000604051602001526040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b1515611c4757600080fd5b6102c65a03f11515611c5857600080fd5b50505060405180519050141515611c6e57600080fd5b87611c836001826130cf90919063ffffffff16565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663de8fa4316000604051602001526040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1515611d1157600080fd5b6102c65a03f11515611d2257600080fd5b5050506040518051905010151515611d3957600080fd5b886000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16635c622a0e836000604051602001526040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b1515611dd557600080fd5b6102c65a03f11515611de657600080fd5b5050506040518051905060ff1614158015611eb7575060018060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16635c622a0e836000604051602001526040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b1515611e9657600080fd5b6102c65a03f11515611ea757600080fd5b5050506040518051905060ff1614155b1515611ec257600080fd5b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166384687f7a8b6000604051602001526040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b1515611f5b57600080fd5b6102c65a03f11515611f6c57600080fd5b505050604051805190501515611f8157600080fd5b611faa6001611f9c620151808c6130ed90919063ffffffff16565b6130cf90919063ffffffff16565b965060018710151515611fb957fe5b612092876120846096600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663e75722308f6000604051602001526040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b151561205b57600080fd5b6102c65a03f1151561206c57600080fd5b505050604051805190506130ed90919063ffffffff16565b61310890919063ffffffff16565b9550600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663f340c0d0336000604051602001526040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b151561215957600080fd5b6102c65a03f1151561216a57600080fd5b50505060405180519050945061218985346130cf90919063ffffffff16565b9350858410151561245457600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663514a6ec78b600080604051602001526040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808381526020018260ff16815260200192505050602060405180830381600087803b151561223857600080fd5b6102c65a03f1151561224957600080fd5b5050506040518051905050600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663a1ab9c168b8b6040518363ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018083815260200182815260200192505050600060405180830381600087803b15156122ec57600080fd5b6102c65a03f115156122fd57600080fd5b50505061231386856130b690919063ffffffff16565b9250600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16635f2d813f33856040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050600060405180830381600087803b15156123d957600080fd5b6102c65a03f115156123ea57600080fd5b5050503373ffffffffffffffffffffffffffffffffffffffff167fc113cdc1303a841866d12e77cd658bc4ca44ef68226dbc3fcc00c96bf4c3ba188b888a60405180848152602001838152602001828152602001935050505060405180910390a260019750612531565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16635f2d813f33866040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050600060405180830381600087803b151561251857600080fd5b6102c65a03f1151561252957600080fd5b505050600097505b5050505050505092915050565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561259b57600080fd5b60008273ffffffffffffffffffffffffffffffffffffffff16141515156125c157600080fd5b7f96b36bedce75759b139551b10b3d2e1e863dbbfbdc30f9f9e374bb24431d5da26000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1683604051808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019250505060405180910390a1816000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060019050919050565b6000806000806000806000876126e26001826130cf90919063ffffffff16565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663de8fa4316000604051602001526040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b151561277057600080fd5b6102c65a03f1151561278157600080fd5b505050604051805190501015151561279857600080fd5b886000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16635c622a0e836000604051602001526040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b151561283457600080fd5b6102c65a03f1151561284557600080fd5b5050506040518051905060ff1614151561285e57600080fd5b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16632c6300528b6000604051602001526040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b15156128f757600080fd5b6102c65a03f1151561290857600080fd5b50505060405180519050421015151561292057600080fd5b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663514a6ec78b60026000604051602001526040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808381526020018260ff16815260200192505050602060405180830381600087803b15156129c557600080fd5b6102c65a03f115156129d657600080fd5b5050506040518051905050600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16632c6300528b6000604051602001526040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b1515612a7a57600080fd5b6102c65a03f11515612a8b57600080fd5b505050604051805190509750612abf62015180612ab18a426130b690919063ffffffff16565b6130ed90919063ffffffff16565b9650600087111561302957612ba387612b956096600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663e75722308f6000604051602001526040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b1515612b6c57600080fd5b6102c65a03f11515612b7d57600080fd5b505050604051805190506130ed90919063ffffffff16565b61310890919063ffffffff16565b9550600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663f340c0d0336000604051602001526040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b1515612c6a57600080fd5b6102c65a03f11515612c7b57600080fd5b5050506040518051905094508585101515612d7f57600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16635f2d813f33612ce289896130b690919063ffffffff16565b6040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050600060405180830381600087803b1515612d6657600080fd5b6102c65a03f11515612d7757600080fd5b505050613028565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663f340c0d0336000604051602001526040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b1515612e4457600080fd5b6102c65a03f11515612e5557600080fd5b505050604051805190509350612e7484876130b690919063ffffffff16565b9250600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16635f2d813f3360006040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050600060405180830381600087803b1515612f3b57600080fd5b6102c65a03f11515612f4c57600080fd5b505050600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16630e02e5b833856040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050600060405180830381600087803b151561301357600080fd5b6102c65a03f1151561302457600080fd5b5050505b5b3373ffffffffffffffffffffffffffffffffffffffff167f918daaa662e70235c7c741b5b0b21054a59ffc9c56471c698bb40959a36cd07a8b42604051808381526020018281526020019250505060405180910390a2600198505050505050505050919050565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60008282111515156130c457fe5b818303905092915050565b60008082840190508381101515156130e357fe5b8091505092915050565b60008082848115156130fb57fe5b0490508091505092915050565b600080600084141561311d576000915061313c565b828402905082848281151561312e57fe5b0414151561313857fe5b8091505b50929150505600a165627a7a7230582042e11a6cd7d289c5b05f51df580ba087b9597deba89654434b13bfe9d4e178b70029";

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<>();
    }

    protected CarController(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected CarController(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<LogChangeCarStorageEventResponse> getLogChangeCarStorageEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("LogChangeCarStorage", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<LogChangeCarStorageEventResponse> responses = new ArrayList<LogChangeCarStorageEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            LogChangeCarStorageEventResponse typedResponse = new LogChangeCarStorageEventResponse();
            typedResponse.oldCarStorage = (Address) eventValues.getNonIndexedValues().get(0);
            typedResponse.newCarStorage = (Address) eventValues.getNonIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<LogChangeCarStorageEventResponse> logChangeCarStorageEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("LogChangeCarStorage", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, LogChangeCarStorageEventResponse>() {
            @Override
            public LogChangeCarStorageEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                LogChangeCarStorageEventResponse typedResponse = new LogChangeCarStorageEventResponse();
                typedResponse.oldCarStorage = (Address) eventValues.getNonIndexedValues().get(0);
                typedResponse.newCarStorage = (Address) eventValues.getNonIndexedValues().get(1);
                return typedResponse;
            }
        });
    }

    public List<LogBuyCarEventResponse> getLogBuyCarEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("LogBuyCar", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<LogBuyCarEventResponse> responses = new ArrayList<LogBuyCarEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            LogBuyCarEventResponse typedResponse = new LogBuyCarEventResponse();
            typedResponse._by = (Address) eventValues.getIndexedValues().get(0);
            typedResponse._id = (Uint256) eventValues.getNonIndexedValues().get(0);
            typedResponse._price = (Uint256) eventValues.getNonIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<LogBuyCarEventResponse> logBuyCarEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("LogBuyCar", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, LogBuyCarEventResponse>() {
            @Override
            public LogBuyCarEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                LogBuyCarEventResponse typedResponse = new LogBuyCarEventResponse();
                typedResponse._by = (Address) eventValues.getIndexedValues().get(0);
                typedResponse._id = (Uint256) eventValues.getNonIndexedValues().get(0);
                typedResponse._price = (Uint256) eventValues.getNonIndexedValues().get(1);
                return typedResponse;
            }
        });
    }

    public List<LogRentCarEventResponse> getLogRentCarEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("LogRentCar", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<LogRentCarEventResponse> responses = new ArrayList<LogRentCarEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            LogRentCarEventResponse typedResponse = new LogRentCarEventResponse();
            typedResponse._by = (Address) eventValues.getIndexedValues().get(0);
            typedResponse._id = (Uint256) eventValues.getNonIndexedValues().get(0);
            typedResponse._price = (Uint256) eventValues.getNonIndexedValues().get(1);
            typedResponse._term = (Uint256) eventValues.getNonIndexedValues().get(2);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<LogRentCarEventResponse> logRentCarEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("LogRentCar", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, LogRentCarEventResponse>() {
            @Override
            public LogRentCarEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                LogRentCarEventResponse typedResponse = new LogRentCarEventResponse();
                typedResponse._by = (Address) eventValues.getIndexedValues().get(0);
                typedResponse._id = (Uint256) eventValues.getNonIndexedValues().get(0);
                typedResponse._price = (Uint256) eventValues.getNonIndexedValues().get(1);
                typedResponse._term = (Uint256) eventValues.getNonIndexedValues().get(2);
                return typedResponse;
            }
        });
    }

    public List<LogCarReturnEventResponse> getLogCarReturnEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("LogCarReturn", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<LogCarReturnEventResponse> responses = new ArrayList<LogCarReturnEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            LogCarReturnEventResponse typedResponse = new LogCarReturnEventResponse();
            typedResponse._by = (Address) eventValues.getIndexedValues().get(0);
            typedResponse._id = (Uint256) eventValues.getNonIndexedValues().get(0);
            typedResponse._time = (Uint256) eventValues.getNonIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<LogCarReturnEventResponse> logCarReturnEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("LogCarReturn", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, LogCarReturnEventResponse>() {
            @Override
            public LogCarReturnEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                LogCarReturnEventResponse typedResponse = new LogCarReturnEventResponse();
                typedResponse._by = (Address) eventValues.getIndexedValues().get(0);
                typedResponse._id = (Uint256) eventValues.getNonIndexedValues().get(0);
                typedResponse._time = (Uint256) eventValues.getNonIndexedValues().get(1);
                return typedResponse;
            }
        });
    }

    public List<LogEtherReceivedEventResponse> getLogEtherReceivedEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("LogEtherReceived", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<LogEtherReceivedEventResponse> responses = new ArrayList<LogEtherReceivedEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            LogEtherReceivedEventResponse typedResponse = new LogEtherReceivedEventResponse();
            typedResponse._by = (Address) eventValues.getIndexedValues().get(0);
            typedResponse._value = (Uint256) eventValues.getNonIndexedValues().get(0);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<LogEtherReceivedEventResponse> logEtherReceivedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("LogEtherReceived", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, LogEtherReceivedEventResponse>() {
            @Override
            public LogEtherReceivedEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                LogEtherReceivedEventResponse typedResponse = new LogEtherReceivedEventResponse();
                typedResponse._by = (Address) eventValues.getIndexedValues().get(0);
                typedResponse._value = (Uint256) eventValues.getNonIndexedValues().get(0);
                return typedResponse;
            }
        });
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

    public RemoteCall<TransactionReceipt> payDebt(BigInteger weiValue) {
        Function function = new Function(
                "payDebt", 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<TransactionReceipt> changeCarStorage(Address _carStorage) {
        Function function = new Function(
                "changeCarStorage", 
                Arrays.<Type>asList(_carStorage), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> withdraw() {
        Function function = new Function(
                "withdraw", 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> kill() {
        Function function = new Function(
                "kill", 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> carEarlyReturn(Uint256 id) {
        Function function = new Function(
                "carEarlyReturn", 
                Arrays.<Type>asList(id), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> buyCar(Uint256 id, BigInteger weiValue) {
        Function function = new Function(
                "buyCar", 
                Arrays.<Type>asList(id), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<Address> owner() {
        Function function = new Function("owner", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> rentCar(Uint256 id, Uint256 term, BigInteger weiValue) {
        Function function = new Function(
                "rentCar", 
                Arrays.<Type>asList(id, term), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<TransactionReceipt> changeOwner(Address newOwner) {
        Function function = new Function(
                "changeOwner", 
                Arrays.<Type>asList(newOwner), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> carReturn(Uint256 id) {
        Function function = new Function(
                "carReturn", 
                Arrays.<Type>asList(id), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Address> carStorage() {
        Function function = new Function("carStorage", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public static RemoteCall<CarController> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, Address _carStorage) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(_carStorage));
        return deployRemoteCall(CarController.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static RemoteCall<CarController> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, Address _carStorage) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(_carStorage));
        return deployRemoteCall(CarController.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static CarController load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new CarController(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static CarController load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new CarController(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static class LogChangeCarStorageEventResponse {
        public Address oldCarStorage;

        public Address newCarStorage;
    }

    public static class LogBuyCarEventResponse {
        public Address _by;

        public Uint256 _id;

        public Uint256 _price;
    }

    public static class LogRentCarEventResponse {
        public Address _by;

        public Uint256 _id;

        public Uint256 _price;

        public Uint256 _term;
    }

    public static class LogCarReturnEventResponse {
        public Address _by;

        public Uint256 _id;

        public Uint256 _time;
    }

    public static class LogEtherReceivedEventResponse {
        public Address _by;

        public Uint256 _value;
    }

    public static class LogChangeOwnerEventResponse {
        public Address oldOwner;

        public Address newOwner;
    }
}
