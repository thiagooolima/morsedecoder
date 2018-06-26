/**
 * 
 */
package com.example.morsedecoder.domain;

/**
 * @author Thiago
 *
 */
public class Range {
	
	
	/**  Start  */
	Integer start;
	
	
	/**  End  */
	Integer end;
	
	/** Instantiates a new range. */
	public Range() {
	}

	
	/**
	 * Instantiates a new range.
	 *
	 * @param start
	 *            the start
	 * @param end
	 *            the end
	 */
	public Range(Integer start, Integer end) {
		super();
		this.start= start;
		this.end= end;
	}
	
	
	
	/**
	 * In range.
	 *
	 * @param num
	 *            the num
	 * @return true, if successful
	 */
	public boolean inRange(Integer num) {
		return num >= start && num <= end;
	}


	/**
	 * @return the start
	 */
	public Integer getStart() {
		return start;
	}


	/**
	 * @param start the start to set
	 */
	public void setStart(Integer start) {
		this.start = start;
	}


	/**
	 * @return the end
	 */
	public Integer getEnd() {
		return end;
	}


	/**
	 * @param end the end to set
	 */
	public void setEnd(Integer end) {
		this.end = end;
	}

	
}
