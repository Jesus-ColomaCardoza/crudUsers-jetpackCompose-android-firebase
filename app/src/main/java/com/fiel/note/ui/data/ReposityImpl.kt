package com.fiel.note.ui.data

import com.fiel.note.ui.domain.entity.Note
import com.fiel.note.ui.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReposityImpl @Inject constructor (private val dao:Repository):Repository {
    override suspend fun insertNote(note: Note) =dao.insertNote(note = note)
    override suspend fun updateNote(note: Note)=dao.updateNote(note = note)
    override suspend fun deleteNote(note: Note) =dao.deleteNote(note = note)
    override fun getNotes(): Flow<List<Note>> =dao.getNotes()
    override suspend fun getByIdNote(id: Int): Note=dao.getByIdNote(id)

    //trying
    override suspend fun getByPasswordNote(titulo:String,contenido:String): Note=dao.getByPasswordNote(titulo,contenido)
}
