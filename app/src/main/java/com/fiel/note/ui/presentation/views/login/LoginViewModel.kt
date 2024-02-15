package com.fiel.note.ui.presentation.views.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fiel.note.ui.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel(){
    var username by mutableStateOf("")
    var password by mutableStateOf("")
    var isUser by mutableStateOf("")

    fun Login() =  viewModelScope.launch {

        //In firebase
        val user=userRepository.getUserByUsernameAndPassword(username,password)
        if (user.firstOrNull()!=null){
            isUser="login"
        }else{
            isUser="no-login"
        }
    }
}