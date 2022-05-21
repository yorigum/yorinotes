package com.yohanesrizky.core.data

data class Notee(
    var title: String,
    var content: String,
    var creationTime: Long,
    var updateTime: Long,
    var id: Long = 0
)