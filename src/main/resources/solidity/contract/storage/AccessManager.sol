pragma solidity 0.4.19;

import "./IAccessManager.sol";
import "../common/Owned.sol";

contract AccessManager is Owned, IAccessManager{

    mapping (address => mapping (bytes => bool)) internal approvedContracts;

    event LogAccessGaranted(address indexed self,address actor, bytes32 role);
    event LogAccessDenied(address indexed self,address actor, bytes32 role);

    function giveAccess(address _actor, bytes32 _role) public onlyByOwner returns (bool) {
        require(!approvedContracts[_actor][_role]);
        approvedContracts[_actor][_role] = true;
        LogAccessGaranted(this, _actor, _role);
        return true;
    }

    function blokAccess(address _actor, bytes32 _role) public onlyByOwner returns (bool) {
        require(!approvedContracts[_actor][_role]);
        approvedContracts[_actor][_role] = false;
        LogAccessDenied(this, _actor, _role);
        return true;
    }

    function isAllowed(address _actor, bytes32 _role) public view returns (bool) {
        return approvedContracts[_actor][_role];
    }
}
