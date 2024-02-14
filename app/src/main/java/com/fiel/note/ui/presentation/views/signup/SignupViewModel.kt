package com.fiel.note.ui.presentation.views.signup

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
class SignupViewModel
@Inject
constructor(
    private val useCase: NoteUseCase,
    private val userRepository: UserRepository

): ViewModel(){
    var titulo by mutableStateOf("")
    var contenido by mutableStateOf("")
    var isUserCorrect by mutableStateOf("")


    fun Signup() =  viewModelScope.launch {
        /*val nota=useCase.insertNote(
            Note(
            titulo = titulo,
            contenido = contenido,
            imageUrl = "https://cdn-icons-png.flaticon.com/512/11832/11832458.png",
            latitud=0.0,
            longitud=0.0)
        )*/

        val user = userRepository.addUser(
            User(
            id= UUID.randomUUID().toString(),
            //these parameters are pull out to the user
            username=titulo,
            password=contenido,
            //Image for default
            imageUrl = "https://cdn-icons-png.flaticon.com/512/11832/11832458.png",
            //UNP location for default will are -5.176953123533414,-80.61788197606802
            latitude=-5.176953123533414,
            longitude =-80.61788197606802
        ))

        if ( user!=null) {
            isUserCorrect="signuped"
        }else{
            isUserCorrect="no-signuped"
        }
    }
}