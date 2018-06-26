/**
 * 
 */
package com.example.morsedecoder.dto;

/**
 *   Class that represents a serializable object with 
 *   the text that arrives at a service to be translated
 * @author Thiago
 *
 */
public class MorseDecoderRequest {

	/**  Text  */
	private String text;
	
	
	/** Instantiates a new morse request.  */	
	public MorseDecoderRequest() {
	}
	
	
	/**
	 * Instantiates a new morse request.
	 *
	 * @param text
	 *            the text
	 */
	public MorseDecoderRequest(String text) {
		super();
		this.text= text;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
}
