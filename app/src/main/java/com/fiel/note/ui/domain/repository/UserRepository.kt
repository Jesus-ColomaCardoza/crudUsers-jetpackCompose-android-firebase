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
) {

    fun deleteUser(id: String) {
        try {
            userList.document(id).delete()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun updateUser(id: String, user: User) {
        try {
            val map = mapOf(
                "username" to user.username,
                "password" to user.password,
                "imageUrl" to user.imageUrl,
                "latitude" to user.latitude,
                "longitude" to user.longitude
            )
            userList.document(id).update(map)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun addUser(user: User) {
        try {
            //we create a new document user (register or row) in the collection users(database)
            userList.document(user.id).set(user)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getUsers(): Flow<List<User>> = flow {
        try {
            val userList = userList.get().await().map { document ->
                document.toObject(User::class.java)
            }
            emit(userList)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getUserById(id: String): Flow<User> = flow {
        try {
            val user = userList
                .whereGreaterThanOrEqualTo("id", id)
                .get()
                .await()
                .toObjects(User::class.java)
                .first()
            emit(user)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getUserByUsernameAndPassword(username: String,password: String): Flow<User> = flow {
        try {
            val user = userList
                .whereEqualTo("username", username)
                .whereEqualTo("password", password)
                .get()
                .await()
                .toObjects(User::class.java)
                .first()
            emit(user)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}