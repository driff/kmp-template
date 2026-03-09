package com.driff.template.core.error

sealed class AppError {
    data class Network(val cause: Throwable) : AppError()
    data class Local(val cause: Throwable) : AppError()
    data class Unknown(val cause: Throwable) : AppError()
}
