package com.fiel.note.ui.domain.usecase

import com.fiel.note.ui.data.ReposityImpl
import com.fiel.note.ui.domain.entity.Note
import javax.inject.Inject

class DeleteNote  @Inject constructor(private val repository:ReposityImpl) {
    suspend operator fun invoke(note: Note)=repository.deleteNote(note)
}