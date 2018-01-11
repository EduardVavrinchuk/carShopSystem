pragma solidity 0.4.18;

import "../common/Mortal.sol";
import "../common/Validator.sol";
import "../storage/StorageAdapter.sol";
import "./ICarCrates.sol";

/**
 * @title CarsCrate
 *
 * @dev This is contract that manage data related to the cars. cars srotes in
 *  separate crate.
 */
contract CarCrates is ICarCrates, Mortal, StorageAdapter, Validator {

    StorageInterface.Car Car;
    StorageInterface.Bytes32UIntMapping periodsOfcarRent;

    event LogAddCar(
        address indexed seller,
        bytes32 brand,
        bytes32 producer,
        bool isRentable,
        uint price,
        uint status
    );

    function CarCrates(Storage _store, bytes32 _crate)
        StorageAdapter(_store, _crate)
        public
    {
        car.init("cars");
        periodsOfCarRent.init("periodsOfCarRent");
    }

    /**
     * @dev Stores the car under '_key' key.
     *
     * @param _key - the key of given car
     * @param brand - the brand of the car
     * @param producer - the producer of the car
     * @param isRentable - represents whether car can be rented
     * @param price - the price of the car
     * @param status - the current car status
     *
     * @return 'true' if car was successfully stored
     */
    function addCar(bytes32 _key, bytes32 brand, bytes32 producer, bool isRentable, uint price, CarStatus status)
        public
        returns (bool)
    {
        store.set(car.brand, _key, brand);
        store.set(car.producer, _key, producer);
        store.set(car.isRentable, _key, isRentable);
        store.set(car.price, _key, price);
        store.set(car.status, _key, uint(status));
        store.set(car.owner, _key, msg.sender);
        LogAddCar(msg.sender, brand, producer, isRentable, price, uint(status));
        return true;
    }

    /**
     * @dev Returns the full info about car by given key.
     *
     * @return info about car
     */
    function getCar(bytes32 _key) public view returns (bytes32[2], bool, uint[2], address) {
        return store.get(car, _key);
    }

    function getCarBrand(bytes32 _key) external view returns (bytes32) {
        return store.get(car.brand, _key);
    }

    function getCarAuthor(bytes32 _key) external view returns (bytes32) {
        return store.get(car.author, _key);
    }

    function getCarIsRentable(bytes32 _key) external view returns (bool) {
        return store.get(car.isRentable, _key);
    }

    function getCarPrice(bytes32 _key) external view returns (uint) {
        return store.get(car.price, _key);
    }

    function getCarStatus(bytes32 _key) external view returns (uint) {
        return store.get(car.status, _key);
    }

    function setCarStatus(bytes32 _key, CarStatus status) external returns (bool) {
        store.set(car.status, _key, uint(status));
        return true;
    }

    function getCarOwner(bytes32 _key) external view returns (address) {
        return store.get(car.owner, _key);
    }

    function setCarOwner(bytes32 _key, address newOwner)
        addressIsNotNull(newOwner)
        external
        returns (bool)
    {
        store.set(car.owner, _key, newOwner);
        return true;
    }

    function getPeriodOfCarRent(bytes32 _key) external view returns (uint) {
        return store.get(periodsOfCarRent, _key);
    }

    function setPeriodOfCarRent(bytes32 _key, uint period) external returns (bool) {
        store.set(periodsOfCarRent, _key, period);
        return true;
    }


    function() public {
        revert();
    }

}
