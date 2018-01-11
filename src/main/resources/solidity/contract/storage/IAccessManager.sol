pragma solidity 0.4.19;

interface IAccessManager{

    function isAllowed(address _actor, bytes32 _role) public constant returns(bool);
}
