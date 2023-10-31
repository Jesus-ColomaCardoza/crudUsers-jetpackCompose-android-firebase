package com.fiel.note.ui.domain.usecase

import com.fiel.note.ui.data.ReposityImpl
import com.fiel.note.ui.domain.entity.Note
import javax.inject.Inject

class UpdateNote @Inject constructor(private val reposityImpl: ReposityImpl) {
    suspend operator fun invoke(note: Note)=reposityImpl.updateNote(note)
}