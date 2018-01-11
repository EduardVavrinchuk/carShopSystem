pragma solidity 0.4.19;

import "./Owned.sol";

/**
 * @title This contract allow owner to destroy contract.
 *
 * @ @dev Contains only one method that allows destroying of the contract but
 *  only by the owner.
 */

contract Mortal is Owned {

  /**
  * @dev Allows destroying the contract only by owner.
  */
    function kill()  public onlyByOwner {
        selfdestruct(owner);
    }

}
