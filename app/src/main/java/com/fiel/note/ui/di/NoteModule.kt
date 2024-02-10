package com.fiel.note.ui.di

import android.content.Context
import androidx.room.Room
import com.fiel.note.ui.data.NoteDatabase
import com.fiel.note.ui.data.ReposityImpl
import com.fiel.note.ui.domain.usecase.DeleteNote
import com.fiel.note.ui.domain.usecase.GetByIdNote
import com.fiel.note.ui.domain.usecase.GetByPasswordNote
import com.fiel.note.ui.domain.usecase.GetNotes
import com.fiel.note.ui.domain.usecase.InsertNote
import com.fiel.note.ui.domain.usecase.NoteUseCase
import com.fiel.note.ui.domain.usecase.UpdateNote
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NoteModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context):NoteDatabase= Room.databaseBuilder(
        context=context,
        klass = NoteDatabase::class.java,
        name = "NoteDB"
    ).build()


    @Provides
    fun provideDao(dao:NoteDatabase)=dao.noteDao()

    @Provides
    fun provideNoteUseCase(repository:ReposityImpl)=NoteUseCase(
        getNotes = GetNotes(repository),
        getByIdNote = GetByIdNote(repository),
        //trying
        getByPasswordNote = GetByPasswordNote(repository),
        deleteNote = DeleteNote(repository),
        updateNote = UpdateNote(repository),
        insertNote = InsertNote(repository)
    )

}