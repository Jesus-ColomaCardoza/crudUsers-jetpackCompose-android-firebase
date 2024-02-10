package com.fiel.note.ui.presentation.views.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fiel.note.ui.domain.entity.Note
import com.fiel.note.ui.domain.usecase.NoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCase: NoteUseCase

): ViewModel(){
    var titulo by mutableStateOf("")
    var contenido by mutableStateOf("")
    var isUser by mutableStateOf("")


    fun Login() =  viewModelScope.launch {
        val nota= useCase.getByPasswordNote(titulo,contenido)//
        if ( nota!=null) {
            isUser="login"
        }else{
            isUser="no-login"
        }
    }
}