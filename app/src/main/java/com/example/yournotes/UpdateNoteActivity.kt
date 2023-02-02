package com.example.yournotes

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.yournotes.databinding.ActivityUpdateNoteBinding

class UpdateNoteActivity : AppCompatActivity() {

   private lateinit var receivedNote : Note

    private lateinit var binding: ActivityUpdateNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        val view = binding.root

        receivedNote = intent.getSerializableExtra("update_note") as Note

        binding.updateHeaderET.setText(receivedNote.headerText)
        binding.updateBodyET.setText(receivedNote.bodyText)

        setContentView(view)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.custom_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.saveIv -> {

                val headerText = binding.updateHeaderET.text
                val bodyText = binding.updateBodyET.text

                val updatedNote = Note(
                    headerText.toString(),
                    bodyText.toString()
                )

                updatedNote.id = receivedNote.id

                //Log.e("UpdateNoteActivity", headerText.toString())

                if (headerText.isEmpty() or bodyText.isEmpty()) {
                    Toast.makeText(this, "Text required..", Toast.LENGTH_LONG).show()
                }
                else {
                    val intent = Intent()
                    intent.putExtra("update_notes", updatedNote)
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}