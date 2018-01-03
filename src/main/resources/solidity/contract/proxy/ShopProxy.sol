pragma solidity 0.4.18;

import "../common/Mortal.sol";
import "./IShopProxy.sol";

/**
 * @title Using for keeping address of actual version of contract.
 *
 * @dev This contract is a significant part of working and upgradability of system.
 *  This contract necessary such as it is keeping an address of actual version of
 *  contract. Because of the fact that a lot of vulnerabilities will be detected
 *  after the system begin to work it is necessary to give  capability replace one
 *  vulnerable contract with another one with better security.
 */
contract ShopProxy is Mortal, IShopProxy {

	event LogUpdateContract(address _oldContract, address _newContract);
	
	// actual contract address
	address public activeContract;
	
	/**
	* This function updates the old contract address with new better.
	*
	* @param newContract - the address of new better contract
	*
	* @return 'true' if address of contract was updated
	*/
	function updateContractAddress(address newContract)
	    onlyByOwner
	    public
	    returns (bool)
	{
	    require(address(newContract) != 0x0);
	    LogUpdateContract(activeContract, newContract);
	    activeContract = newContract;
	    return true;
	}
	
	/**
	 * Contract cannot obtain money.
	 */
	function() public payable {
		  revert();
	}
	  
}