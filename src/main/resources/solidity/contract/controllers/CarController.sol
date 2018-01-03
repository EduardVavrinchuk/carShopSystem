pragma solidity 0.4.18;

import "../common/Mortal.sol";
import "../libraries/SafeMath.sol";
import "./ICarController.sol";

    /**
    * @title Contract that allow customers to buy and rent cars.
    *
    * @dev This contract stores data in the storage and allows customers to buy and
    *  rent cars. This is also a part of pattern that allows to upgrade the system.
    *  This contract only manipulates the data that stored in the storage so it can
    *  be replaced by a new better contract without losing data.
     */
contract CarController is Mortal, ICarController {

    using SafeMath for uint;

    event LogChangeCarStorage(address oldCarStorage, address newCarStorage);
    event LogBuyCar(address indexed _by, uint _id, uint _price);
    event LogRentCar(address indexed _by, uint _id, uint _price, uint _term);
    event LogCarReturn(address indexed _by, uint _id, uint _time);
    event LogEtherReceived(address indexed _by, uint _value);

    // address of the contract storage
	ICarStorage public carStorage;

    modifier carShouldExists(uint id) {
	    require(carStorage.getSize() >= id.add(1));
	    _;
	}
	
	modifier carShouldBeAvailable(uint id) {
	    require(carStorage.getStatus(id) != 0 && carStorage.getStatus(id) != 1);
	    _;
	}
	
	modifier carShouldBeRented(uint id) {
	    require(carStorage.getStatus(id) == 0);
	    _;
	}
	
	modifier senderShouldNotBeDebtor() {
	    require(carStorage.getDebtor(msg.sender) == 0);
	    _;
	}
	
	modifier senderShouldBeDebtor() {
	    require(carStorage.getDebtor(msg.sender) > 0);
	    _;
	}


    function CarController(ICarStorage _carStorage) public {
        require(address(_carStorage) != 0x0);
        carStorage = _carStorage;
    }

    /**
    * Change the address of the storage contract.
    *
    * @param _carStorage - address of the storage contract
    *
    * @return 'true' if operation was success
    */
    function changeCarStorage(ICarStorage _carStorage)
        onlyByOwner public returns (bool)
    {
        require(address(_carStorage) != 0x0);
        LogChangeCarStorage(carStorage, _carStorage);
        carStorage = _carStorage;
        return true;
    }

    /**
    * Allows buying car by customer
    *
    * @param id - the ID of chosen car
    *
    * @return 'true' if buying was success, 'false' otherwise
    */
    function buyCar(uint id)
        senderShouldNotBeDebtor carShouldExists(id) carShouldBeAvailable(id)
        external payable returns (bool)
        {
            uint carPrice = carStorage.getPrice(id);
            uint pendingMoney = carStorage.getPendingWithdrawals(msg.sender);
    		uint moneyOfSender = msg.value.add(pendingMoney);
    
            if(moneyOfSender >= carPrice) {
       			carStorage.updateCarStatus(id, 1);
    
                // if the buyer sent more money than necessary - put the rest into the map
      			uint amount = moneyOfSender.sub(carPrice);
       			carStorage.setPendingWithdrawals(msg.sender, amount);
 
       			LogBuyCar(msg.sender, id, carPrice);
       			return true;
            } else {
                // if money doesn't enough - put money into map, the customer can withdraw
      			// it or leave in system for the next purchases
      			carStorage.setPendingWithdrawals(msg.sender, moneyOfSender);
                return false;
        }
    }

    /**
    * Allows rent the car
    *
    * @param id - the ID of chosen car
    * @param term - the period of the rent in seconds
    *
    * @return 'true' if car was rented, 'false' otherwise
    */
    function rentCar(uint id, uint term)
        senderShouldNotBeDebtor carShouldExists(id) carShouldBeAvailable(id)
        external payable returns (bool){
     assert(termInDays >= 1);

     uint payment = (carStorage.getPrice(id).div(150)).mul(termInDays);
     uint pendingMoney = carStorage.getPendingWithdrawals(msg.sender);
     uint moneyOfSender = msg.value.add(pendingMoney);
 
     if(moneyOfSender >= payment) {
            carStorage.updateCarStatus(id, 0);
            carStorage.setLastRentDay(id, term);

            // if the client sent more money than necessary - return the rest
            uint amount = moneyOfSender.sub(payment);
            carStorage.setPendingWithdrawals(msg.sender, amount);
 
	        LogRentCar(msg.sender, id, payment, termInDays);

            return true;
        } else {
            // if money not enough - put money into the map
	        carStorage.setPendingWithdrawals(msg.sender, moneyOfSender);
            return false;
        }
    }
    
    /**
    * Return car early than last day of rent
    *
    * @param id - the ID of the car
    *
    * @return 'true' if return operation was success
    */
   function carEarlyReturn(uint id)
     carShouldExists(id) carShouldBeRented(id) external returns (bool)
   {
     require(uint(carStorage.getLastRentDay(id)) >= uint(now));
     carStorage.updateCarStatus(id, 3);
 
     // the money for days that left to the end of the rent period should be returned
     uint lastRentDay = carStorage.getLastRentDay(id);
     uint leftDays = (uint(lastRentDay).sub(uint(now))).div(86400);
 
     uint amount = (carStorage.getPrice(id).div(150)).mul(leftDays);
     uint pendingMoney = carStorage.getPendingWithdrawals(msg.sender);
     uint amountToReturn = pendingMoney.add(amount);
     carStorage.setPendingWithdrawals(msg.sender, amountToReturn);
 
     LogCarReturn(msg.sender, id, uint(now));
     return true;
   }


    /**
    * Allows to return the car
    *
    * @param id - the ID of the car
    *
    * @return 'true' if car was returned
    */
    function carReturn(uint id)
        carShouldExists(id) carShouldBeRented(id)
        external returns (bool) 
     {
     require(uint(now) >= uint(carStorage.getLastRentDay(id)));
     carStorage.updateCarStatus(id, 2);

     // if customer return car later than the last day of rent then the customer
     // must pay for these days
     uint lastRentDay = carStorage.getLastRentDay(id);
     uint penaltyDays = (uint(now).sub(uint(lastRentDay))).div(86400);
     if(penaltyDays > 0) {
         uint penaltyMoney = (carStorage.getPrice(id).div(150)).mul(penaltyDays);
         uint pendingMoney = carStorage.getPendingWithdrawals(msg.sender);

        // if money enough - just substract the necessary sum from pendingWithdrawals,
        // else - write to the debtors map.
        if(pendingMoney >= penaltyMoney) {
            carStorage.setPendingWithdrawals(msg.sender, pendingMoney.sub(penaltyMoney));
        } else {
            uint amount = carStorage.getPendingWithdrawals(msg.sender);
            uint penaltyMoneyLeft = penaltyMoney.sub(amount);
            carStorage.setPendingWithdrawals(msg.sender, 0);
            carStorage.setDebtor(msg.sender, penaltyMoneyLeft);
        }
    }    
    LogCarReturn(msg.sender, id, uint(now));
    return true;
    }
    
    /**
   	* Allows clients to pay a debt
   	*
   	* @return 'true' if debt was paid, 'false' otherwise
   	*/
  	function payDebt()
    	senderShouldBeDebtor external payable returns (bool)
    {
      	uint penaltyMoney = carStorage.getDebtor(msg.sender);

    	if(msg.value >= penaltyMoney) {
	      uint extraMoney = msg.value.sub(penaltyMoney);
	      carStorage.setDebtor(msg.sender, 0);
	      carStorage.setPendingWithdrawals(msg.sender, extraMoney);
	
	      return true;
	    } else {
	      uint amountToPay = penaltyMoney.sub(msg.value);
	      carStorage.setDebtor(msg.sender, amountToPay);
	
	      return false;
	    }
	}
    

    /**
   	* Allows customers to withdraw their money.
   	*/
  	function withdraw()
    	external returns (bool)
  	{
    	uint amount = carStorage.getPendingWithdrawals(msg.sender);
    	carStorage.setPendingWithdrawals(msg.sender, 0);
    	msg.sender.transfer(amount);
  	}

  	function() public payable {
    	LogEtherReceived(msg.sender, msg.value);
  	}
  	
}