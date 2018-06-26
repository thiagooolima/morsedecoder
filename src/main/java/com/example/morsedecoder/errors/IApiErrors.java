/**
 * 
 */
package com.example.morsedecoder.errors;


/**
 * Interface to implement to return error types and error message codes
 * @author Thiago
 *
 */
public interface IApiErrors {


	/**
	 * Gets the error type.
	 *
	 * @return the error type
	*/
	ErrorType getErrorType();

	/**
	 * Gets the code message.
	 *
	 * @return the code message
	*/
	String getCodeMessage();

}
