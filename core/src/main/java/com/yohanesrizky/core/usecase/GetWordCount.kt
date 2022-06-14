package com.yohanesrizky.core.usecase

import com.yohanesrizky.core.data.Note

class GetWordCount {
    operator fun invoke(note: Note) = getCount(note.content)

    private fun getCount(str:String) =
        str.split(" ", "\n").count {
            it.contains(Regex(".*[a-zA-Z].*"))
        }
}