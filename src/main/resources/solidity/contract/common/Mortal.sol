pragma solidity 0.4.18;

import "./Owned.sol";

/**
 * @title This contract allow owner to destroy contract.
 *
 * @dev This contract managing access to contract destroying. This means that
 * only owner capable destroy the contract where used this pattern.
 */
contract Mortal is Owned {

  /**
   * Destroys the contract and send all ether from contract to the owner balance.
   */
  function kill() onlyByOwner public {
    selfdestruct(owner);
  }

}