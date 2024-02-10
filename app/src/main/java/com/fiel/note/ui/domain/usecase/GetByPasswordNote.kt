package com.fiel.note.ui.domain.usecase

import com.fiel.note.ui.data.ReposityImpl
import javax.inject.Inject

class GetByPasswordNote @Inject constructor(private val reposityImpl: ReposityImpl) {
    suspend operator fun invoke(titulo:String,contenido:String)=reposityImpl.getByPasswordNote(titulo,contenido)
}