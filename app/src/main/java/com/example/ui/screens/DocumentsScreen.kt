package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.local.ArchiveDocument
import com.example.ui.components.DocumentCard
import com.example.ui.components.SearchBarWithFilters
import com.example.ui.theme.ArchiveBgDark
import com.example.ui.theme.ArchiveBorder
import com.example.ui.theme.ArchiveSurfaceCard
import com.example.ui.theme.ArchiveSurfaceDark
import com.example.ui.theme.ArchiveSurfaceElevated
import com.example.ui.theme.CrimsonStamp
import com.example.ui.theme.CrimsonStampBg
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.SlateTextPrimary
import com.example.ui.theme.SlateTextSecondary
import com.example.ui.theme.SlateTextTertiary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DocumentsScreen(
  documents: List<ArchiveDocument>,
  searchQuery: String,
  onSearchQueryChange: (String) -> Unit,
  selectedCategory: String,
  onCategorySelected: (String) -> Unit,
  selectedBatch: String,
  onBatchSelected: (String) -> Unit,
  onDocumentClick: (ArchiveDocument) -> Unit,
  onToggleBookmark: (ArchiveDocument) -> Unit,
  onEntityClick: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  var showBatchFilterSheet by remember { mutableStateOf(false) }

  val categories = listOf(
    "All",
    "Depositions",
    "Court Dockets",
    "Flight Manifests",
    "Police Reports",
    "Exhibits & Evidence",
    "Grand Jury & NPA"
  )

  val batches = listOf(
    "All",
    "Batch 1",
    "Batch 2",
    "Batch 3",
    "Batch 4",
    "Batch 5",
    "Batch 7",
    "Batch 8",
    "Pre-Release Order"
  )

  Column(
    modifier = modifier
      .fillMaxSize()
      .background(ArchiveBgDark)
  ) {
    // Header Hero Banner Card
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 8.dp),
      colors = CardDefaults.cardColors(containerColor = ArchiveSurfaceCard),
      shape = RoundedCornerShape(12.dp),
      border = androidx.compose.foundation.BorderStroke(1.dp, ArchiveBorder)
    ) {
      Box(modifier = Modifier.fillMaxWidth().height(110.dp)) {
        // Banner Image
        Image(
          painter = painterResource(id = R.drawable.archive_banner_1793154390616),
          contentDescription = "Archive Banner",
          contentScale = ContentScale.Crop,
          modifier = Modifier.fillMaxSize()
        )

        // Gradient Dark Overlay
        Box(
          modifier = Modifier
            .fillMaxSize()
            .background(
              androidx.compose.ui.graphics.Brush.horizontalGradient(
                colors = listOf(
                  ArchiveBgDark.copy(alpha = 0.92f),
                  ArchiveBgDark.copy(alpha = 0.70f),
                  ArchiveBgDark.copy(alpha = 0.40f)
                )
              )
            )
        )

        // Overlay text
        Column(
          modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 12.dp),
          verticalArrangement = Arrangement.Center
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
          ) {
            Surface(
              color = CrimsonStampBg,
              shape = RoundedCornerShape(3.dp),
              border = androidx.compose.foundation.BorderStroke(0.5.dp, CrimsonStamp)
            ) {
              Text(
                text = "SDNY CASE 15-CV-07433",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = CrimsonStamp,
                modifier = Modifier.padding(horizontal = 5.dp, vertical = 1.dp)
              )
            }
          }
          Spacer(modifier = Modifier.height(4.dp))
          Text(
            text = "Unsealed Court Records Database",
            fontSize = 17.sp,
            fontWeight = FontWeight.Black,
            color = SlateTextPrimary
          )
          Spacer(modifier = Modifier.height(2.dp))
          Text(
            text = "Full-text searchable depositions, flight logs & exhibits",
            fontSize = 11.sp,
            color = SlateTextSecondary
          )
        }
      }
    }

    // Search bar with Category Chips
    SearchBarWithFilters(
      query = searchQuery,
      onQueryChange = onSearchQueryChange,
      selectedCategory = selectedCategory,
      onCategorySelected = onCategorySelected,
      categories = categories,
      onFilterClick = { showBatchFilterSheet = true }
    )

    Spacer(modifier = Modifier.height(8.dp))

    // Results Sub-bar
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 4.dp),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
          text = "${documents.size} Unsealed Filings Found",
          fontSize = 12.sp,
          fontWeight = FontWeight.SemiBold,
          color = SlateTextSecondary
        )
        if (selectedBatch != "All") {
          Text(
            text = " • Batch: $selectedBatch",
            fontSize = 12.sp,
            color = GoldAccent
          )
        }
      }

      if (searchQuery.isNotEmpty() || selectedCategory != "All" || selectedBatch != "All") {
        Text(
          text = "Reset Filters",
          fontSize = 12.sp,
          fontWeight = FontWeight.Bold,
          color = GoldAccent,
          modifier = Modifier
            .clickable {
              onSearchQueryChange("")
              onCategorySelected("All")
              onBatchSelected("All")
            }
            .padding(vertical = 4.dp)
        )
      }
    }

    // Document List
    if (documents.isEmpty()) {
      Box(
        modifier = Modifier
          .fillMaxSize()
          .padding(32.dp),
        contentAlignment = Alignment.Center
      ) {
        Column(
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.Center
        ) {
          Icon(
            imageVector = Icons.Default.SearchOff,
            contentDescription = null,
            tint = SlateTextTertiary,
            modifier = Modifier.size(48.dp)
          )
          Spacer(modifier = Modifier.height(12.dp))
          Text(
            text = "No Documents Match Your Filters",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = SlateTextPrimary
          )
          Spacer(modifier = Modifier.height(6.dp))
          Text(
            text = "Try adjusting your search terms, removing keywords, or resetting category filters.",
            fontSize = 13.sp,
            color = SlateTextSecondary,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
          )
          Spacer(modifier = Modifier.height(16.dp))
          Button(
            onClick = {
              onSearchQueryChange("")
              onCategorySelected("All")
              onBatchSelected("All")
            },
            colors = ButtonDefaults.buttonColors(
              containerColor = GoldAccent,
              contentColor = ArchiveBgDark
            ),
            shape = RoundedCornerShape(8.dp)
          ) {
            Text("Clear All Filters", fontWeight = FontWeight.Bold)
          }
        }
      }
    } else {
      LazyColumn(
        modifier = Modifier
          .fillMaxSize()
          .testTag("documents_list"),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 80.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
      ) {
        items(
          items = documents,
          key = { it.id }
        ) { doc ->
          DocumentCard(
            document = doc,
            onClick = { onDocumentClick(doc) },
            onToggleBookmark = { onToggleBookmark(doc) },
            onEntityClick = onEntityClick
          )
        }
      }
    }
  }

  // Batch Filter Modal Sheet
  if (showBatchFilterSheet) {
    ModalBottomSheet(
      onDismissRequest = { showBatchFilterSheet = false },
      containerColor = ArchiveSurfaceDark,
      sheetState = rememberModalBottomSheetState()
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(20.dp)
      ) {
        Text(
          text = "FILTER BY UNSEALING BATCH",
          fontSize = 13.sp,
          fontWeight = FontWeight.Bold,
          color = GoldAccent,
          letterSpacing = 1.sp
        )
        Spacer(modifier = Modifier.height(12.dp))

        batches.forEach { batchName ->
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .clickable {
                onBatchSelected(batchName)
                showBatchFilterSheet = false
              }
              .padding(vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            RadioButton(
              selected = selectedBatch == batchName,
              onClick = {
                onBatchSelected(batchName)
                showBatchFilterSheet = false
              },
              colors = RadioButtonDefaults.colors(
                selectedColor = GoldAccent,
                unselectedColor = SlateTextTertiary
              )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
              text = batchName,
              fontSize = 14.sp,
              color = if (selectedBatch == batchName) GoldAccent else SlateTextPrimary,
              fontWeight = if (selectedBatch == batchName) FontWeight.Bold else FontWeight.Normal
            )
          }
        }
        Spacer(modifier = Modifier.height(24.dp))
      }
    }
  }
}
