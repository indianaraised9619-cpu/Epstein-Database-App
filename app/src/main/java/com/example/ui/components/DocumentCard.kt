package com.example.ui.components

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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.ArchiveDocument
import com.example.ui.theme.ArchiveBorder
import com.example.ui.theme.ArchiveSurfaceCard
import com.example.ui.theme.CategoryCourt
import com.example.ui.theme.CategoryCourtBg
import com.example.ui.theme.CategoryDeposition
import com.example.ui.theme.CategoryDepositionBg
import com.example.ui.theme.CategoryExhibit
import com.example.ui.theme.CategoryExhibitBg
import com.example.ui.theme.CategoryFlight
import com.example.ui.theme.CategoryFlightBg
import com.example.ui.theme.CategoryPolice
import com.example.ui.theme.CategoryPoliceBg
import com.example.ui.theme.CrimsonStamp
import com.example.ui.theme.CrimsonStampBg
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.SlateTextPrimary
import com.example.ui.theme.SlateTextSecondary
import com.example.ui.theme.SlateTextTertiary

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DocumentCard(
  document: ArchiveDocument,
  onClick: () -> Unit,
  onToggleBookmark: () -> Unit,
  onEntityClick: ((String) -> Unit)? = null,
  modifier: Modifier = Modifier
) {
  val (categoryColor, categoryBg) = when (document.category) {
    "Depositions" -> CategoryDeposition to CategoryDepositionBg
    "Flight Manifests" -> CategoryFlight to CategoryFlightBg
    "Police Reports" -> CategoryPolice to CategoryPoliceBg
    "Exhibits & Evidence" -> CategoryExhibit to CategoryExhibitBg
    else -> CategoryCourt to CategoryCourtBg
  }

  Card(
    modifier = modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(12.dp))
      .clickable(onClick = onClick)
      .testTag("document_card_${document.id}"),
    colors = CardDefaults.cardColors(containerColor = ArchiveSurfaceCard),
    border = BorderStroke(1.dp, ArchiveBorder),
    shape = RoundedCornerShape(12.dp)
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
    ) {
      // Header row: Doc Number badge, Unseal Stamp, Bookmark Icon
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          // Monospace Doc Tag
          Surface(
            color = ArchiveBorder,
            shape = RoundedCornerShape(4.dp)
          ) {
            Text(
              text = document.docNumber,
              fontFamily = FontFamily.Monospace,
              fontSize = 12.sp,
              fontWeight = FontWeight.Bold,
              color = GoldAccent,
              modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
            )
          }

          // Category Pill
          Surface(
            color = categoryBg,
            shape = RoundedCornerShape(4.dp)
          ) {
            Text(
              text = document.category,
              fontSize = 11.sp,
              fontWeight = FontWeight.SemiBold,
              color = categoryColor,
              modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
            )
          }
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
          // Unsealed stamp
          Surface(
            color = CrimsonStampBg,
            border = BorderStroke(0.5.dp, CrimsonStamp),
            shape = RoundedCornerShape(3.dp)
          ) {
            Text(
              text = "UNSEALED",
              fontSize = 10.sp,
              fontWeight = FontWeight.Black,
              color = CrimsonStamp,
              modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
            )
          }

          IconButton(
            onClick = onToggleBookmark,
            modifier = Modifier
              .size(36.dp)
              .testTag("bookmark_button_${document.id}")
          ) {
            Icon(
              imageVector = if (document.isBookmarked) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
              contentDescription = if (document.isBookmarked) "Bookmarked" else "Bookmark document",
              tint = if (document.isBookmarked) GoldAccent else SlateTextTertiary
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(8.dp))

      // Title
      Text(
        text = document.title,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = SlateTextPrimary,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
      )

      Spacer(modifier = Modifier.height(6.dp))

      // Summary preview
      Text(
        text = document.summary,
        style = MaterialTheme.typography.bodySmall,
        color = SlateTextSecondary,
        maxLines = 3,
        overflow = TextOverflow.Ellipsis,
        lineHeight = 18.sp
      )

      Spacer(modifier = Modifier.height(10.dp))

      // Entities mentioned chips
      val entitiesList = document.keyEntities.split(",").map { it.trim() }.filter { it.isNotEmpty() }
      if (entitiesList.isNotEmpty()) {
        FlowRow(
          horizontalArrangement = Arrangement.spacedBy(6.dp),
          verticalArrangement = Arrangement.spacedBy(4.dp),
          modifier = Modifier.fillMaxWidth()
        ) {
          entitiesList.take(4).forEach { entityName ->
            Surface(
              color = ArchiveBorder.copy(alpha = 0.6f),
              shape = RoundedCornerShape(12.dp),
              modifier = Modifier.clickable { onEntityClick?.invoke(entityName) }
            ) {
              Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
              ) {
                Icon(
                  imageVector = Icons.Default.Person,
                  contentDescription = null,
                  tint = GoldAccent.copy(alpha = 0.8f),
                  modifier = Modifier.size(11.dp)
                )
                Spacer(modifier = Modifier.width(3.dp))
                Text(
                  text = entityName,
                  fontSize = 11.sp,
                  color = SlateTextPrimary,
                  fontWeight = FontWeight.Medium
                )
              }
            }
          }
          if (entitiesList.size > 4) {
            Text(
              text = "+${entitiesList.size - 4} more",
              fontSize = 11.sp,
              color = SlateTextTertiary,
              modifier = Modifier.padding(vertical = 3.dp)
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(10.dp))

      // Footer metadata
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
          Icon(
            imageVector = Icons.Default.Description,
            contentDescription = null,
            tint = SlateTextTertiary,
            modifier = Modifier.size(13.dp)
          )
          Text(
            text = "${document.pageCount} pages • ${document.unsealBatch}",
            fontSize = 11.sp,
            color = SlateTextTertiary
          )
        }

        if (document.userNotes.isNotBlank()) {
          Surface(
            color = GoldAccent.copy(alpha = 0.15f),
            shape = RoundedCornerShape(4.dp)
          ) {
            Text(
              text = "Notes Added",
              fontSize = 10.sp,
              fontWeight = FontWeight.Bold,
              color = GoldAccent,
              modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
            )
          }
        }
      }
    }
  }
}
