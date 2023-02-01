package com.example.yournotes

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), INoteRvAdapter {

    private lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        val notesRecyclerView = findViewById<RecyclerView>(R.id.notesRecyclerView)

        val adapter = NoteAdapter(this, this)
        notesRecyclerView.adapter = adapter

        notesRecyclerView.layoutManager = LinearLayoutManager(this)

        //val noteViewModel = ViewModelProvider(this)[NodeViewModel::class.java]
        noteViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[NoteViewModel::class.java]
        noteViewModel.getAllNotes().observe(this) { notes ->
            notes?.let {
                adapter.updateNoteList(notes)
            }
        }

        val fabAddNote = findViewById<FloatingActionButton>(R.id.fabAddNote)
        fabAddNote.setOnClickListener {
            val intent = Intent(this, CreateNoteActivity::class.java)
            getResult.launch(intent)
        }
    }

    override fun deleteNoteClicked(note: Note) {
        noteViewModel.deleteNote(note)
        Toast.makeText(this, "${note.headerText} Deleted", Toast.LENGTH_LONG).show()
    }

    override fun addNoteClicked(note: Note) {
        TODO("Not yet implemented")
    }

    private var getResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->

        if (result.resultCode == Activity.RESULT_OK) {
            val headerData = result.data?.getStringExtra("headerResult")
            val bodyData = result.data?.getStringExtra("bodyResult")
            Toast.makeText(this, "$headerData Inserted", Toast.LENGTH_LONG).show()
//          headerData?.let { Note(it) }?.let { noteViewModel.addNote(it) }

            if (bodyData != null) {
                if (headerData != null) {
                    noteViewModel.addNote(Note(headerData, bodyData))
                    Toast.makeText(this, "$headerData Inserted", Toast.LENGTH_LONG).show()
                }
            }
        }
    }


}