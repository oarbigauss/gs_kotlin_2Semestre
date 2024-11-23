package br.com.fiap.gs_kotlin.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ItemModel")
data class ItemModel(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val tip: String,
    val description: String
)