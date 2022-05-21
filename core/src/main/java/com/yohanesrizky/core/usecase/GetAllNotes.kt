package com.yohanesrizky.core.usecase

import com.yohanesrizky.core.repository.NoteRepository

class GetAllNotes(private val noteRepository: NoteRepository ) {
    suspend operator fun invoke() = noteRepository.getAllNotes()
}