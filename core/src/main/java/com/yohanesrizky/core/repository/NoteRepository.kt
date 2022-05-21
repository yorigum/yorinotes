package com.yohanesrizky.core.repository

import com.yohanesrizky.core.data.Notee

class NoteRepository(private val dataSource:NoteDataSource) {
    suspend fun addNote(notee: Notee) = dataSource.add(notee)
    suspend fun getNote(id:Long) = dataSource.get(id)
    suspend fun getAllNotes() = dataSource.getAll()
    suspend fun removeNote(notee:Notee) = dataSource.remove(notee)
}