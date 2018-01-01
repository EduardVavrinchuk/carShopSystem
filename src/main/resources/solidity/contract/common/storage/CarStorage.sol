pragma solidity 0.4.19;

import "./ICarStorage.sol";
import "../common/Mortal.sol";

contract CarStorage is Mortal, ICarSolidity{
    
    enum CarStatus {
        RENTED,   // customer rent the car
        BUYED,    // customer buy the car
        RETURNED,   // customer return the car after rent
        EARLY_RETURN, // customer return car because of some defect, etc.
        NEW_IN_SYSTEM    // the car was just added to the system
    }
    
    struct Car{
        string brand;
        string producer;
        bool isRentable;
        uint price;
        CarStatus status;
        uint lastRentDay; // used only for rent period
    }
    
    Car[] public cars;
    
    /**
    * This function add new car to the array.
    *
    * @param _brand - the title of the car
    * @param _producer - the author of the car
    * @param _isRentable - the car can be test drive if 'true', else only for selling
    * @param _price - the price of the cars
    *
    * @return 'true' if car added
    */
    function addCar(string _brand, string _producer, bool _isRentable, uint256 _price)
        onlyByOwner public returns (bool)
    {
        cars.push(Car({
            brand: _brand,
            producer: _producer,
            isRentable: _isRentable,
            price: _price,
            status: CarStatus.NEW_IN_SYSTEM,
            lastRentDay: uint(now)
        }));
        return true;
    }
    
    /**
    * Update car status.
    *
    * @param id - ID of car
    * @param _status - new status of car
    *
    * @return 'true' if car was updated
    */
    function updateCarStatus(uint id, uint8 _status)
        onlyByOwner public returns (bool)
    {
        cars[id].status = CarStatus(_status);
        return true;
    }
    
    /**
    * Return the size of array
    */
    function getSize() public view returns (uint) {
        return cars.length;
    }

    function getStatus(uint id) public view returns (uint8) {
        return uint8(cars[id].status);
    }

    function getPrice(uint id) public view returns (uint) {
        return cars[id].price;
    }

    function getIsRentable(uint id) public view returns (bool) {
        return cars[id].isRentable;
    }

    /**
    * Set the last day of car rent, used only for rent
    */
    function setLastRentDay(uint id, uint term) public returns (bool) {
        cars[id].lastRentDay = uint(now) + term;
    }

    /**
    * Contract cannot store money or execute another functions
    * (At least throught fallback function).
    */
    function() public {
        revert();
    }
    
}