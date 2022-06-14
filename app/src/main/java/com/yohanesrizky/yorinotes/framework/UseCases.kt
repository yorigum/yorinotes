package com.yohanesrizky.yorinotes.framework

import com.yohanesrizky.core.usecase.*

data class UseCases(
    val addNote: AddNote,
    val getAllNotes: GetAllNotes,
    val getNote: GetNote,
    val removeNote: RemoveNote,
    val wordCount: GetWordCount
)