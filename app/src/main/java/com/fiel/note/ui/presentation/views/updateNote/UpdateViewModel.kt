package com.fiel.note.ui.presentation.views.updateNote

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
class UpdateViewModel @Inject constructor(
    private val useCase: NoteUseCase,
    private val savedStateHandle: SavedStateHandle
):ViewModel() {
    var titulo by mutableStateOf("")
    var contenido by mutableStateOf("")
    var imageUrl by mutableStateOf("")//

    val id=savedStateHandle.get<Int>(key = "id")

    init {
        viewModelScope.launch {
            val nota= id?.let { useCase.getByIdNote(it) }
            if (nota!=null){
                contenido=nota.contenido
                titulo=nota.titulo
                imageUrl=nota.imageUrl

            }
        }
    }

    fun updateNota()=viewModelScope.launch {
        useCase.updateNote(Note(id!!,titulo, contenido = contenido,imageUrl=imageUrl))
    }
}