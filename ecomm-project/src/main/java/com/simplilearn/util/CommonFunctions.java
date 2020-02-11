package com.simplilearn.util;

import java.util.Random;



public class CommonFunctions {
	

	public String generateRandomEmail(String email){
		
		System.out.println("The email is: "+email);
		String[] strs=email.split("@");
		Random rand= new Random();
		email=strs[0]+"+"+rand.nextInt(1000)+"@"+strs[1];
		System.out.println("The email is: "+email);
		return email;
	}
	
	

}
