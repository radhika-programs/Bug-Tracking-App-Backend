package com.bugtracking;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

public class Example {
	public static void main(String args[]) {

		Optional<String> appOpt = Optional.of("hello world");
		
		
		
		Set<String> list1= new HashSet<>();
		
		for(int i=0; i<=10; i++)
		{
			list1.add("Hello"+i);
		}
		list1.add("Hello1");
		
		
	
		
		
		list1.forEach(System.out::println);
		

		if (appOpt.isPresent()) {
			String app = appOpt.get();
			System.out.println(app);
		} else {
			System.out.println("Application not found!");
		}
	}
}