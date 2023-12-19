package ru.krinitsyn.uitestssample.models.presentation

import kotlinx.serialization.SerialName
import ru.krinitsyn.uitestssample.models.data.UserExt

data class Gist(
    val description: String,
    val owner: User
)