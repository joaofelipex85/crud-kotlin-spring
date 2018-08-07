package com.pontoeletronico.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*


@Configuration
class AppConfig {

    @Bean
    fun resourceBundle() = ResourceBundle
            .getBundle("messages", Locale("pt", "BR"))


}
