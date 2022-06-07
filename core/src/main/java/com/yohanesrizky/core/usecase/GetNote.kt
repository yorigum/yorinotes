package com.yohanesrizky.core.usecase

import com.yohanesrizky.core.repository.NoteRepository

class GetNote(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(id:Long) = noteRepository.getNote(id)
}