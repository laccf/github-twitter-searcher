package com.sportdec.application.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import com.sportdec.application.exception.FileNotFoundExpception;

public class FileReader {

	public static ResourceBundle readFileProperties() throws FileNotFoundExpception{
		
		ResourceBundle bundle;
		try {
			bundle = new  PropertyResourceBundle(Files.newInputStream(Paths.get("sportdec.properties")));
		} catch (IOException e) {
			throw new FileNotFoundExpception("The property file sportdec.properties wasn't found.");
		}
		return bundle;
		
	}
}
