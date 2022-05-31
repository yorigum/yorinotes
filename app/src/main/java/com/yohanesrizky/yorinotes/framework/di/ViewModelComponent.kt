package com.yohanesrizky.yorinotes.framework.di

import com.yohanesrizky.yorinotes.framework.NoteListViewModel
import com.yohanesrizky.yorinotes.framework.NoteViewModel
import dagger.Component
import javax.inject.Inject

@Component(modules = [AppModule::class,RepositoryModule::class,UsecaseModule::class])
interface ViewModelComponent{
    fun Inject(noteListViewModel: NoteListViewModel)
    fun Inject(noteViewModel: NoteViewModel)
}