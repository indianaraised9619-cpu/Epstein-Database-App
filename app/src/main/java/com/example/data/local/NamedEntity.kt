package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "named_entities")
data class NamedEntity(
  @PrimaryKey val id: String,
  val name: String,
  val roleCategory: String, // "Plaintiff", "Co-defendant / Convicted", "Primary Subject", "Witness / Deposition", "Flight Passenger / Mentioned", "Pilot / Staff", "Legal Counsel"
  val description: String,
  val mentionCount: Int,
  val legalContextDisclaimer: String,
  val keyDocumentIds: String, // Comma-separated list of document IDs
  val notableExcerpts: String,
  val isFavorite: Boolean = false
)
