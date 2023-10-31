package com.fiel.note.ui.domain.usecase

import com.fiel.note.ui.data.ReposityImpl
import com.fiel.note.ui.domain.entity.Note

class InsertNote constructor(private val reposity: ReposityImpl) {
    suspend operator fun invoke(note: Note)=reposity.insertNote(note)
}