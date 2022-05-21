package com.yohanesrizky.core.usecase

import com.yohanesrizky.core.data.Notee
import com.yohanesrizky.core.repository.NoteRepository

class AddNote(private val noteRepository: NoteRepository) {
    suspend operator fun  invoke(notee:Notee) = noteRepository.addNote(notee)
}