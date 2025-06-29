package com.devspace.conexfy

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableEncryptableProperties
class ConexfyApplication

fun main(args: Array<String>) {
    runApplication<ConexfyApplication>(*args)
}