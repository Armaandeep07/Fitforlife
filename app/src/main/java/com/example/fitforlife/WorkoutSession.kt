package com.example.fitforlife

import com.google.firebase.Timestamp

// Data class representing a workout session in Firestore
data class WorkoutSession(
    var id: String = "", // Firestore document ID
    var title: String = "",
    var type: String = "",
    var duration: Int = 0,
    var notes: String = "",
    var ownerUid: String = "",
    var createdAt: Timestamp? = null
)
