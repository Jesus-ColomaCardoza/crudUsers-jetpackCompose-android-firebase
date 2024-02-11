package com.fiel.note.ui.presentation.views.AddNote

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.fiel.note.ui.domain.entity.User
import com.fiel.note.ui.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import javax.inject.Inject


@HiltViewModel
class UserAddViewModel
@Inject
constructor(
    private val userRepository: UserRepository
): ViewModel() {

    private val _state: MutableState<UserAddState> = mutableStateOf(UserAddState())
    val state: State<UserAddState>
        get() = _state

    fun addUser(username: String, password: String) {
        val user = User(
            id=UUID.randomUUID().toString(),
            username=username,
            password=password,
            //change image
            imageUrl="https://media.istockphoto.com/photos/blank-book-cover-white-picture-id178447904",
            latitude=0.0,
            longitude =0.0,
        )

        userRepository.addUser(user)
    }

}