package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import contoller.SearchServlet;
import exceptions.InvalidParameterException;

public class Demo {
	public static void main(String[] args) throws InvalidParameterException, IOException {

		String str = "Faaff"; 
		System.out.println(str);
		System.out.println(str.matches("[a-zA-Z0-9]*"));
		
		str = str.replaceAll("[^a-zA-Z0-9]", " ");
		
		System.out.println(str);
		
	}
}
