package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.NamedEntity
import com.example.ui.theme.ArchiveBgDark
import com.example.ui.theme.ArchiveBorder
import com.example.ui.theme.ArchiveSurfaceCard
import com.example.ui.theme.ArchiveSurfaceDark
import com.example.ui.theme.ArchiveSurfaceElevated
import com.example.ui.theme.CategoryCourt
import com.example.ui.theme.CategoryCourtBg
import com.example.ui.theme.CategoryDeposition
import com.example.ui.theme.CategoryDepositionBg
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

@Composable
fun EntityCard(
  entity: NamedEntity,
  onClick: () -> Unit,
  onToggleFavorite: () -> Unit,
  onFilterDocuments: () -> Unit,
  modifier: Modifier = Modifier
) {
  val (roleColor, roleBg) = if (entity.roleCategory.contains("Plaintiff", ignoreCase = true)) {
    CategoryCourt to CategoryCourtBg
  } else if (entity.roleCategory.contains("Convicted", ignoreCase = true) || entity.roleCategory.contains("Subject", ignoreCase = true)) {
    CrimsonStamp to CrimsonStampBg
  } else if (entity.roleCategory.contains("Witness", ignoreCase = true)) {
    CategoryDeposition to CategoryDepositionBg
  } else if (entity.roleCategory.contains("Pilot", ignoreCase = true) || entity.roleCategory.contains("Staff", ignoreCase = true)) {
    CategoryFlight to CategoryFlightBg
  } else {
    GoldAccent to GoldAccent.copy(alpha = 0.15f)
  }

  Card(
    modifier = modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(12.dp))
      .clickable(onClick = onClick)
      .testTag("entity_card_${entity.id}"),
    colors = CardDefaults.cardColors(containerColor = ArchiveSurfaceCard),
    border = BorderStroke(1.dp, ArchiveBorder),
    shape = RoundedCornerShape(12.dp)
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        // Role pill
        Surface(
          color = roleBg,
          shape = RoundedCornerShape(4.dp)
        ) {
          Text(
            text = entity.roleCategory,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = roleColor,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
          )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
          Surface(
            color = ArchiveSurfaceElevated,
            shape = RoundedCornerShape(4.dp)
          ) {
            Text(
              text = "${entity.mentionCount} mentions",
              fontSize = 11.sp,
              fontWeight = FontWeight.SemiBold,
              color = SlateTextSecondary,
              modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
            )
          }

          IconButton(
            onClick = onToggleFavorite,
            modifier = Modifier.size(36.dp)
          ) {
            Icon(
              imageVector = if (entity.isFavorite) Icons.Filled.Star else Icons.Outlined.StarBorder,
              contentDescription = "Favorite",
              tint = if (entity.isFavorite) GoldAccent else SlateTextTertiary
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(8.dp))

      // Person Name
      Text(
        text = entity.name,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = SlateTextPrimary
      )

      Spacer(modifier = Modifier.height(6.dp))

      // Description
      Text(
        text = entity.description,
        style = MaterialTheme.typography.bodySmall,
        color = SlateTextSecondary,
        maxLines = 3,
        overflow = TextOverflow.Ellipsis,
        lineHeight = 18.sp
      )

      Spacer(modifier = Modifier.height(10.dp))

      // Quote snippet
      if (entity.notableExcerpts.isNotBlank()) {
        Surface(
          color = ArchiveSurfaceElevated.copy(alpha = 0.5f),
          border = BorderStroke(0.5.dp, ArchiveBorder),
          shape = RoundedCornerShape(6.dp)
        ) {
          Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.Top
          ) {
            Icon(
              imageVector = Icons.Default.FormatQuote,
              contentDescription = null,
              tint = GoldAccent.copy(alpha = 0.6f),
              modifier = Modifier.size(14.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
              text = entity.notableExcerpts,
              fontSize = 11.sp,
              color = SlateTextTertiary,
              maxLines = 2,
              overflow = TextOverflow.Ellipsis,
              lineHeight = 16.sp
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(12.dp))

      // Bottom Actions
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        val docCount = entity.keyDocumentIds.split(",").filter { it.isNotBlank() }.size
        Text(
          text = "$docCount cross-referenced files",
          fontSize = 11.sp,
          color = CategoryCourt
        )

        Row(
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier.clickable { onFilterDocuments() }
        ) {
          Text(
            text = "View Documents",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = GoldAccent
          )
          Spacer(modifier = Modifier.width(4.dp))
          Icon(
            imageVector = Icons.Default.ArrowForward,
            contentDescription = null,
            tint = GoldAccent,
            modifier = Modifier.size(14.dp)
          )
        }
      }
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun EntityDetailSheet(
  entity: NamedEntity,
  onDismiss: () -> Unit,
  onOpenDocument: (String) -> Unit,
  onFilterAllDocs: () -> Unit
) {
  val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
  val scrollState = rememberScrollState()

  ModalBottomSheet(
    onDismissRequest = onDismiss,
    sheetState = sheetState,
    containerColor = ArchiveSurfaceDark,
    dragHandle = null,
    modifier = Modifier.testTag("entity_detail_sheet")
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(0.88f)
    ) {
      // Header
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
            color = GoldAccent.copy(alpha = 0.2f),
            shape = RoundedCornerShape(4.dp)
          ) {
            Text(
              text = entity.roleCategory,
              fontSize = 12.sp,
              fontWeight = FontWeight.Bold,
              color = GoldAccent,
              modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
            )
          }
          Text(
            text = "${entity.mentionCount} Mentions in Files",
            fontSize = 12.sp,
            color = SlateTextSecondary
          )
        }

        IconButton(
          onClick = onDismiss,
          modifier = Modifier.size(36.dp)
        ) {
          Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "Close",
            tint = SlateTextPrimary
          )
        }
      }

      // Title
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .background(ArchiveSurfaceCard)
          .padding(horizontal = 16.dp, vertical = 8.dp)
      ) {
        Text(
          text = entity.name,
          fontSize = 20.sp,
          fontWeight = FontWeight.Bold,
          color = SlateTextPrimary
        )
      }

      // Body
      Column(
        modifier = Modifier
          .fillMaxSize()
          .verticalScroll(scrollState)
          .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
      ) {
        // Legal Context Disclaimer
        Card(
          colors = CardDefaults.cardColors(containerColor = ArchiveSurfaceCard),
          border = BorderStroke(1.dp, ArchiveBorder),
          shape = RoundedCornerShape(8.dp)
        ) {
          Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            Icon(
              imageVector = Icons.Default.Info,
              contentDescription = null,
              tint = CategoryCourt,
              modifier = Modifier.size(18.dp)
            )
            Text(
              text = entity.legalContextDisclaimer,
              fontSize = 12.sp,
              color = SlateTextSecondary,
              lineHeight = 18.sp
            )
          }
        }

        // Background & Overview
        Card(
          colors = CardDefaults.cardColors(containerColor = ArchiveSurfaceCard),
          border = BorderStroke(1.dp, ArchiveBorder),
          shape = RoundedCornerShape(8.dp)
        ) {
          Column(modifier = Modifier.padding(14.dp)) {
            Text(
              text = "ROLE & FACTUAL CONTEXT",
              fontSize = 11.sp,
              fontWeight = FontWeight.Bold,
              color = GoldAccent,
              letterSpacing = 1.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
              text = entity.description,
              fontSize = 14.sp,
              color = SlateTextPrimary,
              lineHeight = 22.sp
            )
          }
        }

        // Notable Testimony Excerpts
        if (entity.notableExcerpts.isNotBlank()) {
          Card(
            colors = CardDefaults.cardColors(containerColor = ArchiveSurfaceCard),
            border = BorderStroke(1.dp, ArchiveBorder),
            shape = RoundedCornerShape(8.dp)
          ) {
            Column(modifier = Modifier.padding(14.dp)) {
              Text(
                text = "SWORN RECORD EXCERPTS",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = CategoryDeposition,
                letterSpacing = 1.sp
              )
              Spacer(modifier = Modifier.height(8.dp))
              Text(
                text = entity.notableExcerpts,
                fontSize = 13.sp,
                fontFamily = FontFamily.Monospace,
                color = SlateTextPrimary,
                lineHeight = 20.sp
              )
            }
          }
        }

        // Linked Documents
        Card(
          colors = CardDefaults.cardColors(containerColor = ArchiveSurfaceCard),
          border = BorderStroke(1.dp, ArchiveBorder),
          shape = RoundedCornerShape(8.dp)
        ) {
          Column(modifier = Modifier.padding(14.dp)) {
            Text(
              text = "CROSS-REFERENCED COURT DOCUMENTS",
              fontSize = 11.sp,
              fontWeight = FontWeight.Bold,
              color = CategoryCourt,
              letterSpacing = 1.sp
            )
            Spacer(modifier = Modifier.height(10.dp))

            val docIds = entity.keyDocumentIds.split(",").filter { it.isNotBlank() }
            FlowRow(
              horizontalArrangement = Arrangement.spacedBy(8.dp),
              verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              docIds.forEach { docId ->
                Surface(
                  color = ArchiveSurfaceElevated,
                  border = BorderStroke(1.dp, ArchiveBorder),
                  shape = RoundedCornerShape(6.dp),
                  modifier = Modifier.clickable {
                    onOpenDocument(docId.trim())
                    onDismiss()
                  }
                ) {
                  Row(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
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
                      text = docId.trim(),
                      fontFamily = FontFamily.Monospace,
                      fontSize = 12.sp,
                      fontWeight = FontWeight.Bold,
                      color = SlateTextPrimary
                    )
                  }
                }
              }
            }
          }
        }

        // Button to filter whole database by this person
        Button(
          onClick = {
            onFilterAllDocs()
            onDismiss()
          },
          colors = ButtonDefaults.buttonColors(
            containerColor = GoldAccent,
            contentColor = ArchiveBgDark
          ),
          shape = RoundedCornerShape(8.dp),
          modifier = Modifier
            .fillMaxWidth()
            .testTag("filter_all_by_entity_button")
        ) {
          Icon(imageVector = Icons.Default.Search, contentDescription = null, modifier = Modifier.size(18.dp))
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = "Search All Archive Files for ${entity.name}",
            fontWeight = FontWeight.Bold
          )
        }

        Spacer(modifier = Modifier.height(20.dp))
      }
    }
  }
}
