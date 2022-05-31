package com.yohanesrizky.yorinotes.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.yohanesrizky.core.data.Note
import com.yohanesrizky.core.repository.NoteRepository
import com.yohanesrizky.core.usecase.AddNote
import com.yohanesrizky.core.usecase.GetAllNotes
import com.yohanesrizky.core.usecase.GetNote
import com.yohanesrizky.core.usecase.RemoveNote
import com.yohanesrizky.yorinotes.framework.di.AppModule
import com.yohanesrizky.yorinotes.framework.di.DaggerViewModelComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class NoteListViewModel(application: Application): AndroidViewModel(application) {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val repository = NoteRepository(RoomNoteDataSource(application))

    @Inject
    lateinit var useCases:UseCases

    init {
        DaggerViewModelComponent.builder()
            .appModule(AppModule(getApplication()))
            .build()
            .Inject(this)
    }

    val notes = MutableLiveData<List<Note>>()

    fun getNotes(){
        coroutineScope.launch {
            val noteList = useCases.getAllNotes()
            notes.postValue(noteList)
        }
    }

}