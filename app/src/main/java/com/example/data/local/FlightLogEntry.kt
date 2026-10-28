package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "flight_logs")
data class FlightLogEntry(
  @PrimaryKey val id: String,
  val date: String,
  val tailNumber: String, // "N908JE", "N909JE", "N212JE", etc.
  val aircraftType: String, // "Boeing 727-31", "Gulfstream II-B", "Bell 206 Helicopter"
  val origin: String,
  val destination: String,
  val pilots: String,
  val passengers: String,
  val notes: String,
  val sourceDocId: String,
  val isBookmarked: Boolean = false
)
