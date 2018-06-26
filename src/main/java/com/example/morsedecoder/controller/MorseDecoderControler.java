/**
 * 
 */
package com.example.morsedecoder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.morsedecoder.dto.MorseDecoderRequest;
import com.example.morsedecoder.dto.MorseDecoderResponse;
import com.example.morsedecoder.exceptions.ApiException;
import com.example.morsedecoder.service.IMorseDecoderService;



/**
 *  Class that exposes REST services to codify and decode morses codes
 * @author Thiago
 *
 */
@RestController
@RequestMapping("/translate")
public class MorseDecoderControler {


	/** Morse Decoder Service */
	@Autowired
	@Qualifier("morseCodeService")
	IMorseDecoderService morseDecoderService;

	
	/**
	 * Morse code to human text.
	 *
	 * @param text
	 *            the text
	 * @return the response entity
	 * @throws ApiException
	 *             the api exception
	 */
	@PostMapping(value = "/2text", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MorseDecoderResponse> morseDecoderToHumanText(@RequestBody MorseDecoderRequest text) throws ApiException {
		return ResponseEntity.ok(new MorseDecoderResponse(HttpStatus.OK.value(), morseDecoderService.decodeMorse2Text(text.getText())));
	}
	
	
	
	/**
	 * Human text to morse code.
	 *
	 * @param text
	 *            the text
	 * @return the response entity
	 * @throws ApiException
	 *             the api exception
	 */
	@PostMapping(value = "/2morse", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MorseDecoderResponse> humanTextToMorseCode(@RequestBody MorseDecoderRequest text) throws ApiException {
		return ResponseEntity.ok(new MorseDecoderResponse(HttpStatus.OK.value(), morseDecoderService.encodeText2Morse(text.getText())));
	}

	
}
