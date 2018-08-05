package com.pontoeletronico.exception.handler

import com.pontoeletronico.exception.dto.ValidationValidDTO
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import java.util.*

@ControllerAdvice(annotations = arrayOf(RestController::class))
class ExceptionsHandler @Autowired
constructor(private val resourceBundle: ResourceBundle) {

    private val LOGGER = LoggerFactory.getLogger(ExceptionsHandler::class.java.name)

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<*> {
        LOGGER.error(e.message, e)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ValidationValidDTO(HttpStatus.BAD_REQUEST.value(), resourceBundle.getString("error.validation.bean"), e.bindingResult.fieldErrors))
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadableException(e: HttpMessageNotReadableException): ResponseEntity<*> {
        LOGGER.error(e.message, e)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resourceBundle.getString("error.validation.bean.required"))
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<*> {
        LOGGER.error(e.message, e)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body<String>(e.message)
    }

}
