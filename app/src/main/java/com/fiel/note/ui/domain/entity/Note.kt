package com.fiel.note.ui.domain.entity

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "note")
data class Note(
    @PrimaryKey(autoGenerate = false)
    val id: String =  UUID.randomUUID().toString(),//edited
    val titulo: String = "", //user name
    val contenido: String = "",//password
    val imageUrl: String = "", //
    val latitud: Double = 0.0, //
    val longitud: Double = 0.0 //
)
