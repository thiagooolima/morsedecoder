/**
 * 
 */
package com.example.morsedecoder.exceptions;

import com.example.morsedecoder.errors.GeneralErrors;

/**
 *  If a morse key character does not exist in the morse code binary tree 
 *  MorseCharacterException is thrown.
 * @author Thiago
 *
 */
public class MorseCharacterException extends ApiException {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Instantiates a new Morse character exception  */
	public MorseCharacterException() {
		super(GeneralErrors.BAD_MORSE_CHARACTER, null);
	}

	
}
