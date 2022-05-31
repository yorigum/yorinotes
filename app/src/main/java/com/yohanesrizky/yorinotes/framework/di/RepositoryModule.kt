package com.yohanesrizky.yorinotes.framework.di

import android.app.Application
import com.yohanesrizky.core.repository.NoteRepository
import com.yohanesrizky.yorinotes.framework.RoomNoteDataSource
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun providesRepository(app:Application) = NoteRepository(RoomNoteDataSource(app))
}