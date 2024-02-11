package com.fiel.note.ui.domain.entity

data class User(
    val id: String,
    val username: String,
    val password: String,
    val imageUrl: String,
    val latitude: Double,
    val longitude: Double
){
    constructor(): this("","","","",0.0,0.0)
}
