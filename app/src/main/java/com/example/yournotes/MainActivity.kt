package com.example.yournotes

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yournotes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), INoteRvAdapter {

    private lateinit var noteViewModel: NoteViewModel
    private lateinit var adapter: NoteAdapter
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
    override fun onStart() {
        super.onStart()

        adapter = NoteAdapter(this, this)
        binding.notesRecyclerView.adapter = adapter

        binding.notesRecyclerView.layoutManager = LinearLayoutManager(this)

        noteViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[NoteViewModel::class.java]

        noteViewModel.getAllNotes().observe(this) { notes ->
            notes?.let {
                adapter.updateNoteList(notes)
            }
        }

        binding.fabAddNote.setOnClickListener {
            val intent = Intent(this, CreateNoteActivity::class.java)
            //getResult.launch(intent)
            //startActivity(intent)
            startActivityForResult(intent, 101)
        }
    }
    override fun deleteNoteClicked(note: Note) {
        noteViewModel.deleteNote(note)
        Toast.makeText(this, "${note.headerText} Deleted", Toast.LENGTH_LONG).show()
    }
    override fun updateNoteClicked(note: Note) {
        val intent = Intent(this, UpdateNoteActivity::class.java)
        intent.putExtra("update_note", note)
        startActivityForResult(intent, 102)
        //getUpdateResult.launch(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 101){
            if (resultCode == Activity.RESULT_OK) {
                val notesResult = data?.getSerializableExtra("notes") as Note
                noteViewModel.insert(notesResult)
                Toast.makeText(this, "${notesResult.headerText} Inserted", Toast.LENGTH_LONG).show()
            }
        }
        else if(requestCode == 102){
            if (resultCode == Activity.RESULT_OK) {
                val notesUpdateResult = data?.getSerializableExtra("update_notes") as Note
                noteViewModel.update(notesUpdateResult)
                Toast.makeText(this, "${notesUpdateResult.headerText} Updated", Toast.LENGTH_LONG).show()
            }
        }
    }

//    private var getResult = registerForActivityResult(
//        ActivityResultContracts.StartActivityForResult()
//    ) { result ->
//
//        if (result.resultCode == Activity.RESULT_OK) {
//            val notesUpdateResult = result.data?.getParcelableExtra<Note>("update_notes")
//            Toast.makeText(this, "Yea..$notesUpdateResult", Toast.LENGTH_LONG).show()
//            if (notesUpdateResult != null) {
//                noteViewModel.update(notesUpdateResult)
//                Toast.makeText(this, "${notesUpdateResult.headerText} Updated", Toast.LENGTH_LONG).show()
//            }
//        }
//    }
}