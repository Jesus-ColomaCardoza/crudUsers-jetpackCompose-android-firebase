package com.fiel.note.ui.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "note")
data class Note(
   @PrimaryKey(autoGenerate = true) val id:Int=0,
    val titulo:String="",
    val contenido:String=""
)
