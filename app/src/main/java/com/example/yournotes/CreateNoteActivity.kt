package com.example.yournotes

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.yournotes.databinding.ActivityNoteCreateBinding

class CreateNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteCreateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteCreateBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.custom_toolbar, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.saveIv -> {

                val headerText = binding.headerET.text
                val bodyText = binding.bodyET.text

                val insertNote = Note(
                    headerText.toString(),
                    bodyText.toString()
                )

                if (headerText.isEmpty() or bodyText.isEmpty()) {
                    Toast.makeText(this, "Text required..", Toast.LENGTH_LONG).show()
                }
                else {
                    val intent = Intent()
                    intent.putExtra("notes", insertNote)
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}