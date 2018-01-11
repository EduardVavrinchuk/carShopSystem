pragma solidity 0.4.19;

  /**
  @title AddressValidator
  *
  * @dev This contract contains the modifier(s) that executes validation of
  *  addresses.
  */

contract Validator {

    modifier addressIsNotNull(address _address) {
        require(_address != 0x0);
        _;
    }
}
