pragma solidity 0.4.19;

 /**
 * @title Owned is a contract that specifies some operations for the owner.
 *
 * @dev There are some operations that should be executed only by the owner.
 *  Goal this contract is to define such operations.
 */

contract Owned {

    event LogChangeOwner(address indexed previousOwner, address indexed newOwner);

    // Contract owner
    address public owner;

    function Owned() public {
        owner = msg.sender;
    }

    modifier onlyByOwner {
        require(msg.sender == owner);
        _;
    }

    /**
    * @dev Allows to change the owner of the contract.
    */
    function changeOwner(address newOwner) public returns (bool) {
        require(newOwner != 0x0);
        LogChangeOwner(owner, newOwner);
        owner = newOwner;
        return true;
    }

}
