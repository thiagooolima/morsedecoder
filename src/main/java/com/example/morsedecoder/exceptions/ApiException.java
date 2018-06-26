/**
 * 
 */
package com.example.morsedecoder.exceptions;

import com.example.morsedecoder.errors.IApiErrors;

/**
 *  Exception thrown when an application error was thrown
 * @author Thiago
 *
 */
public class ApiException extends Exception{
	
	
	/**  Constant serialVersionUID  */
	private static final long serialVersionUID = 1104306672939965560L;
	
	
	/**  Error Type  */
	private final IApiErrors errorType;
	
	
	/**  Constant serialVersionUID  */
	private final String description;
	
	
	/**
	 * Instantiates a new api exception.
	 *
	 * @param apiError
	 *            the api error
	 * @param cause
	 *            the cause
	*/
	public ApiException(IApiErrors apiError, Throwable cause) {
		super(cause);
		this.errorType = apiError;
		this.description = apiError.getCodeMessage();
	}

	/**
	 * Gets the error type.
	 *
	 * @return the error type
	*/
	public IApiErrors getErrorType() {
		return errorType;
	}
	
	
	
	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	

}
