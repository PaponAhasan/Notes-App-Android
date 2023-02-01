package com.example.yournotes

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NoteRepository(private val noteDao: NoteDao) {

    suspend fun insert(note: Note){
        withContext(Dispatchers.IO) {
            noteDao.insert(note)
        }
    }

    suspend fun delete(note: Note){
        withContext(Dispatchers.IO) {
            noteDao.delete(note)
        }
    }

    val getAllNotes : LiveData<List<Note>> = noteDao.getAllNotes()
}