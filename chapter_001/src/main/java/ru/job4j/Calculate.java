package ru.job4j;
/**
This is test-class for first program which output Hello world to console
@author Romantsov Aleksey
*/
public class Calculate{
	/**
	method main
	@param args - array of string params
	*/
	public static void main(String[] args) {
		System.out.println("Hello world!");
	}
	
	/**
	* Method echo.
	* @param name Your name.
	* @return Echo plus your name.
	*/
	public String echo(String name) {
		return "Echo, echo, echo : " + name;
	}

}