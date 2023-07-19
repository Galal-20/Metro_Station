package com.example.metro.Avtivity.DataBase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MetroDao {

    @Insert
    fun save(c:Metro)

    @Query("SELECT * FROM MetroData ORDER BY id DESC")
    fun getAllData():MutableList<Metro>

    @Delete
    fun delete(d: Metro)

}