pragma solidity 0.4.19;

import "../common/Mortal.sol";
import "../common/Validator.sol";
import "../storage/StorageAdapter.sol";
import "./IWithdrawalsCrate.sol";

/**
 * @title WithdrawalCrate
 *
 * @dev This contract is a crate for the info about customer balances. This
 *  contract defines methods that allow setting and withdraws money from the
 *  system.
*/
contract WithdrawalsCrate is IWithdrawalsCrate, Mortal, StorageAdapter, Validator {

    StorageInterface.AddressUIntMapping pendingWithdrawals;

    event LogSetPendingWithdrawals(address indexed actor, uint value);

    function WithdrawalsCrate(Storage _store, bytes32 _crate)
        StorageAdapter(_store, _crate) public
    {
        pendingWithdrawals.init("pendingWithdrawals");
    }

    /**
     * @dev Making an update of the actor's balance
     *
     * @param _actor - the address of the sender
     * @param _value - the value that actor can to withdraw
     *
     * @return 'true' if operation was success
     */
    function setPendingWithdrawal(address _actor, uint _value)
        addressIsNotNull(_actor)
        external returns (bool)
    {
        store.set(pendingWithdrawals, _actor, _value);
        LogSetPendingWithdrawals(_actor, _value);
        return true;
    }

    function getPendingWithdrawals(address _actor) external view returns (uint) {
        return store.get(pendingWithdrawals, _actor);
    }

    function() public {
        revert();
    }
}
