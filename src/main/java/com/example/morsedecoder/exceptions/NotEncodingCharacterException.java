/**
 * 
 */
package com.example.morsedecoder.exceptions;

import com.example.morsedecoder.errors.GeneralErrors;

/**
 *  If a letter or number does not exist in the morse code binary tree
 *  NotEncodingCharacterException is thrown 
 * @author Thiago
 *
 */
public class NotEncodingCharacterException extends ApiException {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**  Instantiates a new not encoding character exception  */
	public NotEncodingCharacterException() {
		super(GeneralErrors.NOT_ENCODING_CHARACTER, null);
	}
	
}
