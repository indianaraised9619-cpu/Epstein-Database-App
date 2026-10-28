package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.TimelineMilestone
import com.example.ui.theme.ArchiveBgDark
import com.example.ui.theme.ArchiveBorder
import com.example.ui.theme.ArchiveSurfaceCard
import com.example.ui.theme.ArchiveSurfaceElevated
import com.example.ui.theme.CategoryCourt
import com.example.ui.theme.CategoryCourtBg
import com.example.ui.theme.CategoryDeposition
import com.example.ui.theme.CategoryDepositionBg
import com.example.ui.theme.CategoryPolice
import com.example.ui.theme.CategoryPoliceBg
import com.example.ui.theme.CrimsonStamp
import com.example.ui.theme.CrimsonStampBg
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.SlateTextPrimary
import com.example.ui.theme.SlateTextSecondary
import com.example.ui.theme.SlateTextTertiary

@Composable
fun TimelineScreen(
  timeline: List<TimelineMilestone>,
  onOpenDocument: (String) -> Unit,
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
          imageVector = Icons.Default.Timeline,
          contentDescription = null,
          tint = GoldAccent,
          modifier = Modifier.size(20.dp)
        )
        Text(
          text = "INVESTIGATIVE & UNSEALING TIMELINE",
          fontSize = 13.sp,
          fontWeight = FontWeight.Bold,
          color = GoldAccent,
          letterSpacing = 1.sp
        )
      }
      Spacer(modifier = Modifier.height(4.dp))
      Text(
        text = "Chronology of events from the 2005 Palm Beach raid to the 2024 unsealed SDNY court releases.",
        fontSize = 13.sp,
        color = SlateTextSecondary,
        lineHeight = 18.sp
      )
    }

    LazyColumn(
      modifier = Modifier
        .fillMaxSize()
        .testTag("timeline_list"),
      contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 80.dp)
    ) {
      itemsIndexed(
        items = timeline,
        key = { _, item -> item.id }
      ) { index, item ->
        TimelineItemRow(
          item = item,
          isLast = index == timeline.lastIndex,
          onOpenDocument = onOpenDocument
        )
      }
    }
  }
}

@Composable
private fun TimelineItemRow(
  item: TimelineMilestone,
  isLast: Boolean,
  onOpenDocument: (String) -> Unit
) {
  val (categoryColor, categoryBg) = when (item.category) {
    "Investigation" -> CategoryPolice to CategoryPoliceBg
    "Criminal Trial" -> CrimsonStamp to CrimsonStampBg
    "Unsealing Order" -> GoldAccent to GoldAccent.copy(alpha = 0.15f)
    else -> CategoryCourt to CategoryCourtBg
  }

  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(bottom = if (isLast) 0.dp else 16.dp)
  ) {
    // Left timeline indicator (node + vertical line)
    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      modifier = Modifier.width(36.dp)
    ) {
      Box(
        modifier = Modifier
          .size(16.dp)
          .background(categoryColor, CircleShape),
        contentAlignment = Alignment.Center
      ) {
        Box(
          modifier = Modifier
            .size(6.dp)
            .background(ArchiveBgDark, CircleShape)
        )
      }

      if (!isLast) {
        Box(
          modifier = Modifier
            .width(2.dp)
            .height(180.dp)
            .background(ArchiveBorder)
        )
      }
    }

    Spacer(modifier = Modifier.width(8.dp))

    // Card Content
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .testTag("timeline_item_${item.id}"),
      colors = CardDefaults.cardColors(containerColor = ArchiveSurfaceCard),
      border = BorderStroke(1.dp, ArchiveBorder),
      shape = RoundedCornerShape(12.dp)
    ) {
      Column(modifier = Modifier.padding(14.dp)) {
        // Date & Category Badge
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(
            text = item.date,
            fontFamily = FontFamily.Monospace,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = GoldAccent
          )

          Surface(
            color = categoryBg,
            shape = RoundedCornerShape(4.dp)
          ) {
            Text(
              text = item.category,
              fontSize = 10.sp,
              fontWeight = FontWeight.Bold,
              color = categoryColor,
              modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
            )
          }
        }

        Spacer(modifier = Modifier.height(6.dp))

        // Title
        Text(
          text = item.title,
          fontSize = 15.sp,
          fontWeight = FontWeight.Bold,
          color = SlateTextPrimary
        )

        Spacer(modifier = Modifier.height(6.dp))

        // Description
        Text(
          text = item.description,
          fontSize = 13.sp,
          color = SlateTextSecondary,
          lineHeight = 18.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Significance note
        Surface(
          color = ArchiveSurfaceElevated,
          shape = RoundedCornerShape(6.dp),
          border = BorderStroke(0.5.dp, ArchiveBorder)
        ) {
          Text(
            text = "Significance: ${item.significance}",
            fontSize = 11.sp,
            color = SlateTextTertiary,
            lineHeight = 15.sp,
            modifier = Modifier.padding(8.dp)
          )
        }

        // Link to court document if available
        if (item.relatedDocId != null) {
          Spacer(modifier = Modifier.height(10.dp))
          Row(
            modifier = Modifier
              .clickable { onOpenDocument(item.relatedDocId) }
              .padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Icon(
              imageVector = Icons.Default.Description,
              contentDescription = null,
              tint = GoldAccent,
              modifier = Modifier.size(14.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text = "Open Court Record (${item.relatedDocId})",
              fontFamily = FontFamily.Monospace,
              fontSize = 12.sp,
              fontWeight = FontWeight.Bold,
              color = GoldAccent
            )
          }
        }
      }
    }
  }
}
