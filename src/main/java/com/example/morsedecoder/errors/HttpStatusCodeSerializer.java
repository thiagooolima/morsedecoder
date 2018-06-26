package com.example.morsedecoder.errors;

import java.io.IOException;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
/**
 *   Serializer of status codes called http
 * @author Thiago
 *
 */
public class HttpStatusCodeSerializer extends JsonSerializer<HttpStatus>  {

	@Override
	public void serialize(HttpStatus value, JsonGenerator generator, SerializerProvider serializers)
			throws IOException, JsonProcessingException {
		generator.writeNumber(value.value());
	}
}
