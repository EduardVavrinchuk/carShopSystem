pragma solidity 0.4.19;

contract ICarController {

  function buyCar(uint id) external returns (bool);

  function rentCar(uint id, uint term) external returns (bool);

  function returnCar(uint id) external returns (bool);
}