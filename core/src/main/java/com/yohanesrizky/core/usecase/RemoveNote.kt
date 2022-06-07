package com.yohanesrizky.core.usecase

import com.yohanesrizky.core.data.Note
import com.yohanesrizky.core.repository.NoteRepository

class RemoveNote(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(note:Note) = noteRepository.removeNote(note)
}