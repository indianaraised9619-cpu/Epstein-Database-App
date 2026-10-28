package com.example.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Flight
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Flight
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.Timeline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.components.DocumentDetailSheet
import com.example.ui.components.EntityDetailSheet
import com.example.ui.components.StatsDialog
import com.example.ui.screens.BookmarksScreen
import com.example.ui.screens.DocumentsScreen
import com.example.ui.screens.EntitiesScreen
import com.example.ui.screens.FlightLogsScreen
import com.example.ui.screens.TimelineScreen
import com.example.ui.theme.ArchiveBgDark
import com.example.ui.theme.ArchiveBorder
import com.example.ui.theme.ArchiveSurfaceCard
import com.example.ui.theme.ArchiveSurfaceDark
import com.example.ui.theme.ArchiveSurfaceElevated
import com.example.ui.theme.CategoryCourt
import com.example.ui.theme.CrimsonStamp
import com.example.ui.theme.CrimsonStampBg
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.SlateTextPrimary
import com.example.ui.theme.SlateTextSecondary
import com.example.ui.theme.SlateTextTertiary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArchiveApp(
  viewModel: ArchiveViewModel = viewModel(),
  modifier: Modifier = Modifier
) {
  val currentTab by viewModel.currentTab.collectAsState()
  val documents by viewModel.documents.collectAsState()
  val docQuery by viewModel.docSearchQuery.collectAsState()
  val selectedCategory by viewModel.selectedCategory.collectAsState()
  val selectedBatch by viewModel.selectedBatch.collectAsState()

  val entities by viewModel.entities.collectAsState()
  val entityQuery by viewModel.entitySearchQuery.collectAsState()
  val selectedRole by viewModel.selectedRole.collectAsState()

  val flightLogs by viewModel.flightLogs.collectAsState()
  val flightQuery by viewModel.flightSearchQuery.collectAsState()
  val selectedTail by viewModel.flightTailFilter.collectAsState()

  val timeline by viewModel.timeline.collectAsState()
  val bookmarkedDocs by viewModel.bookmarkedDocuments.collectAsState()

  val selectedDocument by viewModel.selectedDocument.collectAsState()
  val selectedEntity by viewModel.selectedEntity.collectAsState()
  val selectedFlightLog by viewModel.selectedFlightLog.collectAsState()
  val showStatsDialog by viewModel.showStatsDialog.collectAsState()

  // Handle Android Back Navigation
  BackHandler(enabled = selectedDocument != null || selectedEntity != null || currentTab != ArchiveTab.DOCUMENTS) {
    when {
      selectedDocument != null -> viewModel.selectDocument(null)
      selectedEntity != null -> viewModel.selectEntity(null)
      currentTab != ArchiveTab.DOCUMENTS -> viewModel.setTab(ArchiveTab.DOCUMENTS)
    }
  }

  Scaffold(
    modifier = modifier
      .fillMaxSize()
      .background(ArchiveBgDark),
    topBar = {
      ArchiveTopBar(
        onOpenStats = { viewModel.setShowStatsDialog(true) }
      )
    },
    bottomBar = {
      ArchiveBottomBar(
        currentTab = currentTab,
        onTabSelected = { viewModel.setTab(it) },
        bookmarkCount = bookmarkedDocs.size
      )
    },
    containerColor = ArchiveBgDark
  ) { innerPadding ->
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
    ) {
      when (currentTab) {
        ArchiveTab.DOCUMENTS -> DocumentsScreen(
          documents = documents,
          searchQuery = docQuery,
          onSearchQueryChange = { viewModel.setDocSearchQuery(it) },
          selectedCategory = selectedCategory,
          onCategorySelected = { viewModel.setSelectedCategory(it) },
          selectedBatch = selectedBatch,
          onBatchSelected = { viewModel.setSelectedBatch(it) },
          onDocumentClick = { viewModel.selectDocument(it) },
          onToggleBookmark = { viewModel.toggleBookmark(it) },
          onEntityClick = { entityName ->
            viewModel.filterDocumentsByEntityName(entityName)
          }
        )

        ArchiveTab.ENTITIES -> EntitiesScreen(
          entities = entities,
          searchQuery = entityQuery,
          onSearchQueryChange = { viewModel.setEntitySearchQuery(it) },
          selectedRole = selectedRole,
          onRoleSelected = { viewModel.setSelectedRole(it) },
          onEntityClick = { viewModel.selectEntity(it) },
          onToggleFavorite = { viewModel.toggleEntityFavorite(it) },
          onFilterDocuments = { entity ->
            viewModel.filterDocumentsByEntityName(entity.name)
          }
        )

        ArchiveTab.FLIGHT_LOGS -> FlightLogsScreen(
          logs = flightLogs,
          searchQuery = flightQuery,
          onSearchQueryChange = { viewModel.setFlightSearchQuery(it) },
          selectedTail = selectedTail,
          onTailSelected = { viewModel.setFlightTailFilter(it) },
          onToggleBookmark = { viewModel.toggleFlightBookmark(it) },
          onOpenSourceDoc = { docId ->
            viewModel.openDocumentById(docId)
          },
          onPassengerClick = { passengerName ->
            viewModel.setFlightSearchQuery(passengerName)
          }
        )

        ArchiveTab.TIMELINE -> TimelineScreen(
          timeline = timeline,
          onOpenDocument = { docId ->
            viewModel.openDocumentById(docId)
          }
        )

        ArchiveTab.BOOKMARKS -> BookmarksScreen(
          bookmarkedDocuments = bookmarkedDocs,
          onDocumentClick = { viewModel.selectDocument(it) },
          onToggleBookmark = { viewModel.toggleBookmark(it) },
          onExploreDocuments = { viewModel.setTab(ArchiveTab.DOCUMENTS) },
          onEntityClick = { entityName ->
            viewModel.filterDocumentsByEntityName(entityName)
          }
        )
      }
    }
  }

  // Document Detail Sheet
  selectedDocument?.let { doc ->
    DocumentDetailSheet(
      document = doc,
      onDismiss = { viewModel.selectDocument(null) },
      onToggleBookmark = { viewModel.toggleBookmark(doc) },
      onSaveNotes = { notes -> viewModel.saveDocumentNotes(doc.id, notes) },
      onEntityClick = { entityName ->
        viewModel.selectDocument(null)
        viewModel.filterDocumentsByEntityName(entityName)
      }
    )
  }

  // Entity Detail Sheet
  selectedEntity?.let { entity ->
    EntityDetailSheet(
      entity = entity,
      onDismiss = { viewModel.selectEntity(null) },
      onOpenDocument = { docId ->
        viewModel.openDocumentById(docId)
      },
      onFilterAllDocs = {
        viewModel.filterDocumentsByEntityName(entity.name)
      }
    )
  }

  // Stats & Public Record Dialog
  if (showStatsDialog) {
    StatsDialog(
      docCount = documents.size,
      entityCount = entities.size,
      flightCount = flightLogs.size,
      onDismiss = { viewModel.setShowStatsDialog(false) }
    )
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ArchiveTopBar(
  onOpenStats: () -> Unit
) {
  Surface(
    color = ArchiveSurfaceDark,
    border = BorderStroke(1.dp, ArchiveBorder)
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .windowInsetsPadding(WindowInsets.statusBars)
        .padding(horizontal = 16.dp, vertical = 10.dp),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        Surface(
          color = ArchiveSurfaceElevated,
          shape = CircleShape,
          border = BorderStroke(1.dp, GoldAccent.copy(alpha = 0.5f))
        ) {
          Icon(
            imageVector = Icons.Default.Shield,
            contentDescription = null,
            tint = GoldAccent,
            modifier = Modifier
              .padding(6.dp)
              .size(20.dp)
          )
        }

        Column {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
          ) {
            Text(
              text = "Epstein Files Archive",
              fontSize = 17.sp,
              fontWeight = FontWeight.Bold,
              color = SlateTextPrimary
            )
            Surface(
              color = CrimsonStampBg,
              shape = RoundedCornerShape(3.dp),
              border = BorderStroke(0.5.dp, CrimsonStamp)
            ) {
              Text(
                text = "SDNY",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = CrimsonStamp,
                modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
              )
            }
          }
          Text(
            text = "Unsealed Court Database & Evidence Analysis",
            fontSize = 11.sp,
            color = SlateTextSecondary
          )
        }
      }

      IconButton(
        onClick = onOpenStats,
        modifier = Modifier
          .size(40.dp)
          .testTag("metrics_button")
      ) {
        Icon(
          imageVector = Icons.Default.Analytics,
          contentDescription = "Database Metrics",
          tint = GoldAccent
        )
      }
    }
  }
}

@Composable
private fun ArchiveBottomBar(
  currentTab: ArchiveTab,
  onTabSelected: (ArchiveTab) -> Unit,
  bookmarkCount: Int
) {
  NavigationBar(
    containerColor = ArchiveSurfaceDark,
    contentColor = SlateTextPrimary,
    tonalElevation = 8.dp,
    modifier = Modifier
      .windowInsetsPadding(WindowInsets.navigationBars)
      .background(ArchiveSurfaceDark)
      .testTag("bottom_nav_bar")
  ) {
    ArchiveTab.values().forEach { tab ->
      val selected = currentTab == tab
      val (iconSelected, iconUnselected) = when (tab) {
        ArchiveTab.DOCUMENTS -> Icons.Filled.Description to Icons.Outlined.Description
        ArchiveTab.ENTITIES -> Icons.Filled.People to Icons.Outlined.People
        ArchiveTab.FLIGHT_LOGS -> Icons.Filled.Flight to Icons.Outlined.Flight
        ArchiveTab.TIMELINE -> Icons.Filled.Timeline to Icons.Outlined.Timeline
        ArchiveTab.BOOKMARKS -> Icons.Filled.Bookmark to Icons.Outlined.BookmarkBorder
      }

      NavigationBarItem(
        selected = selected,
        onClick = { onTabSelected(tab) },
        icon = {
          Box {
            Icon(
              imageVector = if (selected) iconSelected else iconUnselected,
              contentDescription = tab.label
            )
            if (tab == ArchiveTab.BOOKMARKS && bookmarkCount > 0) {
              Surface(
                color = GoldAccent,
                shape = CircleShape,
                modifier = Modifier
                  .size(8.dp)
                  .align(Alignment.TopEnd)
              ) {}
            }
          }
        },
        label = {
          Text(
            text = when (tab) {
              ArchiveTab.DOCUMENTS -> "Docs"
              ArchiveTab.ENTITIES -> "Names"
              ArchiveTab.FLIGHT_LOGS -> "Flights"
              ArchiveTab.TIMELINE -> "Timeline"
              ArchiveTab.BOOKMARKS -> "Saved"
            },
            fontSize = 11.sp,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
          )
        },
        colors = NavigationBarItemDefaults.colors(
          selectedIconColor = ArchiveBgDark,
          selectedTextColor = GoldAccent,
          indicatorColor = GoldAccent,
          unselectedIconColor = SlateTextSecondary,
          unselectedTextColor = SlateTextTertiary
        ),
        modifier = Modifier.testTag("nav_item_${tab.name.lowercase()}")
      )
    }
  }
}
