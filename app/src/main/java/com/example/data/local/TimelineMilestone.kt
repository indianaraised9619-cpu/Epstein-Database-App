package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "timeline_milestones")
data class TimelineMilestone(
  @PrimaryKey val id: String,
  val year: String,
  val date: String,
  val title: String,
  val category: String, // "Investigation", "Legal Action", "Criminal Trial", "Unsealing Order"
  val description: String,
  val significance: String,
  val relatedDocId: String? = null
)
