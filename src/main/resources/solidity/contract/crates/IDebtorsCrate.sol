pragma solidity 0.4.19;

interface IDebtorsCrate {

    function setDebtor(address debtor, uint debtMoney) external returns (bool);

    function getDebtor(address debtor) external view returns (uint);
    
}
