package com.scainitiative.kotlinscainitiativeproject.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.scainitiative.kotlinscainitiativeproject.model.Note

@Database(entities = [Note::class], version = 1)
 abstract class Notedb : RoomDatabase() {

    abstract fun getNoteDao() : NoteDao

    companion object {

        @Volatile private var instance : Notedb? = null
        private val  LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private  fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext, Notedb::class.java,
            "notedb"
        ).build()
    }



}