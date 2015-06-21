package com.easyfx.exception;

public class UnAuthorizedAccessException extends Exception {
	/**
	 * 
	 */
	//private static final long serialVersionUID = -6143188551684616184L;
	private String message;
	public UnAuthorizedAccessException()
	{
		this.message="UnAuthorized Access Attempt";
	}
	public UnAuthorizedAccessException(String message)
	{
		this.message=message;
	}
	public String toString()
	{
		return this.message;
	}
}
