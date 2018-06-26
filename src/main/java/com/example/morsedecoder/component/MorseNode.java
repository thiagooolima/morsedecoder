
package com.example.morsedecoder.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.morsedecoder.configuration.Messages;


/**
 * MorseNode Class
 * @author Thiago
 *
 */
@Component("morseNode")
public class MorseNode {
	
	/** Constante Logger  */
	private static final Logger LOGGER = LoggerFactory.getLogger(MorseNode.class);
	
	/** Messages  */
	@Autowired
	Messages messages;
	
	/** Logger  */
	private char letter;
	
	/** Code  */
	private String code;
	
	/** Left  */
	private MorseNode left;
	
	/** Right */
	private MorseNode right;
	
	/** Constante EMPTY  */
	public static final char EMPTY = ' ';
	

	/**  Init a new morse node */
	public MorseNode() {
		code="";
		letter = EMPTY;
		left= null;
		right=null;
	}



	/**
	 * @return the letter
	 */
	public char getLetter() {
		return letter;
	}



	/**
	 * @param letter the letter to set
	 */
	public void setLetter(char letter) {
		if (this.letter == EMPTY) {
			this.letter = letter;
		} else if (this.letter != letter) {
			LOGGER.error(messages.get("morse.node.exists"));
		}
	}



	/**Get Code
	 * @return the code
	 */
	public String getCode() {
		return code;
	}



	/**Set Code
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}



	/**Get Left
	 * @return the left
	 */
	public MorseNode getLeft() {
		return left;
	}



	/**Set left
	 * @param left the left to set
	 */
	public void setLeft(MorseNode left) {
		this.left = left;
	}

	/**Get Right
	 * @return the right
	 */
	public MorseNode getRight() {
		return right;
	}

	/**Set Right
	 * @param right the right to set
	 */
	public void setRight(MorseNode right) {
		this.right = right;
	}
}
