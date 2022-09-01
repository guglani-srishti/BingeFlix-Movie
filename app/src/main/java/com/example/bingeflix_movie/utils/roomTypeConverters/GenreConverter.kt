package com.example.bingeflix_movie.utils.roomTypeConverters

import androidx.room.TypeConverter
import java.util.*


class GenreConverter {
    @TypeConverter
    fun storedStringToIds(value: String): ArrayList<Int>? {
        val list = Arrays.asList(value.split("\\s*,\\s*"))
        lateinit var ids: ArrayList<Int>
        for(id in list!!)
            ids.add(Integer.parseInt(id.toString()))
        return ids
    }

    @TypeConverter
    fun idsToStoredString(id: ArrayList<Int>): String? {
        var value = ""
        for (i in id!!) value += "$id,"
        return value
    }
}
