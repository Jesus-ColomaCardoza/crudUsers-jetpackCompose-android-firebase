package com.fiel.note.ui.presentation.views.AddNote

import com.fiel.note.ui.domain.entity.User

data class UserAddState(
    val isLoading: Boolean = false,
    val book: User? = null,
    val error: String = ""
)
