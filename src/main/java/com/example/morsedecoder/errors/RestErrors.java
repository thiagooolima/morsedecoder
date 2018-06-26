/**
 * 
 */
package com.example.morsedecoder.errors;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 *
 *  Class that represents an error in a REST service
 * @author Thiago
 *
 */
@JsonInclude(value = Include.NON_NULL)
public class RestErrors {

	/** Status. */
	@JsonSerialize(using = HttpStatusCodeSerializer.class)
	public HttpStatus status;

	/** Error Type. */
	private ErrorType errorType;

	/** Path. */
	private String path;

	/** Time. */
	private Date time;

	/** Description. */
	private String description;

	/**
	 * Instantiates a new rest error.
	 *
	 * @param status
	 *            the status
	 * @param errorType
	 *            the error type
	 * @param exceptionDescription
	 *            the exception description
	 * @param apiErrorDescription
	 *            the api error description
	 * @param time
	 *            the time
	 * @param path
	 *            the path
	 */
	public RestErrors(HttpStatus status, ErrorType errorType, String exceptionDescription, String apiErrorDescription,
			Date time, String path) {
		this.status = status;
		this.errorType = errorType;
		this.description = StringUtils.isNotEmpty(exceptionDescription)
				? exceptionDescription + " - " + apiErrorDescription : apiErrorDescription;
		this.time = time;
		this.path = path;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public HttpStatus getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status
	 *            the new status
	 */
	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	/**
	 * Gets the error type.
	 *
	 * @return the error type
	 */
	public ErrorType getErrorType() {
		return errorType;
	}

	/**
	 * Sets the error type.
	 *
	 * @param errorType
	 *            the new error type
	 */
	public void setErrorType(ErrorType errorType) {
		this.errorType = errorType;
	}

	/**
	 * Gets the path.
	 *
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Sets the path.
	 *
	 * @param path
	 *            the new path
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * Gets the time.
	 *
	 * @return the time
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * Sets the time.
	 *
	 * @param time
	 *            the new time
	 */
	public void setTime(Date time) {
		this.time = time;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description
	 *            the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RestError [status=" + status + ", errorType=" + errorType + ", path=" + path + ", time=" + time
				+ ", description=" + description + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((errorType == null) ? 0 : errorType.hashCode());
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RestErrors other = (RestErrors) obj;
		if (errorType != other.errorType)
			return false;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	
	
}
