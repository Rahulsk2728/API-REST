package com.rest;

public class MethodChaining {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
	a1().a2().a3();
		
		
	
	}

	public static MethodChaining a1()  {
	System.out.println("this is a method");
	return new MethodChaining();
	}
	
	public MethodChaining a2() {System.out.println("This is method a2");
	return this;
	}
	
	public MethodChaining a3() {System.out.println("This is method a3");
    return this;
	}
}
