package com.easyfx.exception;

public class NoSessionAvailableException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2497773028363312229L;

	private String message;
	
	public NoSessionAvailableException()
	{
		this.message="No Session Found";
	}
	public NoSessionAvailableException(String message)
	{
		this.message=message;
	}
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return this.message;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.message;
	}

}
