/**
 * 
 */
package com.example.morsedecoder.service;

import java.util.List;

import com.example.morsedecoder.exceptions.ApiException;

/**
 * @author Thiago
 *
 */
public interface IMorseDecoderService {

	
	void readTreeInfo() throws ApiException;

	List<String> decodeBits2Morse() throws ApiException;
	
	String decodeMorse2Text(String str) throws ApiException;
	
	String encodeText2Morse(String str) throws ApiException;
	
	
	
}
