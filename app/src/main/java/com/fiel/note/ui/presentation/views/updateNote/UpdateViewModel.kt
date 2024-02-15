package com.fiel.note.ui.presentation.views.updateNote

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fiel.note.ui.domain.entity.User
import com.fiel.note.ui.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val userRepository: UserRepository
):ViewModel() {
    var username by mutableStateOf("")
    var password by mutableStateOf("")
    var imageUrl by mutableStateOf("")//
    var latitud by mutableStateOf(0.0)//
    var longitud by mutableStateOf(0.0)//

    //id is send to update url
    val id=savedStateHandle.get<String>(key = "id")

    init {
        viewModelScope.launch {
            //in firebase
            val user= id?.let {getUserById(it)}
            if (user?.firstOrNull() !=null){
                username= user.firstOrNull()!!.username
                password=user.firstOrNull()!!.password
                imageUrl=user.firstOrNull()!!.imageUrl
                latitud=user.firstOrNull()!!.latitude
                longitud=user.firstOrNull()!!.longitude
            }
        }
    }

    fun updateNota()=viewModelScope.launch {

        val user = User(
            id!!,
            username=username,
            password=password,
            imageUrl=imageUrl,
            latitude=latitud,
            longitude =longitud,
        )
        userRepository.updateUser(id,user)
    }

    private fun getUserById(id: String): Flow<User> {
        return userRepository.getUserById(id)
    }
}