pragma solidity 0.4.19;

import "../common/Mortal.sol";
import "../common/Validator.sol";
import "../storage/StorageAdapter.sol";

/**
 * @title DebtorsCrate
 *
 * @dev The contract that govers information related to the debtors.
 *  Debtors are actors that returned the book not in time, therefore they must pay
 *  penalty.
 */
contract DebtorsCrate is Mortal, StorageAdapter, Validator {

    StorageInterface.AddressUIntMapping debtors;

    event LogSetDebtor(address indexed debtor, uint debtMoney);

    function DebtorsCrate(Storage _store, bytes32 _crate)
        StorageAdapter(_store, _crate) public
    {
        debtors.init("debtors");
    }

    /**
     * @dev Stores debtor's penalty money in storage.
     *
     * @param debtor - the address of the debtor
     * @param debtMoney - the amout of penalty
     *
     * @return 'true' if operation was success
     */
    function setDebtor(address debtor, uint debtMoney)
        addressIsNotNull(debtor)
        external returns (bool)
    {
        store.set(debtors, debtor, debtMoney);
        LogSetDebtor(debtor, debtMoney);
        return true;
    }

    function getDebtor(address debtor)
        addressIsNotNull(debtor)
        external view returns (uint)
    {
        return store.get(debtors, debtor);
    }


    function() public {
        revert();
    }

}
