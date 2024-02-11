package com.fiel.note.ui.presentation.views.AddNote

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fiel.note.ui.domain.entity.Note
import com.fiel.note.ui.domain.usecase.NoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(private val useCase: NoteUseCase):ViewModel() {

    var titulo by mutableStateOf("")
    var contenido by mutableStateOf("")
    var imageUrl by mutableStateOf("")//
    var latitud by mutableStateOf(0.0)//
    var longitud by mutableStateOf(0.0)//

    fun addNote()=viewModelScope.launch {
        useCase.insertNote(Note(
            titulo = titulo,
            contenido = contenido,
            imageUrl = imageUrl,
            latitud=latitud,
            longitud=longitud))
    }
}