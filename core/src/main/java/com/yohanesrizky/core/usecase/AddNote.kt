package com.yohanesrizky.core.usecase

import com.yohanesrizky.core.data.Note
import com.yohanesrizky.core.repository.NoteRepository

class AddNote(private val noteRepository: NoteRepository) {
    suspend operator fun  invoke(note:Note) = noteRepository.addNote(note)
}