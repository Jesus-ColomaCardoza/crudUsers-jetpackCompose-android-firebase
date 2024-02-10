package com.fiel.note.ui.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fiel.note.ui.domain.entity.Note
import com.fiel.note.ui.domain.repository.Repository

@Database(entities = [Note::class], exportSchema = true, version = 1)
//version 1 : init
abstract class NoteDatabase:RoomDatabase() {
    abstract fun noteDao():Repository
}