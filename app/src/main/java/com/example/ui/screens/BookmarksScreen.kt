package com.example.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Note
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.ArchiveDocument
import com.example.ui.components.DocumentCard
import com.example.ui.theme.ArchiveBgDark
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.SlateTextPrimary
import com.example.ui.theme.SlateTextSecondary
import com.example.ui.theme.SlateTextTertiary

@Composable
fun BookmarksScreen(
  bookmarkedDocuments: List<ArchiveDocument>,
  onDocumentClick: (ArchiveDocument) -> Unit,
  onToggleBookmark: (ArchiveDocument) -> Unit,
  onExploreDocuments: () -> Unit,
  onEntityClick: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier
      .fillMaxSize()
      .background(ArchiveBgDark)
  ) {
    // Header
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        Icon(
          imageVector = Icons.Default.Bookmark,
          contentDescription = null,
          tint = GoldAccent,
          modifier = Modifier.size(20.dp)
        )
        Text(
          text = "SAVED RESEARCH & BOOKMARKED FILES",
          fontSize = 13.sp,
          fontWeight = FontWeight.Bold,
          color = GoldAccent,
          letterSpacing = 1.sp
        )
      }
      Spacer(modifier = Modifier.height(4.dp))
      Text(
        text = "${bookmarkedDocuments.size} files bookmarked for active research.",
        fontSize = 13.sp,
        color = SlateTextSecondary
      )
    }

    if (bookmarkedDocuments.isEmpty()) {
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
            imageVector = Icons.Default.BookmarkBorder,
            contentDescription = null,
            tint = SlateTextTertiary,
            modifier = Modifier.size(54.dp)
          )
          Spacer(modifier = Modifier.height(16.dp))
          Text(
            text = "No Bookmarked Records",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = SlateTextPrimary
          )
          Spacer(modifier = Modifier.height(8.dp))
          Text(
            text = "Tap the bookmark icon on any court document, deposition, or exhibit to save it here for fast retrieval and research analysis.",
            fontSize = 13.sp,
            color = SlateTextSecondary,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
            lineHeight = 18.sp
          )
          Spacer(modifier = Modifier.height(20.dp))
          Button(
            onClick = onExploreDocuments,
            colors = ButtonDefaults.buttonColors(
              containerColor = GoldAccent,
              contentColor = ArchiveBgDark
            ),
            shape = RoundedCornerShape(8.dp)
          ) {
            Text("Browse Archive Records", fontWeight = FontWeight.Bold)
          }
        }
      }
    } else {
      LazyColumn(
        modifier = Modifier
          .fillMaxSize()
          .testTag("bookmarked_documents_list"),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 80.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
      ) {
        items(
          items = bookmarkedDocuments,
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
}
