/**
 * 
 */
package com.example.morsedecoder.service.impl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.morsedecoder.component.MorseNode;
import com.example.morsedecoder.configuration.Messages;
import com.example.morsedecoder.domain.Range;
import com.example.morsedecoder.exceptions.ApiException;
import com.example.morsedecoder.exceptions.MorseCharacterException;
import com.example.morsedecoder.exceptions.NotEncodingCharacterException;
import com.example.morsedecoder.service.IMorseDecoderService;

/**
 *
 *  Class that implements  IMorseDecoderService
 * @author Thiago
 *
 */
@Service("morseCodeService")
public class MorseDecoderServiceImpl implements IMorseDecoderService {


	/**  Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(IMorseDecoderService.class);
	
	
	/** Root  */
	@Autowired
	@Qualifier("morseNode")
	private MorseNode root;
	
	/**  Exists Pulse Alternative.  */
	private boolean existsPulseAlternative;
	
	/**  Exists Pause Alternative.   */	
	private boolean existsPauseAlternative;
	
	/**  Dot Range.  */
	private Range dotRange = new Range();
	
	/**    Dash Range */
	private Range dashRange = new Range();

	/**  	Dot Range  Alterntive */
	private Range dotRangeAlternative = new Range();
	
	/**   Dash Range Alternative */
	private Range dashRangeAlternative = new Range();
	
	
	/**    Letter Separator Range  */
	private Range letterSeparatorRange = new Range();
	
	
	/**  Word Separator Range  */
	private Range wordSeparatorRange = new Range();
	
	/**  Letter Separator Range Alternative  */
	private Range letterSeparatorRangeAlternative = new Range();
	
	/** Word Separator Range Alternative  */
	private Range wordSeparatorRangeAlternative = new Range();
	
	/**  Dot*/
	private static final String DOT = ".";
	
	
	/** Dash */
	private static final String DASH = "-";
	
	/** Letter Separator */
	private static final String LETTER_SEPARATOR = "";
	
	/** Word Separator   */
	private static final String WORD_SEPARATOR = " ";
	
	/**  Messages  */
	@Autowired
	Messages messages;
	
	
	
	
	
	
	
	/**
	 *  Read a file inside the resources folder where the
  	 * references for the morses codes with their equivalences in letters and
  	 * numbers.
	 * 
	 * @throws ApiException
	 *             the api exception
	 */
	public void readTreeInfo() throws ApiException {
		try (Stream<String> stream = Files
				.lines(Paths.get(getClass().getClassLoader().getResource("encodings.txt").toURI()))) {
			stream.forEach(input -> {
				try {
					add(input.substring(1).trim(), input.charAt(0));
				} catch (ApiException e) {
					LOGGER.error(e.getMessage());
				}
			});
		} catch (IOException | URISyntaxException e) {
			LOGGER.error(e.getMessage());
		}
	}

	
	
	
	/**
	 *  Add a morse code with your letter correspondence inside a tree 
	 *  morse code binary
	 * 
	 * 
	 * @param mcode
	 * @param letter
	 * @throws ApiException
	 */
	private void add(String mcode, char letter) throws ApiException {
		MorseNode current = root;
		for (int i = 0; i < mcode.length(); i++) {
			current = nextNode(current, mcode.charAt(i));
		}
		current.setLetter(letter);
		current.setCode(mcode);
	}
	/**
	 * Returns the next node in a morse code binary tree looking for
	 * from a dot or dash
	 * 
	 * 
	 * @param current
	 * @param dotOrDash
	 * @return
	 * @throws ApiException
	 */
	private MorseNode nextNode(MorseNode current, char dotOrDash) throws ApiException {
		switch (dotOrDash) {
		case '.':
			if (current.getLeft() == null)
				current.setLeft(new MorseNode());
			return current.getLeft();
		case '-':
			if (current.getRight() == null)
				current.setRight(new MorseNode());
			return current.getRight();
		default:
			throw new MorseCharacterException();
		}
	}

	
	/**
	 *   It receives as a parameter a morse code to be decoded.
	 * 
	 * @param str
	 *            the str
	 * @return the string
	 * @throws ApiException
	 *             the api exception
	 *  	
	 */
	public String decodeMorse2Text(String str) throws ApiException {
		String signal = "";
		StringBuffer result = new StringBuffer("");
		MorseNode current = root;
		for (int i = 0; i < str.length(); i++) {
			signal = str.substring(i, i + 1);
			if (signal.equals(".")) {
				if (current.getLeft() != null) {
					current = current.getLeft();
				} else {
					current.setLeft(new MorseNode());
					current = current.getLeft();
				}
			} else if (signal.equals("-")) {
				if (current.getRight() != null) {
					current = current.getRight();
				} else {
					current.setRight(new MorseNode());
					current = current.getRight();
				}
			} else {
				result = result.append(current.getLetter());
				current = root;
			}
		}
		result = result.append(current.getLetter());
		return result.toString();
	}

	/**
	 * 
	 * It encodes a human text in Morse code
	 * 
	 * @param str
	 *            the str
	 * @return the string
	 * @throws ApiException
	 *             the api exception
	 */
	public String encodeText2Morse(String str) throws ApiException {
		MorseNode current = root;
		String result = "";
		char ltr;

		for (int i = 0; i < str.length(); i++) {
			ltr = str.charAt(i);
			MorseNode node = search(Character.toLowerCase(ltr), current);
			if (node == null) {
				throw new NotEncodingCharacterException();
			}
			result += node.getCode();
			if (i + 1 < str.length()) {
				result += " ";
			}
		}
		return result;
	}
	
	
	
	/**
	 * 
	 * Search from a letter a node in the morse code binary tree
	 * 
	 * @param ltr
	 * @param node
	 * @return
	 */
	private MorseNode search(char ltr, MorseNode node) {
		if (node != null) {
			if (node.getLetter() == ltr) {
				return node;
			} else {
				MorseNode foundNode = search(ltr, node.getLeft());
				if (foundNode == null) {
					foundNode = search(ltr, node.getRight());
				}
				return foundNode;
			}
		}
		return node;
	}

	/**
	 * Decode a string of bits in a morse code.
	 * 
     * @return the string
	 * @throws ApiException
	 *             the api exception
	 */
	@Override
	public List<String> decodeBits2Morse() throws ApiException {
		List<String> toDecode = new ArrayList<String>();
		List<String> result = new ArrayList<String>();
		try (Stream<String> stream = Files
				.lines(Paths.get(getClass().getClassLoader().getResource("bitsToDecode.txt").toURI()))) {
			stream.forEach(input -> {
				toDecode.add(input);
			});
		} catch (IOException | URISyntaxException e) {
			LOGGER.error(e.getMessage());
		}
		for (String str : toDecode) {
			result.addAll(decodeBits(str));
		}
		return result;
	}
	
	
	private List<String> decodeBits(String str) throws ApiException {
		String strToDecode = removeZerosLeftRight(str);
		List<String> result = new ArrayList<String>();
		if (strToDecode.matches("[01]+")) {
			Map<Integer, Integer> zeros = new HashMap<Integer, Integer>();
			Map<Integer, Integer> ones = new HashMap<Integer, Integer>();
			int cantCons = 0, promZeros = 0, promOnes = 0;
			for (int i = 0; i <= strToDecode.length(); i++) {
				if (i == 0 || (i < strToDecode.length() && strToDecode.charAt(i - 1) == strToDecode.charAt(i))) {
					cantCons++;
				} else {
					if (strToDecode.charAt(i - 1) == '0') {
						if (zeros.containsKey(cantCons)) {
							zeros.put(cantCons, zeros.get(cantCons) + 1);
						} else {
							zeros.put(cantCons, 1);
							promZeros += cantCons;
						}

					} else {
						if (ones.containsKey(cantCons)) {
							ones.put(cantCons, ones.get(cantCons) + 1);
						} else {
							ones.put(cantCons, 1);
							promOnes += cantCons;
						}
					}
					cantCons = 1;
				}
			}
			setSeparatorRanges(new ArrayList<Integer>(zeros.keySet()), Math.round(promZeros / zeros.size()));
			setSignalRanges(new ArrayList<Integer>(ones.keySet()), Math.round(promOnes / ones.size()));
		}
		String resultToAdd = translateMessage(strToDecode, false);
		if (resultToAdd != null) {
			result.add(resultToAdd);
		}
		if (existsPauseAlternative || existsPulseAlternative) {
			resultToAdd = translateMessage(strToDecode, true);
			if (resultToAdd != null) {
				result.add(resultToAdd);
			}
		}
		return result;
	}

	/**
	 * Eliminate the zeros to the left and to the right of the bit string
	 *
	 * @param str
	 *            the str
	 * @return the string
	 */
	private String removeZerosLeftRight(String str) {
		return str.replace("0", " ").trim().replace(" ", "0");
	}

	
	
	/**
	 * It establishes the ranges of pauses to separate letters and words,
	 *  in case there is more than one possibility, alternative ranges are established
	 *
	 * @param arrayList
	 *            the array list
	 * @param prom
	 *            the prom
	 * @return the separator ranges
	 */
	private void setSeparatorRanges(ArrayList<Integer> arrayList, Integer prom) {
		Map<Boolean, List<Integer>> lettersWords = arrayList.stream().collect(Collectors.partitioningBy(s -> s > prom));
		List<List<Integer>> subSets = new ArrayList<List<Integer>>(lettersWords.values());
		letterSeparatorRange.setStart(subSets.get(0).get(0));
		letterSeparatorRange.setEnd(subSets.get(0).get(subSets.get(0).size() - 1));
		wordSeparatorRange.setStart(subSets.get(1).get(0));
		wordSeparatorRange.setEnd(subSets.get(1).get(subSets.get(1).size() - 1));
		if (arrayList.contains(prom)) {
			existsPauseAlternative = true;
			Map<Boolean, List<Integer>> lettersWords2 = arrayList.stream()
					.collect(Collectors.partitioningBy(s -> s >= prom));
			List<List<Integer>> subSets2 = new ArrayList<List<Integer>>(lettersWords2.values());
			letterSeparatorRangeAlternative.setStart(subSets2.get(0).get(0));
			letterSeparatorRangeAlternative.setEnd(subSets2.get(0).get(subSets2.get(0).size() - 1));
			wordSeparatorRangeAlternative.setStart(subSets2.get(1).get(0));
			wordSeparatorRangeAlternative.setEnd(subSets2.get(1).get(subSets2.get(1).size() - 1));
		}
	}
	
	/**
	 *  It establishes the ranges of pulses for points and lines,
	 * in case there is more than one possibility, alternative ranges are established
	 * 
	 * @param arrayList
	 * @param prom
	 */
	private void setSignalRanges(ArrayList<Integer> arrayList, Integer prom) {
		Map<Boolean, List<Integer>> dotsDashes = arrayList.stream().collect(Collectors.partitioningBy(s -> s > prom));
		List<List<Integer>> subSets = new ArrayList<List<Integer>>(dotsDashes.values());
		dotRange.setStart(subSets.get(0).get(0));
		dotRange.setEnd(subSets.get(0).get(subSets.get(0).size() - 1));
		dashRange.setStart(subSets.get(1).get(0));
		dashRange.setEnd(subSets.get(1).get(subSets.get(1).size() - 1));
		if (arrayList.contains(prom)) {
			existsPulseAlternative = true;
			Map<Boolean, List<Integer>> dotsDashes2 = arrayList.stream()
					.collect(Collectors.partitioningBy(s -> s >= prom));
			List<List<Integer>> subSets2 = new ArrayList<List<Integer>>(dotsDashes2.values());
			dotRangeAlternative.setStart(subSets2.get(0).get(0));
			dotRangeAlternative.setEnd(subSets2.get(0).get(subSets2.get(0).size() - 1));
			dashRangeAlternative.setStart(subSets2.get(1).get(0));
			dashRangeAlternative.setEnd(subSets2.get(1).get(subSets2.get(1).size() - 1));
		}
	}

	/**
	 * 
	 * Translate a bit string into a morse code
	 * 
	 * 
	 * @param str
	 * @param checkAlternative
	 * @return
	 * @throws ApiException
	 */
	private String translateMessage(String str, boolean checkAlternative) throws ApiException {
		String result = "";
		String checkWord = "";
		int cantCons = 0;
		for (int i = 0; i <= str.length(); i++) {
			if (i == 0 || (i < str.length() && str.charAt(i - 1) == str.charAt(i))) {
				cantCons++;
			} else {
				if (str.charAt(i - 1) == '0') {
					String toAdd = getSeparator(cantCons, checkAlternative);
					if (toAdd == " ") {
						if (decodeMorse2Text(checkWord).equals(" ")) {
							return null;
						}
						checkWord = "";
					}
					result += toAdd;
				} else {
					String toAdd = getDotOrDash(cantCons, checkAlternative);
					result += toAdd;
					checkWord += toAdd;
				}
				cantCons = 1;
			}
		}
		return result;
	}

	/**
	 * From a consecutive number of bits a point or stroke is obtained depending on the range that is found
	 * 
	 * @param cantCons
	 * @param checkAlternative
	 * @return
	 */
	private String getDotOrDash(Integer cantCons, boolean checkAlternative) {
		if (checkAlternative && existsPulseAlternative) {
			if (dotRangeAlternative.inRange(cantCons)) {
				return DOT;
			} else {
				return DASH;
			}
		} else {
			if (dotRange.inRange(cantCons)) {
				return DOT;
			} else {
				return DASH;
			}
		}

	}

	
	/**
	 * From a consecutive number of bits a word or letter separator is obtained depending on the range that is found
	 * 
	 * 
	 * @param cantCons
	 * @param checkAlternative
	 * @return
	 */
	private String getSeparator(Integer cantCons, boolean checkAlternative) {
		if (checkAlternative && existsPauseAlternative) {
			if (letterSeparatorRangeAlternative.inRange(cantCons)) {
				return LETTER_SEPARATOR;
			} else {
				return WORD_SEPARATOR;
			}
		} else {
			if (letterSeparatorRange.inRange(cantCons)) {
				return LETTER_SEPARATOR;
			} else {
				return WORD_SEPARATOR;
			}
		}

	}
	
	
	


}
