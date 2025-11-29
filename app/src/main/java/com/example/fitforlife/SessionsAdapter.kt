package com.example.fitforlife

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fitforlife.databinding.ItemSessionBinding
import java.text.SimpleDateFormat
import java.util.*

class SessionsAdapter(
    private val sessions: List<WorkoutSession>,
    private val onEdit: (WorkoutSession) -> Unit,
    private val onDelete: (WorkoutSession) -> Unit) :
    RecyclerView.Adapter<SessionsAdapter.SessionViewHolder>() {

    inner class SessionViewHolder(val binding: ItemSessionBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SessionViewHolder {
        val binding =
            ItemSessionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SessionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SessionViewHolder, position: Int) {
        val session = sessions[position]

        holder.binding.tvTitle.text = session.title
        holder.binding.tvType.text = session.type
        holder.binding.tvDuration.text = "${session.duration} mins"
        holder.binding.tvNotes.text = session.notes

        val dateStr = session.createdAt?.toDate()?.let {
            SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault()).format(it)
        } ?: ""
        holder.binding.tvDate.text = dateStr

        // Long press to delete
        holder.binding.root.setOnLongClickListener {
            onDelete(session)
            true
        }
        // Click to edit
        holder.binding.root.setOnClickListener {
            onEdit(session)
        }

    }

    override fun getItemCount(): Int = sessions.size
}
