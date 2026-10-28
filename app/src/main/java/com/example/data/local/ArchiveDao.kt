package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ArchiveDao {

  // Documents
  @Query("SELECT * FROM archive_documents ORDER BY unsealDate DESC, docNumber ASC")
  fun getAllDocuments(): Flow<List<ArchiveDocument>>

  @Query("SELECT * FROM archive_documents WHERE id = :id")
  fun getDocumentById(id: String): Flow<ArchiveDocument?>

  @Query("""
    SELECT * FROM archive_documents 
    WHERE title LIKE '%' || :query || '%' 
       OR docNumber LIKE '%' || :query || '%' 
       OR summary LIKE '%' || :query || '%' 
       OR fullExcerpt LIKE '%' || :query || '%' 
       OR keyEntities LIKE '%' || :query || '%' 
       OR keywordTags LIKE '%' || :query || '%' 
       OR locations LIKE '%' || :query || '%'
    ORDER BY unsealDate DESC
  """)
  fun searchDocuments(query: String): Flow<List<ArchiveDocument>>

  @Query("SELECT * FROM archive_documents WHERE category = :category ORDER BY unsealDate DESC")
  fun getDocumentsByCategory(category: String): Flow<List<ArchiveDocument>>

  @Query("SELECT * FROM archive_documents WHERE isBookmarked = 1 ORDER BY unsealDate DESC")
  fun getBookmarkedDocuments(): Flow<List<ArchiveDocument>>

  @Query("UPDATE archive_documents SET isBookmarked = :isBookmarked WHERE id = :id")
  suspend fun setDocumentBookmark(id: String, isBookmarked: Boolean)

  @Query("UPDATE archive_documents SET userNotes = :notes WHERE id = :id")
  suspend fun updateDocumentNotes(id: String, notes: String)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertDocuments(documents: List<ArchiveDocument>)

  @Query("SELECT COUNT(*) FROM archive_documents")
  suspend fun getDocumentCount(): Int

  // Named Entities
  @Query("SELECT * FROM named_entities ORDER BY mentionCount DESC, name ASC")
  fun getAllEntities(): Flow<List<NamedEntity>>

  @Query("""
    SELECT * FROM named_entities 
    WHERE name LIKE '%' || :query || '%' 
       OR description LIKE '%' || :query || '%' 
       OR notableExcerpts LIKE '%' || :query || '%'
    ORDER BY mentionCount DESC
  """)
  fun searchEntities(query: String): Flow<List<NamedEntity>>

  @Query("SELECT * FROM named_entities WHERE roleCategory = :role ORDER BY mentionCount DESC")
  fun getEntitiesByRole(role: String): Flow<List<NamedEntity>>

  @Query("UPDATE named_entities SET isFavorite = :isFavorite WHERE id = :id")
  suspend fun setEntityFavorite(id: String, isFavorite: Boolean)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertEntities(entities: List<NamedEntity>)

  // Flight Logs
  @Query("SELECT * FROM flight_logs ORDER BY date DESC")
  fun getAllFlightLogs(): Flow<List<FlightLogEntry>>

  @Query("""
    SELECT * FROM flight_logs 
    WHERE date LIKE '%' || :query || '%' 
       OR tailNumber LIKE '%' || :query || '%' 
       OR origin LIKE '%' || :query || '%' 
       OR destination LIKE '%' || :query || '%' 
       OR pilots LIKE '%' || :query || '%' 
       OR passengers LIKE '%' || :query || '%' 
       OR notes LIKE '%' || :query || '%'
    ORDER BY date DESC
  """)
  fun searchFlightLogs(query: String): Flow<List<FlightLogEntry>>

  @Query("UPDATE flight_logs SET isBookmarked = :isBookmarked WHERE id = :id")
  suspend fun setFlightLogBookmark(id: String, isBookmarked: Boolean)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertFlightLogs(logs: List<FlightLogEntry>)

  // Timeline
  @Query("SELECT * FROM timeline_milestones ORDER BY date ASC")
  fun getAllTimeline(): Flow<List<TimelineMilestone>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertTimeline(items: List<TimelineMilestone>)
}
