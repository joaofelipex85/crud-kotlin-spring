package com.pontoeletronico.exception.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.validation.FieldError
import java.util.*


@JsonIgnoreProperties(ignoreUnknown = true)
class ValidationValidDTO(status: Int?, message: String, fieldErrors: List<FieldError>) {

    var status: Int? = null

    var message: String? = null

    var fieldErrors: List<FieldDTO>? = null

    init {
        prepare(status, message, fieldErrors)
    }

    private fun prepare(status: Int?, message: String, fieldErrorList: List<FieldError>) {

        val fieldErrorsList: MutableList<FieldDTO> = ArrayList()

        for (fieldError in fieldErrorList) {
            val field = FieldDTO(fieldError.field, fieldError.defaultMessage)
            fieldErrorsList.add(field)
        }

        this.status = status
        this.message = message
        this.fieldErrors = fieldErrorsList

    }

}
