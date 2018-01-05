# carShopSystem
## What is this?
CarShopSystem it's market for buying and renting cars
## Why this project was created?

The purpose of this project is to demonstrate the possibility of upgrading the smart-contracts written using Solidity language. For this purpose are used the pattern that separates functionality of the program into three types of smart-contracts:

    Storage - contains the data
    Controller - executes bussiness logic of the program
    Proxy - contain the addresses of the actual version of controller (and maybe storage) contracts

## What tools did I use?

The following tools I did used:

    Java 9 (v9.0.1)
    Gradle (v4.3.1)
    Remix IDE
    Solc (solidity compiler, v0.4.19+commit.c4cbbb05.Linux.g++)
    Truffle (v4.0.3)
    Web3j (v3.2.0/v3.1.0)
    Testrpc (v4.1.3)

## How to test?

You should have Java, Gradle, Git and Testrpc. After installation, you need to clone this repository:

    git clone https://github.com/EduardVavrinchuk/carShopSystem.git

## And run the tests:

    gradle test
