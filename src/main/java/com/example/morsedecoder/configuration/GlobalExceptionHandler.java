package com.example.morsedecoder.configuration;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.example.morsedecoder.errors.ErrorType;
import com.example.morsedecoder.errors.GeneralErrors;
import com.example.morsedecoder.errors.RestErrors;
import com.example.morsedecoder.exceptions.ApiException;

	

/**
 *  Handler of global exceptions within the application
 *   @author Thiago
 *
 */
@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {

	/** Constant LOGGER. */
	private final static Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/** Messages. */
	@Autowired
	private Messages messages;
	
	
	/**
	 * Handle api error.
	 *
	 * @param ex
	 *            the ex
	 * @param request
	 *            the request
	 * @return the response entity
	 */
	@ExceptionHandler(value = ApiException.class)
	protected ResponseEntity<RestErrors> handleApiError(ApiException ex, HttpServletRequest request) {

		LOGGER.error(messages.get(GeneralErrors.SYSTEM_ERROR.getCodeMessage()), request.getRequestURI(), ex);

		ResponseStatus responseStatus = AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class);

		HttpStatus status;
		if (responseStatus != null) {
			status = responseStatus.value();
		} else {
			LOGGER.error(messages.get(GeneralErrors.SYSTEM_NOSTATUS.getCodeMessage()));
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		RestErrors error = new RestErrors(status, ex.getErrorType().getErrorType(), messages.get(ex.getDescription()),
				ex.getErrorType().getCodeMessage(), Calendar.getInstance().getTime(), request.getPathInfo());

		return new ResponseEntity<>(error, status);
	}
	
	
	/**
	 * Handle invalid message.
	 *
	 * @param ex
	 *            the ex
	 * @param request
	 *            the request
	 * @return the response entity
	 */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	protected ResponseEntity<RestErrors> handleInvalidMessage(HttpMessageNotReadableException ex,
			HttpServletRequest request) {
		LOGGER.error(messages.get(GeneralErrors.SYSTEM_INVALID_MESSAGE.getCodeMessage()), request.getRequestURI(), ex);

		RestErrors error = new RestErrors(HttpStatus.BAD_REQUEST, ErrorType.GENERAL_ERROR, ex.getMessage(),
				messages.get(GeneralErrors.USER_INVALID_MESSAGE.getCodeMessage()), Calendar.getInstance().getTime(),
				request.getPathInfo());

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	
	/**
	 * Handle media type not supported.
	 *
	 * @param ex
	 *            the ex
	 * @param request
	 *            the request
	 * @return the response entity
	 */
	@ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
	protected ResponseEntity<RestErrors> handleMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpServletRequest request) {
		LOGGER.error(messages.get(GeneralErrors.SYSTEM_MEDIA_TYPE.getCodeMessage()), request.getRequestURI(), ex);

		RestErrors error = new RestErrors(HttpStatus.BAD_REQUEST, ErrorType.GENERAL_ERROR, ex.getMessage(),
				messages.get(GeneralErrors.USER_MEDIA_TYPE.getCodeMessage()), Calendar.getInstance().getTime(),
				request.getPathInfo());

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	
	/**
	 * Handle method not supported.
	 *
	 * @param ex
	 *            the ex
	 * @param request
	 *            the request
	 * @return the response entity
	 */
	@ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
	protected ResponseEntity<RestErrors> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpServletRequest request) {
		LOGGER.error(messages.get(GeneralErrors.SYSTEM_UNSUPPORTED.getCodeMessage()), request.getRequestURI(), ex);

		RestErrors error = new RestErrors(HttpStatus.BAD_REQUEST, ErrorType.GENERAL_ERROR, ex.getMessage(),
				messages.get(GeneralErrors.USER_UNSUPPORTED.getCodeMessage()), Calendar.getInstance().getTime(),
				request.getPathInfo());

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	
	/**
	 * Handle binding exception.
	 *
	 * @param ex
	 *            the ex
	 * @param request
	 *            the request
	 * @return the response entity
	 */
	@ExceptionHandler(value = BindException.class)
	protected ResponseEntity<RestErrors> handleBindingException(BindException ex, HttpServletRequest request) {
		LOGGER.error(messages.get(GeneralErrors.SYSTEM_ERROR.getCodeMessage()), request.getRequestURI(), ex);

		RestErrors error = new RestErrors(HttpStatus.BAD_REQUEST, ErrorType.GENERAL_ERROR, ex.getMessage(),
				messages.get(GeneralErrors.USER_BINDING.getCodeMessage()), Calendar.getInstance().getTime(),
				request.getPathInfo());

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * Handle argument not valid exception.
	 *
	 * @param ex
	 *            the ex
	 * @param request
	 *            the request
	 * @return the response entity
	 */
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	protected ResponseEntity<RestErrors> handleArgumentNotValidException(MethodArgumentNotValidException ex,
			HttpServletRequest request) {
		LOGGER.error(messages.get(GeneralErrors.SYSTEM_REST.getCodeMessage()), request.getRequestURI(), ex);

		RestErrors error = new RestErrors(HttpStatus.BAD_REQUEST, ErrorType.GENERAL_ERROR, ex.getMessage(),
				messages.get(GeneralErrors.ARGUMENTOS_INVALIDOS.getCodeMessage()), Calendar.getInstance().getTime(),
				request.getPathInfo());

		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	/**
	 * Handle.
	 *
	 * @param ex
	 *            the ex
	 * @param request
	 *            the request
	 * @return the response entity
	 */
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<RestErrors> handle(Exception ex, HttpServletRequest request) {
		LOGGER.error(messages.get(GeneralErrors.SYSTEM_ERROR.getCodeMessage()), request.getRequestURI(), ex);
		RestErrors error = new RestErrors(HttpStatus.INTERNAL_SERVER_ERROR, ErrorType.GENERAL_ERROR,
				ex.getMessage() != null ? ex.getMessage()
						: messages.get(GeneralErrors.UNSPECTED_ERROR.getCodeMessage()),
				messages.get(GeneralErrors.ARGUMENTOS_INVALIDOS.getCodeMessage()), Calendar.getInstance().getTime(),
				request.getPathInfo());

		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	/**
	 * Handle general error.
	 *
	 * @param ex
	 *            the ex
	 * @param request
	 *            the request
	 * @return the response entity
	 */
	@ExceptionHandler(value = Exception.class)
	protected ResponseEntity<RestErrors> handleGeneralError(Exception ex, HttpServletRequest request) {
		LOGGER.error(messages.get(GeneralErrors.SYSTEM_ERROR.getCodeMessage()), request.getRequestURI(), ex);

		RestErrors error = new RestErrors(HttpStatus.INTERNAL_SERVER_ERROR, ErrorType.GENERAL_ERROR,
				ex.getMessage() != null ? ex.getMessage() : "An unexpected error has ocurred",
				messages.get(GeneralErrors.ARGUMENTOS_INVALIDOS.getCodeMessage()), Calendar.getInstance().getTime(),
				request.getPathInfo());

		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
