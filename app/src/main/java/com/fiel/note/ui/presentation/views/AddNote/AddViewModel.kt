package com.fiel.note.ui.presentation.views.AddNote

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fiel.note.ui.domain.entity.Note
import com.fiel.note.ui.domain.entity.User
import com.fiel.note.ui.domain.repository.UserRepository
import com.fiel.note.ui.domain.usecase.NoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class AddViewModel
@Inject
constructor(
    private val useCase: NoteUseCase,
    private val userRepository: UserRepository
):ViewModel() {

    var titulo by mutableStateOf("")
    var contenido by mutableStateOf("")
    var imageUrl by mutableStateOf("")//
    var latitud by mutableStateOf(0.0)//
    var longitud by mutableStateOf(0.0)//

    fun addNote()=viewModelScope.launch {
        //In Room
        useCase.insertNote(Note(
            titulo = titulo,
            contenido = contenido,
            imageUrl = imageUrl,
            latitud=latitud,
            longitud=longitud))

        //In firebase
        val user = User(
            id= UUID.randomUUID().toString(),
            username=titulo,
            password=contenido,
            //change image
            imageUrl=imageUrl,
            latitude=latitud,
            longitude =longitud,
        )

        userRepository.addUser(user)

    }
}