pragma solidity 0.4.18;

interface IShopProxy {

	function updateContractAddress(address newContract) public returns (bool);
	
}