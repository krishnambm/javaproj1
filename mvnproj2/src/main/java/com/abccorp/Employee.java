package com.abccorp;

/**
 * Hello world!
 *
 */
public class Employee
{
	String firstName;
	String lastName;

	public Employee(String firstName, String lastName) {
		firstName = firstName;
		lastName = lastName;
	}

	@Override
	public String toString() {
		return "Employee: " + firstName + " " + lastName;
	}
}
