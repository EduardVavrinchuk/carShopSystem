package main.java.com.vavrinchuk.carShopSystem;

import main.java.com.vavrinchuk.carShopSystem.generators.WrapperGenerator;
public class Main {

	public static void main(String[] args) {
		System.out.println(greeting());
		generate();
	}
	
	public static String greeting() {
		return 
				"Car Shop";
	}
	
	public static void generate() {
		String pathToFiles = "src/main/resources/solidity/contract/build/contracts";
		String pathForOutput = "src/";
		String packageName = "main.java.com.vavrinchuk.carShopSystem.wrappers";
		
		WrapperGenerator.generateWrappers(pathToFiles, pathForOutput, packageName);
	}

}
