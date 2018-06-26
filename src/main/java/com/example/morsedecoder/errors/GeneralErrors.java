/**
 * 
 */
package com.example.morsedecoder.errors;



/**
 *   Enumerator that implements types of errors within the application
 * @author Thiago
 *
 */
public enum GeneralErrors implements IApiErrors {

	/** Unspected Error */
	UNSPECTED_ERROR(ErrorType.GENERAL_ERROR, "system.error.unspected"),

	/** Argumentos invalidos. */
	ARGUMENTOS_INVALIDOS(ErrorType.GENERAL_ERROR, "user.error.arguments"),

	/** System Error. */
	SYSTEM_ERROR(ErrorType.SYSTEM_ERROR, "system.error.generic"),

	/** System Nostatus */
	SYSTEM_NOSTATUS(ErrorType.SYSTEM_ERROR, "system.error.noStatus"),

	/** System Invalid Message. */
	SYSTEM_INVALID_MESSAGE(ErrorType.SYSTEM_ERROR, "system.error.invalid.message"),

	/** System Media Type. */
	SYSTEM_MEDIA_TYPE(ErrorType.SYSTEM_ERROR, "system.error.media.type"),

	/** System Unsupported. */
	SYSTEM_UNSUPPORTED(ErrorType.SYSTEM_ERROR, "system.error.unsupported.method"),

	/** System Rest. */
	SYSTEM_REST(ErrorType.SYSTEM_ERROR, "system.error.rest"),

	/** User Invalid Message. */
	// Described a business errors
	USER_INVALID_MESSAGE(ErrorType.BUSINESS_ERROR, "user.error.invalid.message"),

	/** User Unsupported. */
	USER_UNSUPPORTED(ErrorType.BUSINESS_ERROR, "user.error.unsupported.method"),

	/** User Binding. */
	USER_BINDING(ErrorType.BUSINESS_ERROR, "user.error.binding"),

	/** User Media Type. */
	USER_MEDIA_TYPE(ErrorType.BUSINESS_ERROR, "user.error.media.type"),

	/** Letter Already Exists. */
	LETTER_ALREADY_EXISTS(ErrorType.BUSINESS_ERROR, "morse.node.exists"),

	/** Not Encoding Character. */
	NOT_ENCODING_CHARACTER(ErrorType.BUSINESS_ERROR, "morse.encode.error"),

	/** Bad Morse Character. */
	BAD_MORSE_CHARACTER(ErrorType.BUSINESS_ERROR, "morse.carga.error");

	/** Error Type */
	private final ErrorType tipoError;

	/** Code Message. */
	private final String codeMessage;

	/**
	 * Instantiates a new general errors.
	 *
	 * @param tipoError
	 *            the tipo error
	 * @param codeMessage
	 *            the code message
	 */
	private GeneralErrors(ErrorType tipoError, String codeMessage) {
		this.tipoError = tipoError;
		this.codeMessage = codeMessage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 */
	@Override
	public String getCodeMessage() {
		return codeMessage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 */
	@Override
	public ErrorType getErrorType() {
		return tipoError;
	}
	
	
}
