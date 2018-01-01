pragma solidity 0.4.19;

contract ICarStorage{
    
    function addCar(string _brand, string _producer, bool _isRentable, uint256 _price) public returns (bool);
    
    function updateCarStatus(uint id, uint8 _status) public returns (bool);
}