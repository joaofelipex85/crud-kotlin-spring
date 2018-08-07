package com.pontoeletronico.response

import java.util.*

data class Response<T>(
        val erros: MutableList<String> = ArrayList(),
        var data: T? = null
)
