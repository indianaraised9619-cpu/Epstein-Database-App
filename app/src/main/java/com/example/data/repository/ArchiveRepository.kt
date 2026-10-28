package com.example.data.repository

import android.content.Context
import com.example.data.local.ArchiveDao
import com.example.data.local.ArchiveDatabase
import com.example.data.local.ArchiveDocument
import com.example.data.local.ArchiveInitialData
import com.example.data.local.FlightLogEntry
import com.example.data.local.NamedEntity
import com.example.data.local.TimelineMilestone
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArchiveRepository(private val dao: ArchiveDao) {

  val allDocuments: Flow<List<ArchiveDocument>> = dao.getAllDocuments()
  val bookmarkedDocuments: Flow<List<ArchiveDocument>> = dao.getBookmarkedDocuments()
  val allEntities: Flow<List<NamedEntity>> = dao.getAllEntities()
  val allFlightLogs: Flow<List<FlightLogEntry>> = dao.getAllFlightLogs()
  val allTimeline: Flow<List<TimelineMilestone>> = dao.getAllTimeline()

  suspend fun initializeDatabaseIfEmpty() = withContext(Dispatchers.IO) {
    val count = dao.getDocumentCount()
    if (count == 0) {
      dao.insertDocuments(ArchiveInitialData.getInitialDocuments())
      dao.insertEntities(ArchiveInitialData.getInitialEntities())
      dao.insertFlightLogs(ArchiveInitialData.getInitialFlightLogs())
      dao.insertTimeline(ArchiveInitialData.getInitialTimeline())
    }
  }

  fun getDocumentById(id: String): Flow<ArchiveDocument?> = dao.getDocumentById(id)

  fun searchDocuments(query: String): Flow<List<ArchiveDocument>> = dao.searchDocuments(query)

  fun getDocumentsByCategory(category: String): Flow<List<ArchiveDocument>> =
    dao.getDocumentsByCategory(category)

  suspend fun toggleDocumentBookmark(id: String, currentStatus: Boolean) = withContext(Dispatchers.IO) {
    dao.setDocumentBookmark(id, !currentStatus)
  }

  suspend fun updateDocumentNotes(id: String, notes: String) = withContext(Dispatchers.IO) {
    dao.updateDocumentNotes(id, notes)
  }

  fun searchEntities(query: String): Flow<List<NamedEntity>> = dao.searchEntities(query)

  fun getEntitiesByRole(role: String): Flow<List<NamedEntity>> = dao.getEntitiesByRole(role)

  suspend fun toggleEntityFavorite(id: String, currentStatus: Boolean) = withContext(Dispatchers.IO) {
    dao.setEntityFavorite(id, !currentStatus)
  }

  fun searchFlightLogs(query: String): Flow<List<FlightLogEntry>> = dao.searchFlightLogs(query)

  suspend fun toggleFlightLogBookmark(id: String, currentStatus: Boolean) = withContext(Dispatchers.IO) {
    dao.setFlightLogBookmark(id, !currentStatus)
  }

  companion object {
    @Volatile
    private var INSTANCE: ArchiveRepository? = null

    fun getInstance(context: Context): ArchiveRepository {
      return INSTANCE ?: synchronized(this) {
        val database = ArchiveDatabase.getDatabase(context)
        val repo = ArchiveRepository(database.archiveDao())
        CoroutineScope(Dispatchers.IO).launch {
          repo.initializeDatabaseIfEmpty()
        }
        INSTANCE = repo
        repo
      }
    }
  }
}
