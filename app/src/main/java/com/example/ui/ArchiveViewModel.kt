package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.ArchiveDocument
import com.example.data.local.FlightLogEntry
import com.example.data.local.NamedEntity
import com.example.data.local.TimelineMilestone
import com.example.data.repository.ArchiveRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class ArchiveTab(val label: String) {
  DOCUMENTS("Documents"),
  ENTITIES("Names & Analysis"),
  FLIGHT_LOGS("Flight Manifests"),
  TIMELINE("Timeline"),
  BOOKMARKS("Saved Research")
}

class ArchiveViewModel(application: Application) : AndroidViewModel(application) {

  private val repository = ArchiveRepository.getInstance(application)

  private val _currentTab = MutableStateFlow(ArchiveTab.DOCUMENTS)
  val currentTab: StateFlow<ArchiveTab> = _currentTab.asStateFlow()

  // Documents state
  private val _docSearchQuery = MutableStateFlow("")
  val docSearchQuery: StateFlow<String> = _docSearchQuery.asStateFlow()

  private val _selectedCategory = MutableStateFlow("All")
  val selectedCategory: StateFlow<String> = _selectedCategory.asStateFlow()

  private val _selectedBatch = MutableStateFlow("All")
  val selectedBatch: StateFlow<String> = _selectedBatch.asStateFlow()

  val documents: StateFlow<List<ArchiveDocument>> = combine(
    repository.allDocuments,
    _docSearchQuery,
    _selectedCategory,
    _selectedBatch
  ) { docs, query, category, batch ->
    docs.filter { doc ->
      val matchesQuery = if (query.isBlank()) true else {
        doc.title.contains(query, ignoreCase = true) ||
          doc.docNumber.contains(query, ignoreCase = true) ||
          doc.summary.contains(query, ignoreCase = true) ||
          doc.fullExcerpt.contains(query, ignoreCase = true) ||
          doc.keyEntities.contains(query, ignoreCase = true) ||
          doc.keywordTags.contains(query, ignoreCase = true) ||
          doc.locations.contains(query, ignoreCase = true)
      }
      val matchesCategory = if (category == "All") true else doc.category.equals(category, ignoreCase = true)
      val matchesBatch = if (batch == "All") true else doc.unsealBatch.contains(batch, ignoreCase = true)
      matchesQuery && matchesCategory && matchesBatch
    }
  }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

  // Bookmarks
  val bookmarkedDocuments: StateFlow<List<ArchiveDocument>> = repository.bookmarkedDocuments
    .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

  // Entities state
  private val _entitySearchQuery = MutableStateFlow("")
  val entitySearchQuery: StateFlow<String> = _entitySearchQuery.asStateFlow()

  private val _selectedRole = MutableStateFlow("All")
  val selectedRole: StateFlow<String> = _selectedRole.asStateFlow()

  val entities: StateFlow<List<NamedEntity>> = combine(
    repository.allEntities,
    _entitySearchQuery,
    _selectedRole
  ) { list, query, role ->
    list.filter { entity ->
      val matchesQuery = if (query.isBlank()) true else {
        entity.name.contains(query, ignoreCase = true) ||
          entity.description.contains(query, ignoreCase = true) ||
          entity.notableExcerpts.contains(query, ignoreCase = true)
      }
      val matchesRole = if (role == "All") true else entity.roleCategory.contains(role, ignoreCase = true)
      matchesQuery && matchesRole
    }
  }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

  // Flight Logs state
  private val _flightSearchQuery = MutableStateFlow("")
  val flightSearchQuery: StateFlow<String> = _flightSearchQuery.asStateFlow()

  private val _flightTailFilter = MutableStateFlow("All")
  val flightTailFilter: StateFlow<String> = _flightTailFilter.asStateFlow()

  val flightLogs: StateFlow<List<FlightLogEntry>> = combine(
    repository.allFlightLogs,
    _flightSearchQuery,
    _flightTailFilter
  ) { logs, query, tail ->
    logs.filter { log ->
      val matchesQuery = if (query.isBlank()) true else {
        log.date.contains(query, ignoreCase = true) ||
          log.tailNumber.contains(query, ignoreCase = true) ||
          log.origin.contains(query, ignoreCase = true) ||
          log.destination.contains(query, ignoreCase = true) ||
          log.passengers.contains(query, ignoreCase = true) ||
          log.pilots.contains(query, ignoreCase = true) ||
          log.notes.contains(query, ignoreCase = true)
      }
      val matchesTail = if (tail == "All") true else log.tailNumber.equals(tail, ignoreCase = true)
      matchesQuery && matchesTail
    }
  }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

  // Timeline state
  val timeline: StateFlow<List<TimelineMilestone>> = repository.allTimeline
    .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

  // Selected Detail Sheets
  private val _selectedDocument = MutableStateFlow<ArchiveDocument?>(null)
  val selectedDocument: StateFlow<ArchiveDocument?> = _selectedDocument.asStateFlow()

  private val _selectedEntity = MutableStateFlow<NamedEntity?>(null)
  val selectedEntity: StateFlow<NamedEntity?> = _selectedEntity.asStateFlow()

  private val _selectedFlightLog = MutableStateFlow<FlightLogEntry?>(null)
  val selectedFlightLog: StateFlow<FlightLogEntry?> = _selectedFlightLog.asStateFlow()

  private val _showStatsDialog = MutableStateFlow(false)
  val showStatsDialog: StateFlow<Boolean> = _showStatsDialog.asStateFlow()

  // Actions
  fun setTab(tab: ArchiveTab) {
    _currentTab.value = tab
  }

  fun setDocSearchQuery(query: String) {
    _docSearchQuery.value = query
  }

  fun setSelectedCategory(category: String) {
    _selectedCategory.value = category
  }

  fun setSelectedBatch(batch: String) {
    _selectedBatch.value = batch
  }

  fun setEntitySearchQuery(query: String) {
    _entitySearchQuery.value = query
  }

  fun setSelectedRole(role: String) {
    _selectedRole.value = role
  }

  fun setFlightSearchQuery(query: String) {
    _flightSearchQuery.value = query
  }

  fun setFlightTailFilter(tail: String) {
    _flightTailFilter.value = tail
  }

  fun selectDocument(document: ArchiveDocument?) {
    _selectedDocument.value = document
  }

  fun selectEntity(entity: NamedEntity?) {
    _selectedEntity.value = entity
  }

  fun selectFlightLog(log: FlightLogEntry?) {
    _selectedFlightLog.value = log
  }

  fun openDocumentById(docId: String) {
    viewModelScope.launch {
      // Find document in allDocuments or by direct query
      val currentList = documents.value
      val found = currentList.firstOrNull { it.id == docId || it.docNumber.equals(docId, ignoreCase = true) }
      if (found != null) {
        _selectedDocument.value = found
      } else {
        // Also look in initial docs
        val initialMatch = com.example.data.local.ArchiveInitialData.getInitialDocuments()
          .firstOrNull { it.id == docId || it.docNumber.equals(docId, ignoreCase = true) }
        if (initialMatch != null) {
          _selectedDocument.value = initialMatch
        }
      }
    }
  }

  fun filterDocumentsByEntityName(name: String) {
    _currentTab.value = ArchiveTab.DOCUMENTS
    _docSearchQuery.value = name
    _selectedCategory.value = "All"
  }

  fun toggleBookmark(doc: ArchiveDocument) {
    viewModelScope.launch {
      repository.toggleDocumentBookmark(doc.id, doc.isBookmarked)
      if (_selectedDocument.value?.id == doc.id) {
        _selectedDocument.value = _selectedDocument.value?.copy(isBookmarked = !doc.isBookmarked)
      }
    }
  }

  fun saveDocumentNotes(docId: String, notes: String) {
    viewModelScope.launch {
      repository.updateDocumentNotes(docId, notes)
      if (_selectedDocument.value?.id == docId) {
        _selectedDocument.value = _selectedDocument.value?.copy(userNotes = notes)
      }
    }
  }

  fun toggleFlightBookmark(log: FlightLogEntry) {
    viewModelScope.launch {
      repository.toggleFlightLogBookmark(log.id, log.isBookmarked)
      if (_selectedFlightLog.value?.id == log.id) {
        _selectedFlightLog.value = _selectedFlightLog.value?.copy(isBookmarked = !log.isBookmarked)
      }
    }
  }

  fun toggleEntityFavorite(entity: NamedEntity) {
    viewModelScope.launch {
      repository.toggleEntityFavorite(entity.id, entity.isFavorite)
      if (_selectedEntity.value?.id == entity.id) {
        _selectedEntity.value = _selectedEntity.value?.copy(isFavorite = !entity.isFavorite)
      }
    }
  }

  fun setShowStatsDialog(show: Boolean) {
    _showStatsDialog.value = show
  }
}
