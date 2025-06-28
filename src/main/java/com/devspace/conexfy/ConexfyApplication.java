package com.devspace.conexfy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@SpringBootApplication
@EnableEncryptableProperties
public class ConexfyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConexfyApplication.class, args);
	}

}
