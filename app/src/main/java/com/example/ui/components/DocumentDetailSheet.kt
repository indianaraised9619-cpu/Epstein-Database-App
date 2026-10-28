package com.example.ui.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.FormatSize
import androidx.compose.material.icons.filled.Gavel
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.ArchiveDocument
import com.example.ui.theme.ArchiveBgDark
import com.example.ui.theme.ArchiveBorder
import com.example.ui.theme.ArchiveSurfaceCard
import com.example.ui.theme.ArchiveSurfaceDark
import com.example.ui.theme.ArchiveSurfaceElevated
import com.example.ui.theme.CategoryCourt
import com.example.ui.theme.CategoryCourtBg
import com.example.ui.theme.CategoryDeposition
import com.example.ui.theme.CrimsonStamp
import com.example.ui.theme.CrimsonStampBg
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.GoldAccentLight
import com.example.ui.theme.GoldMuted
import com.example.ui.theme.HighlightYellow
import com.example.ui.theme.SlateTextPrimary
import com.example.ui.theme.SlateTextSecondary
import com.example.ui.theme.SlateTextTertiary

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun DocumentDetailSheet(
  document: ArchiveDocument,
  onDismiss: () -> Unit,
  onToggleBookmark: () -> Unit,
  onSaveNotes: (String) -> Unit,
  onEntityClick: ((String) -> Unit)? = null
) {
  val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
  val context = LocalContext.current
  var selectedTab by remember { mutableIntStateOf(0) } // 0: Analysis, 1: Excerpt Text, 2: Research Notes
  var inTextSearchQuery by remember { mutableStateOf("") }
  var fontSizeSp by remember { mutableFloatStateOf(14f) }
  var userNotesText by remember(document.userNotes) { mutableStateOf(document.userNotes) }
  var notesSavedConfirmation by remember { mutableStateOf(false) }

  ModalBottomSheet(
    onDismissRequest = onDismiss,
    sheetState = sheetState,
    containerColor = ArchiveSurfaceDark,
    dragHandle = null,
    modifier = Modifier.testTag("document_detail_sheet")
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(0.92f)
    ) {
      // Top header bar
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .background(ArchiveSurfaceCard)
          .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          Surface(
            color = ArchiveBorder,
            shape = RoundedCornerShape(4.dp)
          ) {
            Text(
              text = document.docNumber,
              fontFamily = FontFamily.Monospace,
              fontSize = 13.sp,
              fontWeight = FontWeight.Bold,
              color = GoldAccent,
              modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
            )
          }

          Surface(
            color = CrimsonStampBg,
            border = BorderStroke(0.5.dp, CrimsonStamp),
            shape = RoundedCornerShape(3.dp)
          ) {
            Text(
              text = "UNSEALED",
              fontSize = 11.sp,
              fontWeight = FontWeight.Black,
              color = CrimsonStamp,
              modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
            )
          }
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
          IconButton(
            onClick = onToggleBookmark,
            modifier = Modifier.size(40.dp)
          ) {
            Icon(
              imageVector = if (document.isBookmarked) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
              contentDescription = "Bookmark",
              tint = if (document.isBookmarked) GoldAccent else SlateTextSecondary
            )
          }
          IconButton(
            onClick = onDismiss,
            modifier = Modifier
              .size(40.dp)
              .testTag("close_sheet_button")
          ) {
            Icon(
              imageVector = Icons.Default.Close,
              contentDescription = "Close",
              tint = SlateTextPrimary
            )
          }
        }
      }

      // Title & Case Banner
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .background(ArchiveSurfaceCard)
          .padding(horizontal = 16.dp, vertical = 8.dp)
      ) {
        Text(
          text = document.title,
          fontSize = 18.sp,
          fontWeight = FontWeight.Bold,
          color = SlateTextPrimary,
          lineHeight = 24.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
          text = "${document.courtCase} • ${document.unsealBatch}",
          fontSize = 12.sp,
          color = SlateTextSecondary
        )
      }

      // Sub-Tabs
      PrimaryTabRow(
        selectedTabIndex = selectedTab,
        containerColor = ArchiveSurfaceCard,
        contentColor = GoldAccent,
        divider = {
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .height(1.dp)
              .background(ArchiveBorder)
          )
        }
      ) {
        Tab(
          selected = selectedTab == 0,
          onClick = { selectedTab = 0 },
          text = {
            Text(
              text = "Document Analysis",
              fontSize = 13.sp,
              fontWeight = if (selectedTab == 0) FontWeight.Bold else FontWeight.Normal,
              color = if (selectedTab == 0) GoldAccent else SlateTextSecondary
            )
          }
        )
        Tab(
          selected = selectedTab == 1,
          onClick = { selectedTab = 1 },
          text = {
            Text(
              text = "Full Excerpt",
              fontSize = 13.sp,
              fontWeight = if (selectedTab == 1) FontWeight.Bold else FontWeight.Normal,
              color = if (selectedTab == 1) GoldAccent else SlateTextSecondary
            )
          }
        )
        Tab(
          selected = selectedTab == 2,
          onClick = { selectedTab = 2 },
          text = {
            Text(
              text = if (document.userNotes.isNotBlank()) "Research Notes (1)" else "Research Notes",
              fontSize = 13.sp,
              fontWeight = if (selectedTab == 2) FontWeight.Bold else FontWeight.Normal,
              color = if (selectedTab == 2) GoldAccent else SlateTextSecondary
            )
          }
        )
      }

      // Tab Content Body
      when (selectedTab) {
        0 -> DocumentAnalysisTab(
          document = document,
          onEntityClick = onEntityClick
        )
        1 -> FullExcerptTab(
          document = document,
          inTextSearchQuery = inTextSearchQuery,
          onQueryChange = { inTextSearchQuery = it },
          fontSizeSp = fontSizeSp,
          onFontSizeChange = { fontSizeSp = it },
          onCopyText = {
            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("Document Excerpt", document.fullExcerpt)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(context, "Excerpt copied to clipboard", Toast.LENGTH_SHORT).show()
          }
        )
        2 -> ResearchNotesTab(
          notes = userNotesText,
          onNotesChange = {
            userNotesText = it
            notesSavedConfirmation = false
          },
          onSave = {
            onSaveNotes(userNotesText)
            notesSavedConfirmation = true
            Toast.makeText(context, "Notes saved successfully", Toast.LENGTH_SHORT).show()
          },
          savedConfirmation = notesSavedConfirmation
        )
      }
    }
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun DocumentAnalysisTab(
  document: ArchiveDocument,
  onEntityClick: ((String) -> Unit)?
) {
  val scrollState = rememberScrollState()

  Column(
    modifier = Modifier
      .fillMaxSize()
      .verticalScroll(scrollState)
      .padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    // 1. Executive Summary
    Card(
      colors = CardDefaults.cardColors(containerColor = ArchiveSurfaceCard),
      border = BorderStroke(1.dp, ArchiveBorder),
      shape = RoundedCornerShape(10.dp)
    ) {
      Column(modifier = Modifier.padding(14.dp)) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          Icon(
            imageVector = Icons.Default.Description,
            contentDescription = null,
            tint = GoldAccent,
            modifier = Modifier.size(16.dp)
          )
          Text(
            text = "EXECUTIVE SUMMARY",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = GoldAccent,
            letterSpacing = 1.sp
          )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
          text = document.summary,
          fontSize = 14.sp,
          color = SlateTextPrimary,
          lineHeight = 22.sp
        )
      }
    }

    // 2. Key Entities Detected
    Card(
      colors = CardDefaults.cardColors(containerColor = ArchiveSurfaceCard),
      border = BorderStroke(1.dp, ArchiveBorder),
      shape = RoundedCornerShape(10.dp)
    ) {
      Column(modifier = Modifier.padding(14.dp)) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          Icon(
            imageVector = Icons.Default.Person,
            contentDescription = null,
            tint = CategoryCourt,
            modifier = Modifier.size(16.dp)
          )
          Text(
            text = "KEY FIGURES & ENTITIES MENTIONED",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = CategoryCourt,
            letterSpacing = 1.sp
          )
        }
        Spacer(modifier = Modifier.height(10.dp))

        val entitiesList = document.keyEntities.split(",").map { it.trim() }.filter { it.isNotEmpty() }
        FlowRow(
          horizontalArrangement = Arrangement.spacedBy(8.dp),
          verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          entitiesList.forEach { entityName ->
            Surface(
              color = ArchiveSurfaceElevated,
              shape = RoundedCornerShape(20.dp),
              border = BorderStroke(1.dp, ArchiveBorder),
              modifier = Modifier
                .clickable { onEntityClick?.invoke(entityName) }
                .testTag("entity_mention_$entityName")
            ) {
              Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
              ) {
                Icon(
                  imageVector = Icons.Default.Person,
                  contentDescription = null,
                  tint = GoldAccent,
                  modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                  text = entityName,
                  fontSize = 12.sp,
                  fontWeight = FontWeight.SemiBold,
                  color = SlateTextPrimary
                )
              }
            }
          }
        }
      }
    }

    // 3. Legal & Judicial Significance
    Card(
      colors = CardDefaults.cardColors(containerColor = ArchiveSurfaceCard),
      border = BorderStroke(1.dp, ArchiveBorder),
      shape = RoundedCornerShape(10.dp)
    ) {
      Column(modifier = Modifier.padding(14.dp)) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          Icon(
            imageVector = Icons.Default.Gavel,
            contentDescription = null,
            tint = CategoryDeposition,
            modifier = Modifier.size(16.dp)
          )
          Text(
            text = "JUDICIAL SIGNIFICANCE & ANALYSIS",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = CategoryDeposition,
            letterSpacing = 1.sp
          )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
          text = document.legalSignificance,
          fontSize = 13.sp,
          color = SlateTextPrimary,
          lineHeight = 20.sp
        )
      }
    }

    // 4. Redaction & Doe Analysis
    Card(
      colors = CardDefaults.cardColors(containerColor = ArchiveSurfaceCard),
      border = BorderStroke(1.dp, ArchiveBorder),
      shape = RoundedCornerShape(10.dp)
    ) {
      Column(modifier = Modifier.padding(14.dp)) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          Icon(
            imageVector = Icons.Default.LockOpen,
            contentDescription = null,
            tint = CrimsonStamp,
            modifier = Modifier.size(16.dp)
          )
          Text(
            text = "REDACTION & PROTECTIVE ORDER STATUS",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = CrimsonStamp,
            letterSpacing = 1.sp
          )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
          text = document.redactionNotes,
          fontSize = 13.sp,
          color = SlateTextSecondary,
          lineHeight = 19.sp
        )
      }
    }

    // 5. Locations & Keyword Tags
    Card(
      colors = CardDefaults.cardColors(containerColor = ArchiveSurfaceCard),
      border = BorderStroke(1.dp, ArchiveBorder),
      shape = RoundedCornerShape(10.dp)
    ) {
      Column(modifier = Modifier.padding(14.dp)) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          Icon(
            imageVector = Icons.Default.LocationOn,
            contentDescription = null,
            tint = GoldAccent,
            modifier = Modifier.size(16.dp)
          )
          Text(
            text = "GEOGRAPHIC FOOTPRINT & KEYWORDS",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = GoldAccent,
            letterSpacing = 1.sp
          )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
          text = "Locations: ${document.locations}",
          fontSize = 12.sp,
          color = SlateTextSecondary
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
          text = "Keyword Tags: ${document.keywordTags}",
          fontSize = 12.sp,
          color = SlateTextTertiary
        )
      }
    }

    Spacer(modifier = Modifier.height(24.dp))
  }
}

@Composable
private fun FullExcerptTab(
  document: ArchiveDocument,
  inTextSearchQuery: String,
  onQueryChange: (String) -> Unit,
  fontSizeSp: Float,
  onFontSizeChange: (Float) -> Unit,
  onCopyText: () -> Unit
) {
  val scrollState = rememberScrollState()

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp)
  ) {
    // Top Controls: in-text search bar and font scale / copy tools
    Row(
      modifier = Modifier.fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      OutlinedTextField(
        value = inTextSearchQuery,
        onValueChange = onQueryChange,
        placeholder = { Text("Highlight text in transcript...", fontSize = 12.sp, color = SlateTextTertiary) },
        leadingIcon = {
          Icon(
            imageVector = Icons.Default.Search,
            contentDescription = null,
            tint = GoldAccent,
            modifier = Modifier.size(18.dp)
          )
        },
        singleLine = true,
        modifier = Modifier
          .weight(1f)
          .testTag("in_text_search_input"),
        shape = RoundedCornerShape(8.dp),
        colors = OutlinedTextFieldDefaults.colors(
          focusedContainerColor = ArchiveSurfaceCard,
          unfocusedContainerColor = ArchiveSurfaceDark,
          focusedBorderColor = GoldAccent,
          unfocusedBorderColor = ArchiveBorder,
          focusedTextColor = SlateTextPrimary,
          unfocusedTextColor = SlateTextPrimary
        )
      )

      // Font size buttons
      Surface(
        color = ArchiveSurfaceCard,
        border = BorderStroke(1.dp, ArchiveBorder),
        shape = RoundedCornerShape(8.dp)
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          IconButton(
            onClick = { if (fontSizeSp > 11f) onFontSizeChange(fontSizeSp - 2f) },
            modifier = Modifier.size(36.dp)
          ) {
            Text("A-", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = SlateTextPrimary)
          }
          IconButton(
            onClick = { if (fontSizeSp < 22f) onFontSizeChange(fontSizeSp + 2f) },
            modifier = Modifier.size(36.dp)
          ) {
            Text("A+", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = GoldAccent)
          }
        }
      }

      // Copy button
      IconButton(
        onClick = onCopyText,
        modifier = Modifier
          .size(40.dp)
          .background(ArchiveSurfaceCard, RoundedCornerShape(8.dp))
          .testTag("copy_excerpt_button")
      ) {
        Icon(
          imageVector = Icons.Default.ContentCopy,
          contentDescription = "Copy Excerpt",
          tint = SlateTextPrimary,
          modifier = Modifier.size(18.dp)
        )
      }
    }

    Spacer(modifier = Modifier.height(12.dp))

    // Text content box
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .weight(1f),
      colors = CardDefaults.cardColors(containerColor = ArchiveBgDark),
      border = BorderStroke(1.dp, ArchiveBorder),
      shape = RoundedCornerShape(8.dp)
    ) {
      Column(
        modifier = Modifier
          .fillMaxSize()
          .verticalScroll(scrollState)
          .padding(16.dp)
      ) {
        // Render text with highlight for query
        val annotatedText = buildAnnotatedStringWithHighlight(
          fullText = document.fullExcerpt,
          query = inTextSearchQuery
        )

        Text(
          text = annotatedText,
          fontSize = fontSizeSp.sp,
          fontFamily = FontFamily.Monospace,
          lineHeight = (fontSizeSp * 1.55f).sp,
          color = SlateTextPrimary
        )
      }
    }
  }
}

@Composable
private fun buildAnnotatedStringWithHighlight(
  fullText: String,
  query: String
): AnnotatedString {
  if (query.isBlank()) {
    return AnnotatedString(fullText)
  }

  return buildAnnotatedString {
    var startIndex = 0
    val lowerText = fullText.lowercase()
    val lowerQuery = query.lowercase()

    while (startIndex < fullText.length) {
      val foundIndex = lowerText.indexOf(lowerQuery, startIndex)
      if (foundIndex == -1) {
        append(fullText.substring(startIndex))
        break
      } else {
        append(fullText.substring(startIndex, foundIndex))
        pushStyle(
          SpanStyle(
            background = HighlightYellow,
            color = Color.Black,
            fontWeight = FontWeight.Bold
          )
        )
        append(fullText.substring(foundIndex, foundIndex + query.length))
        pop()
        startIndex = foundIndex + query.length
      }
    }
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ResearchNotesTab(
  notes: String,
  onNotesChange: (String) -> Unit,
  onSave: () -> Unit,
  savedConfirmation: Boolean
) {
  val scrollState = rememberScrollState()

  Column(
    modifier = Modifier
      .fillMaxSize()
      .verticalScroll(scrollState)
      .padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    Text(
      text = "RESEARCH & INVESTIGATIVE NOTES",
      fontSize = 12.sp,
      fontWeight = FontWeight.Bold,
      color = GoldAccent,
      letterSpacing = 1.sp
    )

    Text(
      text = "Record annotations, cross-references, or points of interest for this document. Notes are saved locally and synced with your database.",
      fontSize = 13.sp,
      color = SlateTextSecondary,
      lineHeight = 18.sp
    )

    // Quick tag pills to add to notes
    val quickTags = listOf(
      "Corroborates Sjoberg testimony",
      "Matches Flight Manifest",
      "Mention of Prince Andrew",
      "Unsealed under Preska Dec Order",
      "Key Exhibit for Timeline"
    )

    FlowRow(
      horizontalArrangement = Arrangement.spacedBy(6.dp),
      verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
      quickTags.forEach { tag ->
        Surface(
          color = ArchiveSurfaceCard,
          border = BorderStroke(1.dp, ArchiveBorder),
          shape = RoundedCornerShape(16.dp),
          modifier = Modifier.clickable {
            val updated = if (notes.isBlank()) "• $tag\n" else "$notes\n• $tag"
            onNotesChange(updated)
          }
        ) {
          Text(
            text = "+ $tag",
            fontSize = 11.sp,
            color = SlateTextSecondary,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
          )
        }
      }
    }

    OutlinedTextField(
      value = notes,
      onValueChange = onNotesChange,
      modifier = Modifier
        .fillMaxWidth()
        .height(180.dp)
        .testTag("research_notes_input"),
      placeholder = { Text("Write your research notes, findings, or questions...", color = SlateTextTertiary) },
      shape = RoundedCornerShape(10.dp),
      colors = OutlinedTextFieldDefaults.colors(
        focusedContainerColor = ArchiveSurfaceCard,
        unfocusedContainerColor = ArchiveSurfaceDark,
        focusedBorderColor = GoldAccent,
        unfocusedBorderColor = ArchiveBorder,
        focusedTextColor = SlateTextPrimary,
        unfocusedTextColor = SlateTextPrimary,
        cursorColor = GoldAccent
      )
    )

    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      if (savedConfirmation) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
          Icon(
            imageVector = Icons.Default.Check,
            contentDescription = null,
            tint = CategoryCourt,
            modifier = Modifier.size(16.dp)
          )
          Text(
            text = "Saved to Archive Database",
            fontSize = 12.sp,
            color = CategoryCourt,
            fontWeight = FontWeight.Medium
          )
        }
      } else {
        Spacer(modifier = Modifier.width(8.dp))
      }

      Button(
        onClick = onSave,
        colors = ButtonDefaults.buttonColors(
          containerColor = GoldAccent,
          contentColor = ArchiveBgDark
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.testTag("save_notes_button")
      ) {
        Icon(
          imageVector = Icons.Default.Save,
          contentDescription = null,
          modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text("Save Notes", fontWeight = FontWeight.Bold)
      }
    }
  }
}
