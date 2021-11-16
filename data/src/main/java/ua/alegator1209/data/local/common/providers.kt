package ua.alegator1209.data.local.common

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration

inline fun <reified T : RoomDatabase> db(
    context: Context,
    vararg migrations: Migration,
) = Room.databaseBuilder(
    context,
    T::class.java,
    T::class.simpleName ?: "database"
).addMigrations(*migrations).build()
