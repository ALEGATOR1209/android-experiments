package ua.alegator1209.data.local.common

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

inline fun <reified T : RoomDatabase> db(context: Context) = Room.databaseBuilder(
    context,
    T::class.java,
    T::class.simpleName ?: "database"
).build()
