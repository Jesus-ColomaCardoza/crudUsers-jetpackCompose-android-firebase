package com.fiel.note.ui.presentation.views.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fiel.note.ui.domain.entity.Note
import com.fiel.note.ui.domain.repository.UserRepository
import com.fiel.note.ui.domain.usecase.NoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCase: NoteUseCase,
    private val userRepository: UserRepository
): ViewModel(){
    var titulo by mutableStateOf("")
    var contenido by mutableStateOf("")
    var isUser by mutableStateOf("")


    fun Login() =  viewModelScope.launch {
        //In Room
        //val nota= useCase.getByPasswordNote(titulo,contenido)//

        //In firebase
        val user=userRepository.getUserByUsernameAndPassword(titulo,contenido)
        if (user.firstOrNull()!=null){
            isUser="login"
        }else{
            isUser="no-login"
        }
    }
}