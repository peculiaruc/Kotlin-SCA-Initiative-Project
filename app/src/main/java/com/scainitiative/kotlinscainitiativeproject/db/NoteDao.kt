package com.scainitiative.kotlinscainitiativeproject.db

import androidx.room.*
import com.scainitiative.kotlinscainitiativeproject.model.Note

@Dao
interface NoteDao {

    @Insert
    suspend fun addNote(note : Note)

    @Query( "SELECT * FROM note ORDER BY id  DESC")
    suspend fun getAllNotes(): List<Note>

    @Insert
    suspend fun addMultipleNote(vararg note: Note)

    @Update
    suspend fun updateNotes(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)
}
