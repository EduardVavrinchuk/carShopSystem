pragma solidity 0.4.19;

interface ICarCrates {

    enum CarStatus{
        RENTED,   // customer rent the car
        BUYED,    // customer buy the car
        RETURNED,   // customer return the car after rent
        EARLY_RETURN,   // customer return car because of some defect, etc.
        NEW_IN_SYSTEM    // the car was just added to the system
    }

    function addCar(bytes32 _key, bytes32 brand, bytes32 producer, bool isRentable, uint price, uint status) public returns (bool);

    function getCar(bytes32 _key) public view returns (bytes32[2], bool, uint[2], address);

    function getCarBrand(bytes32 _key) external view returns (bytes32);

    function getCarProducer(bytes32 _key) external view returns (bytes32);

    function getCarIsRentable(bytes32 _key) external view returns (bool);

    function getCarPrice(bytes32 _key) external view returns (uint);

    function getCarStatus(bytes32 _key) external view returns (uint);

    function setCarStatus(bytes32 _key, CarStatus status) external returns (bool);

    function getCarOwner(bytes32 _key) external view returns (address);

    function setCarOwner(bytes32 _key, address newOwner) external returns (bool);

    function getPeriodOfCarRent(bytes32 _key) external view returns (uint);

    function setPeriodOfCarRent(bytes32 _key, uint period) external returns (bool);
}
