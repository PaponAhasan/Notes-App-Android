package com.example.yournotes

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.example.yournotes.databinding.ActivityNoteCreateBinding

class CreateNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoteCreateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteCreateBinding.inflate(layoutInflater)
        val view = binding.root

        val headerText = binding.headerET.text
        val bodyText = binding.bodyET.text

        binding.saveBtn.setOnClickListener {

            Log.e("CreateNoteActivity", headerText.toString())

            if (headerText.isEmpty() or bodyText.isEmpty()) {
                Toast.makeText(this, "Text required..", Toast.LENGTH_LONG).show()
            }
            else {
                Toast.makeText(this, "$headerText Inserted", Toast.LENGTH_LONG).show()
                val intent = Intent()
                intent.putExtra("headerResult", "$headerText")
                intent.putExtra("bodyResult", "$bodyText")
                setResult(Activity.RESULT_OK, intent)
                finish()
           }
        }

        setContentView(view)
    }
}