/**
 * 
 */
package com.example.morsedecoder.dto;

/**
 *   Class that represents a serializable object
 *  with the translated text and the service status code
 * @author Thiago
 *
 */
public class MorseDecoderResponse {
	
	
	/** Code */
	private int code;
	
	
	/** Text  */
	private String text;
	
	/**  Instantiates a new morse response.  */
	public MorseDecoderResponse() {
	}

		
	/**
	 * Instantiates a new morse response.
	 *
	 * @param code
	 *            the code
	 * @param text
	 *            the text
	 */
	public MorseDecoderResponse(int code, String text) {
		super();
		this.code= code;
		this.text=text;
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
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
