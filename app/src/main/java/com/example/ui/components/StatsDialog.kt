package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Flight
import androidx.compose.material.icons.filled.Gavel
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.ui.theme.ArchiveBgDark
import com.example.ui.theme.ArchiveBorder
import com.example.ui.theme.ArchiveSurfaceCard
import com.example.ui.theme.ArchiveSurfaceElevated
import com.example.ui.theme.CategoryCourt
import com.example.ui.theme.CategoryDeposition
import com.example.ui.theme.CategoryFlight
import com.example.ui.theme.CrimsonStamp
import com.example.ui.theme.CrimsonStampBg
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.SlateTextPrimary
import com.example.ui.theme.SlateTextSecondary
import com.example.ui.theme.SlateTextTertiary

@Composable
fun StatsDialog(
  docCount: Int,
  entityCount: Int,
  flightCount: Int,
  onDismiss: () -> Unit
) {
  Dialog(onDismissRequest = onDismiss) {
    Card(
      colors = CardDefaults.cardColors(containerColor = ArchiveSurfaceCard),
      border = BorderStroke(1.dp, ArchiveBorder),
      shape = RoundedCornerShape(16.dp),
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
        .testTag("stats_dialog")
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(20.dp)
      ) {
        // Header
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            Icon(
              imageVector = Icons.Default.Analytics,
              contentDescription = null,
              tint = GoldAccent,
              modifier = Modifier.size(22.dp)
            )
            Text(
              text = "Archive Database Metrics",
              fontSize = 16.sp,
              fontWeight = FontWeight.Bold,
              color = SlateTextPrimary
            )
          }

          IconButton(
            onClick = onDismiss,
            modifier = Modifier.size(32.dp)
          ) {
            Icon(
              imageVector = Icons.Default.Close,
              contentDescription = "Close",
              tint = SlateTextSecondary
            )
          }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Grid of metric tiles
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          MetricTile(
            title = "Unsealed Files",
            value = "$docCount",
            subtitle = "Indexed SDNY dockets",
            icon = Icons.Default.Description,
            color = CategoryCourt,
            modifier = Modifier.weight(1f)
          )

          MetricTile(
            title = "Entities Mapped",
            value = "$entityCount",
            subtitle = "Cross-referenced names",
            icon = Icons.Default.Person,
            color = CategoryDeposition,
            modifier = Modifier.weight(1f)
          )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          MetricTile(
            title = "Flight Manifests",
            value = "$flightCount",
            subtitle = "FAA certified records",
            icon = Icons.Default.Flight,
            color = CategoryFlight,
            modifier = Modifier.weight(1f)
          )

          MetricTile(
            title = "Court Authority",
            value = "SDNY",
            subtitle = "Case 15-cv-07433",
            icon = Icons.Default.Gavel,
            color = GoldAccent,
            modifier = Modifier.weight(1f)
          )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Public Records notice
        Surface(
          color = ArchiveSurfaceElevated,
          border = BorderStroke(1.dp, ArchiveBorder),
          shape = RoundedCornerShape(8.dp)
        ) {
          Column(modifier = Modifier.padding(12.dp)) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
              Icon(
                imageVector = Icons.Default.Shield,
                contentDescription = null,
                tint = GoldAccent,
                modifier = Modifier.size(15.dp)
              )
              Text(
                text = "PUBLIC RECORDS INTEGRITY",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = GoldAccent,
                letterSpacing = 1.sp
              )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
              text = "All documents, exhibits, and flight manifests in this database are unsealed public court records released by order of the United States District Court for the Southern District of New York. Mentions in filings do not constitute findings of wrongdoing.",
              fontSize = 12.sp,
              color = SlateTextSecondary,
              lineHeight = 17.sp
            )
          }
        }
      }
    }
  }
}

@Composable
private fun MetricTile(
  title: String,
  value: String,
  subtitle: String,
  icon: ImageVector,
  color: androidx.compose.ui.graphics.Color,
  modifier: Modifier = Modifier
) {
  Surface(
    color = ArchiveSurfaceElevated,
    border = BorderStroke(1.dp, ArchiveBorder),
    shape = RoundedCornerShape(10.dp),
    modifier = modifier
  ) {
    Column(modifier = Modifier.padding(12.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = title,
          fontSize = 11.sp,
          fontWeight = FontWeight.Medium,
          color = SlateTextTertiary
        )
        Icon(
          imageVector = icon,
          contentDescription = null,
          tint = color,
          modifier = Modifier.size(16.dp)
        )
      }
      Spacer(modifier = Modifier.height(6.dp))
      Text(
        text = value,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Monospace,
        color = color
      )
      Spacer(modifier = Modifier.height(2.dp))
      Text(
        text = subtitle,
        fontSize = 10.sp,
        color = SlateTextSecondary
      )
    }
  }
}
