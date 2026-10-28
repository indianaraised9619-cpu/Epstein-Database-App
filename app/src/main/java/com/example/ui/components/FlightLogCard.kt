package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Flight
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.FlightLogEntry
import com.example.ui.theme.ArchiveBorder
import com.example.ui.theme.ArchiveSurfaceCard
import com.example.ui.theme.ArchiveSurfaceElevated
import com.example.ui.theme.CategoryFlight
import com.example.ui.theme.CategoryFlightBg
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.SlateTextPrimary
import com.example.ui.theme.SlateTextSecondary
import com.example.ui.theme.SlateTextTertiary

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FlightLogCard(
  log: FlightLogEntry,
  onToggleBookmark: () -> Unit,
  onOpenSourceDoc: (String) -> Unit,
  onPassengerClick: ((String) -> Unit)? = null,
  modifier: Modifier = Modifier
) {
  Card(
    modifier = modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(12.dp))
      .testTag("flight_log_card_${log.id}"),
    colors = CardDefaults.cardColors(containerColor = ArchiveSurfaceCard),
    border = BorderStroke(1.dp, ArchiveBorder),
    shape = RoundedCornerShape(12.dp)
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
    ) {
      // Header: Date, Tail number pill, Bookmark
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          Surface(
            color = CategoryFlightBg,
            shape = RoundedCornerShape(4.dp)
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
              Icon(
                imageVector = Icons.Default.Flight,
                contentDescription = null,
                tint = CategoryFlight,
                modifier = Modifier.size(12.dp)
              )
              Spacer(modifier = Modifier.width(4.dp))
              Text(
                text = "${log.tailNumber} (${log.aircraftType})",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = CategoryFlight
              )
            }
          }

          Text(
            text = log.date,
            fontFamily = FontFamily.Monospace,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = GoldAccent
          )
        }

        IconButton(
          onClick = onToggleBookmark,
          modifier = Modifier.size(36.dp)
        ) {
          Icon(
            imageVector = if (log.isBookmarked) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
            contentDescription = "Bookmark Flight",
            tint = if (log.isBookmarked) GoldAccent else SlateTextTertiary
          )
        }
      }

      Spacer(modifier = Modifier.height(10.dp))

      // Route Display: Origin -> Destination
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        Surface(
          color = ArchiveSurfaceElevated,
          shape = RoundedCornerShape(6.dp)
        ) {
          Text(
            text = log.origin,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = SlateTextPrimary,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
          )
        }

        Icon(
          imageVector = Icons.AutoMirrored.Filled.ArrowForward,
          contentDescription = "to",
          tint = GoldAccent,
          modifier = Modifier.size(16.dp)
        )

        Surface(
          color = ArchiveSurfaceElevated,
          shape = RoundedCornerShape(6.dp)
        ) {
          Text(
            text = log.destination,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = SlateTextPrimary,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
          )
        }
      }

      Spacer(modifier = Modifier.height(12.dp))

      // Passengers list
      Text(
        text = "PASSENGERS LOGGED",
        fontSize = 10.sp,
        fontWeight = FontWeight.Bold,
        color = SlateTextTertiary,
        letterSpacing = 1.sp
      )
      Spacer(modifier = Modifier.height(4.dp))

      val passengersList = log.passengers.split(",").map { it.trim() }.filter { it.isNotEmpty() }
      FlowRow(
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.fillMaxWidth()
      ) {
        passengersList.forEach { passenger ->
          Surface(
            color = ArchiveBorder.copy(alpha = 0.5f),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.clickable { onPassengerClick?.invoke(passenger) }
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
              Spacer(modifier = Modifier.width(4.dp))
              Text(
                text = passenger,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                color = SlateTextPrimary
              )
            }
          }
        }
      }

      Spacer(modifier = Modifier.height(8.dp))

      // Flight Details & Notes
      if (log.notes.isNotBlank()) {
        Text(
          text = log.notes,
          style = MaterialTheme.typography.bodySmall,
          color = SlateTextSecondary,
          lineHeight = 16.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
      }

      // Footer
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = "Pilots: ${log.pilots}",
          fontSize = 11.sp,
          color = SlateTextTertiary
        )

        Surface(
          color = ArchiveSurfaceElevated,
          shape = RoundedCornerShape(4.dp),
          modifier = Modifier.clickable { onOpenSourceDoc(log.sourceDocId) }
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
          ) {
            Icon(
              imageVector = Icons.Default.Description,
              contentDescription = null,
              tint = GoldAccent,
              modifier = Modifier.size(11.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
              text = "Source: ${log.sourceDocId}",
              fontFamily = FontFamily.Monospace,
              fontSize = 10.sp,
              fontWeight = FontWeight.Bold,
              color = GoldAccent
            )
          }
        }
      }
    }
  }
}
