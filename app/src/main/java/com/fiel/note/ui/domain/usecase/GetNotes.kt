package com.fiel.note.ui.domain.usecase

import com.fiel.note.ui.data.ReposityImpl
import javax.inject.Inject

class GetNotes @Inject constructor(private val reposity: ReposityImpl) {
    operator fun invoke()=reposity.getNotes()
}