package com.example.fitforlife

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitforlife.databinding.ActivitySessionsListBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class SessionsListActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySessionsListBinding
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()
    private val sessionsList = mutableListOf<WorkoutSession>()
    private lateinit var adapter: SessionsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySessionsListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupClickListeners()
    }

    override fun onStart() {
        super.onStart()

        val user = auth.currentUser
        if (user == null) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        loadSessions()
    }

    private fun setupRecyclerView() {
        adapter = SessionsAdapter(
            sessions = sessionsList,
            onEdit = { session ->
                val intent = Intent(this, CreateSessionActivity::class.java)
                intent.putExtra("sessionId", session.id)
                startActivity(intent)
            },
            onRequestDelete = { session ->
                showDeleteDialog(session)
            }
        )

        binding.recyclerSessions.layoutManager = LinearLayoutManager(this)
        binding.recyclerSessions.adapter = adapter
    }

    private fun setupClickListeners() {
        binding.fabAddSession.setOnClickListener {
            startActivity(Intent(this, CreateSessionActivity::class.java))
        }

        binding.btnLogout.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun showDeleteDialog(session: WorkoutSession) {
        AlertDialog.Builder(this)
            .setTitle("Delete Session")
            .setMessage("Are you sure you want to delete this session?")
            .setPositiveButton("Delete") { _, _ ->
                deleteSession(session)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun deleteSession(session: WorkoutSession) {
        db.collection("sessions")
            .document(session.id)
            .delete()
            .addOnSuccessListener {
                Toast.makeText(this, "Session deleted successfully", Toast.LENGTH_SHORT).show()

                // Update instantly
                sessionsList.remove(session)
                adapter.notifyDataSetChanged()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error deleting session: ${e.message}", Toast.LENGTH_LONG).show()
            }
    }

    private fun loadSessions() {
        val uid = auth.currentUser?.uid ?: return

        binding.progressBar.visibility = View.VISIBLE

        db.collection("sessions")
            .whereEqualTo("ownerUid", uid)
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->

                binding.progressBar.visibility = View.GONE

                if (error != null) return@addSnapshotListener

                sessionsList.clear()

                snapshot?.documents?.forEach { doc ->
                    val session = doc.toObject(WorkoutSession::class.java)
                    session?.let {
                        it.id = doc.id
                        sessionsList.add(it)
                    }
                }

                adapter.notifyDataSetChanged()
            }
    }
}
