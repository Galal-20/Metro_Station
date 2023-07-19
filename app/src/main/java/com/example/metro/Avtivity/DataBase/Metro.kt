package com.example.metro.Avtivity.DataBase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "MetroData")
data class Metro (
    val count : Int?,
    val timeOfTrip : Int?,
    val ticketPrice : Int?,
    val routs : String?
        )
{
    @PrimaryKey(autoGenerate = true)
    var id : Int =0
}