package com.fiel.note.ui.domain.usecase

import com.fiel.note.ui.data.ReposityImpl
import javax.inject.Inject

class GetByIdNote @Inject constructor(private val reposityImpl: ReposityImpl) {
    suspend operator fun invoke(id:String)=reposityImpl.getByIdNote(id)
}