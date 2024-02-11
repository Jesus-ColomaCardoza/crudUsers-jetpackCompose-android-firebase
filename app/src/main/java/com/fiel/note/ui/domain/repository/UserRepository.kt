package com.fiel.note.ui.domain.repository

import com.fiel.note.ui.domain.entity.User
import com.google.firebase.firestore.CollectionReference
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
}