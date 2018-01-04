pragma solidity 0.4.18;

import "../storage/CarStorage.sol";

interface ICarController {

  function buyCar(uint id) external payable returns (bool);

  function changeCarStorage(ICarStorage _carStorage) public returns (bool);
  
  function rentCar(uint id, uint term) external payable returns (bool);
  
  function carEarlyReturn(uint id) external returns (bool);

  function carReturn(uint id) external returns (bool);

  function payDebt() external payable returns (bool);

  function withdraw() external returns (bool);
  
}