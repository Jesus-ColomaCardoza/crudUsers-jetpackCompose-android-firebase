package com.fiel.note.ui.presentation.views.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fiel.note.ui.domain.entity.Note
import com.fiel.note.ui.domain.repository.UserRepository
import com.fiel.note.ui.domain.repository.UserResult
import com.fiel.note.ui.domain.usecase.NoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject
constructor(
    private val useCase: NoteUseCase,
    private val userRepository: UserRepository

):ViewModel() {

    //In Room
    val notes=useCase.getNotes()
    //In firebase
    val notes1=userRepository.getUsers()

    fun deleteNote(id:String/*note:Note*/)=viewModelScope.launch {
        //In Room
        //useCase.deleteNote(note)

        //In firebase
        userRepository.deleteUser(id)
    }

}