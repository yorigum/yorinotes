package com.yohanesrizky.core.repository

import com.yohanesrizky.core.data.Notee

interface NoteDataSource {
    suspend fun add(notee: Notee)
    suspend fun get(id:Long):Notee?
    suspend fun getAll():List<Notee>
    suspend fun remove(notee:Notee)
}