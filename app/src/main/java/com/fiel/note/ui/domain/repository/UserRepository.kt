package com.fiel.note.ui.domain.repository

import com.fiel.note.ui.domain.entity.User
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository
@Inject
constructor(
    private val userList: CollectionReference
)
{
    fun addUser(user: User){
        try {
            //we create a new document user (register or row) in the collection users(database)
            userList.document(user.id).set(user)
        } catch (e: Exception){
            e.printStackTrace()
        }
    }

    fun getUsers():Flow<List<User>> = flow {
        //try {
            //emit(UserResult.Loading<List<User>>())

            val userList = userList.get().await().map{ document ->
                document.toObject(User::class.java)
            }


            emit(userList)
        //} catch (e: Exception) {
            //emit(UserResult.Error<List<User>>(message = e.localizedMessage ?: "Error Desconocido"))
        //}
    }
}