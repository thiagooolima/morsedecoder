/**
 * 
 */
package com.example.morsedecoder.configuration;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

/**
 * Class representing a read message from a property	
 * @author Thiago
 *
 */
@Component
public class Messages {

	
	/** Message Source*/
	@Autowired
	private MessageSource messageSource;
	
	/** Message Accesor*/
	private MessageSourceAccessor messageAccessor;

	
	/** Method init  */
	@PostConstruct
	private void init() {
		messageAccessor = new MessageSourceAccessor(messageSource, Locale.ENGLISH);
	}
	
	/**
	 * Get Method
	 * @param code
	 * @return  string
	 */
	public String get(String code) {
		return messageAccessor.getMessage(code);
	}
}
