package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "archive_documents")
data class ArchiveDocument(
  @PrimaryKey val id: String,
  val docNumber: String,
  val courtCase: String,
  val title: String,
  val category: String, // "Court Dockets", "Depositions", "Flight Manifests", "Police Reports", "Exhibits & Evidence", "Grand Jury & NPA"
  val unsealDate: String,
  val originalDate: String,
  val unsealBatch: String,
  val pageCount: Int,
  val classification: String,
  val summary: String,
  val fullExcerpt: String,
  val keyEntities: String, // Comma-separated names
  val locations: String, // Comma-separated locations
  val keywordTags: String, // Comma-separated keywords
  val redactionNotes: String,
  val legalSignificance: String,
  val isBookmarked: Boolean = false,
  val userNotes: String = ""
)
