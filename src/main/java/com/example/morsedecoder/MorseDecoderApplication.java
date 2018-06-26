package com.example.morsedecoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.example.morsedecoder.configuration.Messages;
import com.example.morsedecoder.service.IMorseDecoderService;




@SpringBootApplication
public class MorseDecoderApplication extends SpringBootServletInitializer{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MorseDecoderApplication.class);
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MorseDecoderApplication.class);
	}
	

	public static void main(String[] args) {
		SpringApplication.run(MorseDecoderApplication.class, args);
	}
	
	@Bean
	CommandLineRunner cargaCodigoMorse(IMorseDecoderService morseCodeService, Messages messages) {
		return (args) -> {
			LOGGER.info(messages.get("carga.inicial.iniciando"));
			morseCodeService.readTreeInfo();
			LOGGER.info(messages.get("carga.inicial.finalizada"));

			LOGGER.info(messages.get("decodebits.iniciando"));
			for (String morseCode : morseCodeService.decodeBits2Morse()) {
				LOGGER.info(messages.get("decodebits.codigoMorse") + morseCode);
				LOGGER.info(messages.get("decodebits.textoHumano") + morseCodeService.decodeMorse2Text(morseCode));
			}
			LOGGER.info(messages.get("decodebits.finalizada"));
		};
	};
}
