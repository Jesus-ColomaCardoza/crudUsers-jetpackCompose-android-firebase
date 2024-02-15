package com.fiel.note.ui.presentation.views.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fiel.note.ui.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject
constructor(
    private val userRepository: UserRepository

):ViewModel() {

    //In firebase
    val notes1=userRepository.getUsers()

    fun deleteNote(id:String/*note:Note*/)=viewModelScope.launch {

        //In firebase
        userRepository.deleteUser(id)
    }

}