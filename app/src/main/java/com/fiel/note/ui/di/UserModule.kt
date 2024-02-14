package com.fiel.note.ui.di

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserModule {

    //we create the firebase instance
    @Provides
    @Singleton
    fun provideFirestoreInstance() = FirebaseFirestore.getInstance()

    //we create the collection users in firebase
    @Provides
    @Singleton
    fun provideUserList(
        firestore: FirebaseFirestore
    ) = firestore.collection("users")
}