package com.yohanesrizky.yorinotes.framework

import android.content.Context
import com.yohanesrizky.core.data.Note
import com.yohanesrizky.core.repository.NoteDataSource
import com.yohanesrizky.yorinotes.framework.db.DatabaseService
import com.yohanesrizky.yorinotes.framework.db.NoteEntity

class RoomNoteDataSource(context:Context):NoteDataSource {

    private val noteDao = DatabaseService.getInstance(context).noteDao()
    override suspend fun add(note: Note) = noteDao.addNoteEntity(NoteEntity.fromNote(note))

    override suspend fun get(id: Long): Note?  = noteDao.getNoteEntities(id).toNote()


    override suspend fun getAll(): List<Note> =noteDao.getAllNotesEntity().map { it.toNote() }

    override suspend fun remove(note: Note) = noteDao.deleteNoteEntity(NoteEntity.fromNote(note))

}