package com.example.fitforlife

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fitforlife.databinding.ActivityCreateSessionBinding
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

class CreateSessionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateSessionBinding
    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    private var sessionId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateSessionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupTypeDropdown()

        // Load session if editing
        sessionId = intent.getStringExtra("sessionId")
        sessionId?.let { loadSession(it) }

        binding.btnSave.setOnClickListener {
            saveSession()
        }
    }

    private fun loadSession(id: String) {
        db.collection("sessions").document(id).get()
            .addOnSuccessListener { doc ->
                val session = doc.toObject(WorkoutSession::class.java)
                session?.let {
                    binding.etTitle.setText(it.title)
                    binding.etDuration.setText(it.duration.toString())
                    binding.etNotes.setText(it.notes)
                    val spinnerIndex =
                        (binding.spinnerType.adapter as ArrayAdapter<String>).getPosition(it.type)
                    binding.spinnerType.setSelection(spinnerIndex)
                }
            }
    }

    private fun setupTypeDropdown() {
        val types = listOf("Cardio", "Strength", "Flexibility", "Mobility", "Other")
        val adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, types)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerType.adapter = adapter
    }

    private fun saveSession() {
        val title = binding.etTitle.text.toString().trim()
        val type = binding.spinnerType.selectedItem.toString()
        val durationStr = binding.etDuration.text.toString().trim()
        val notes = binding.etNotes.text.toString().trim()
        val uid = auth.currentUser?.uid ?: return

        // -----------------------------
        // VALIDATION RULES
        // -----------------------------

        // 1. Title must be at least 4 characters
        if (title.length < 4) {
            binding.etTitle.error = "Title must be at least 4 characters"
            return
        }

        // 2. Duration must be a number and >= 1
        if (durationStr.isEmpty()) {
            binding.etDuration.error = "Enter duration in minutes"
            return
        }

        val duration = durationStr.toIntOrNull()
        if (duration == null || duration < 1) {
            binding.etDuration.error = "Duration must be at least 1 minute"
            return
        }

        // 3. Notes must be at least 4 words
        val wordCount = notes.split(" ").filter { it.isNotEmpty() }.size
        if (wordCount < 4) {
            binding.etNotes.error = "Notes must contain at least 4 words"
            return
        }

        // -----------------------------
        // All good → save to Firestore
        // -----------------------------

        val sessionData = hashMapOf(
            "title" to title,
            "type" to type,
            "duration" to duration,
            "notes" to notes,
            "ownerUid" to uid,
            "createdAt" to FieldValue.serverTimestamp()
        )

        if (sessionId != null) {
            // Update existing session
            db.collection("sessions").document(sessionId!!)
                .update(sessionData as Map<String, Any>)
                .addOnSuccessListener {
                    Toast.makeText(this, "Session updated successfully", Toast.LENGTH_SHORT).show()
                    finish()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                }

        } else {
            // Create new session
            db.collection("sessions").add(sessionData)
                .addOnSuccessListener {
                    Toast.makeText(this, "Session created successfully", Toast.LENGTH_SHORT).show()
                    finish()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                }
        }
    }
}
