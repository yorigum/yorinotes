package com.yohanesrizky.yorinotes.framework.di

import android.app.Application
import com.yohanesrizky.core.repository.NoteRepository
import com.yohanesrizky.core.usecase.AddNote
import com.yohanesrizky.core.usecase.GetAllNotes
import com.yohanesrizky.core.usecase.GetNote
import com.yohanesrizky.core.usecase.RemoveNote
import com.yohanesrizky.yorinotes.framework.RoomNoteDataSource
import com.yohanesrizky.yorinotes.framework.UseCases
import dagger.Module
import dagger.Provides

@Module
class UsecaseModule {
    @Provides
    fun getUsecase(repository: NoteRepository) = UseCases(
        AddNote(repository),
        GetAllNotes(repository),
        GetNote(repository),
        RemoveNote(repository)
    )
}