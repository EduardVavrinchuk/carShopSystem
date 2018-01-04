package main.java.com.vavrinchuk.carShopSystem.generators;

import java.io.File;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.web3j.codegen.TruffleJsonFunctionWrapperGenerator;

public class WrapperGenerator {
	
	private static final Logger logger = Logger.getLogger(WrapperGenerator.class.getName());
	
	private static String pathToFiles;
	private static String pathForOutput;
	private static String packageName;
	
	
	public static void generateWrappers(String pathToFiles, String pathForOutput, String packageName) {
		WrapperGenerator.pathToFiles = pathToFiles;
		WrapperGenerator.pathForOutput = pathForOutput;
		WrapperGenerator.packageName = packageName;
		
		File directory = new File(pathToFiles);
		String[] solidityFiles = directory.list((file, name) -> {
			return name.endsWith(".json");
		});
		
		Arrays.stream(solidityFiles).forEach(WrapperGenerator::generate);
		
		logger.info("Wrappers generated");
	}
	
	public static void generate(String filename) {
		String[] params = new String[] {
			"--solidityTypes",
			pathToFiles + "/" + filename,
			"-o",
			pathForOutput,
			"-p",
			packageName
		};
		
		try {
			TruffleJsonFunctionWrapperGenerator.main(params);
		} catch(Exception e) {
			logger.log(Level.SEVERE, "Exception during wrapper generetors\n" + e.getMessage() + "\n" + e.getStackTrace());
		}
	}
}
