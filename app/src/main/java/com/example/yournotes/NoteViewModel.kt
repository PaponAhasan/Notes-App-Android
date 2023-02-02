package com.example.yournotes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val noteRepository: NoteRepository

    init {
        val noteDao = NoteRoomDatabase.getDatabase(application).getNoteDao()
        noteRepository = NoteRepository(noteDao)
    }

    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        noteRepository.delete(note)
    }

    fun update(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        noteRepository.update(note)
    }

    fun insert(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        noteRepository.insert(note)
    }

    fun getAllNotes(): LiveData<List<Note>> = noteRepository.getAllNotes
}