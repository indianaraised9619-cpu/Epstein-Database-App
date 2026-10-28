package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Flight
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.FlightLogEntry
import com.example.ui.components.FlightLogCard
import com.example.ui.theme.ArchiveBgDark
import com.example.ui.theme.ArchiveBorder
import com.example.ui.theme.ArchiveSurfaceCard
import com.example.ui.theme.ArchiveSurfaceDark
import com.example.ui.theme.ArchiveSurfaceElevated
import com.example.ui.theme.CategoryFlight
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.SlateTextPrimary
import com.example.ui.theme.SlateTextSecondary
import com.example.ui.theme.SlateTextTertiary

@Composable
fun FlightLogsScreen(
  logs: List<FlightLogEntry>,
  searchQuery: String,
  onSearchQueryChange: (String) -> Unit,
  selectedTail: String,
  onTailSelected: (String) -> Unit,
  onToggleBookmark: (FlightLogEntry) -> Unit,
  onOpenSourceDoc: (String) -> Unit,
  onPassengerClick: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  val tailNumbers = listOf(
    "All",
    "N908JE",
    "N909JE",
    "N212JE"
  )

  Column(
    modifier = modifier
      .fillMaxSize()
      .background(ArchiveBgDark)
  ) {
    // Search input
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 12.dp, start = 16.dp, end = 16.dp)
    ) {
      OutlinedTextField(
        value = searchQuery,
        onValueChange = onSearchQueryChange,
        modifier = Modifier
          .fillMaxWidth()
          .testTag("flight_search_input"),
        placeholder = {
          Text(
            text = "Search passenger, airport code, date, or pilot...",
            color = SlateTextTertiary,
            fontSize = 14.sp
          )
        },
        leadingIcon = {
          Icon(
            imageVector = Icons.Default.Flight,
            contentDescription = "Search Flights",
            tint = CategoryFlight
          )
        },
        trailingIcon = {
          if (searchQuery.isNotEmpty()) {
            IconButton(
              onClick = { onSearchQueryChange("") },
              modifier = Modifier.size(36.dp)
            ) {
              Icon(
                imageVector = Icons.Default.Clear,
                contentDescription = "Clear",
                tint = SlateTextSecondary
              )
            }
          }
        },
        singleLine = true,
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
          focusedContainerColor = ArchiveSurfaceCard,
          unfocusedContainerColor = ArchiveSurfaceDark,
          focusedBorderColor = CategoryFlight,
          unfocusedBorderColor = ArchiveBorder,
          focusedTextColor = SlateTextPrimary,
          unfocusedTextColor = SlateTextPrimary,
          cursorColor = CategoryFlight
        )
      )

      Spacer(modifier = Modifier.height(10.dp))

      // Horizontal Aircraft Tail Filter Chips
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        tailNumbers.forEach { tail ->
          val isSelected = tail.equals(selectedTail, ignoreCase = true)
          FilterChip(
            selected = isSelected,
            onClick = { onTailSelected(tail) },
            label = {
              Text(
                text = if (tail == "All") "All Aircraft" else tail,
                fontSize = 12.sp,
                color = if (isSelected) CategoryFlight else SlateTextSecondary
              )
            },
            colors = FilterChipDefaults.filterChipColors(
              containerColor = ArchiveSurfaceDark,
              labelColor = SlateTextSecondary,
              selectedContainerColor = ArchiveSurfaceElevated,
              selectedLabelColor = CategoryFlight
            ),
            border = FilterChipDefaults.filterChipBorder(
              enabled = true,
              selected = isSelected,
              borderColor = ArchiveBorder,
              selectedBorderColor = CategoryFlight,
              borderWidth = if (isSelected) 1.5.dp else 1.dp
            ),
            shape = RoundedCornerShape(20.dp)
          )
        }
      }

      Spacer(modifier = Modifier.height(10.dp))

      // Header row
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = "${logs.size} FAA Flight Manifest Records",
          fontSize = 12.sp,
          fontWeight = FontWeight.SemiBold,
          color = SlateTextSecondary
        )

        if (searchQuery.isNotEmpty() || selectedTail != "All") {
          Text(
            text = "Reset",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = CategoryFlight,
            modifier = Modifier
              .clickable {
                onSearchQueryChange("")
                onTailSelected("All")
              }
              .padding(4.dp)
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(8.dp))

    // List of Flight Logs
    if (logs.isEmpty()) {
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
            imageVector = Icons.Default.Flight,
            contentDescription = null,
            tint = SlateTextTertiary,
            modifier = Modifier.size(48.dp)
          )
          Spacer(modifier = Modifier.height(12.dp))
          Text(
            text = "No Flight Records Found",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = SlateTextPrimary
          )
          Spacer(modifier = Modifier.height(16.dp))
          Button(
            onClick = {
              onSearchQueryChange("")
              onTailSelected("All")
            },
            colors = ButtonDefaults.buttonColors(
              containerColor = CategoryFlight,
              contentColor = ArchiveBgDark
            ),
            shape = RoundedCornerShape(8.dp)
          ) {
            Text("Clear Search", fontWeight = FontWeight.Bold)
          }
        }
      }
    } else {
      LazyColumn(
        modifier = Modifier
          .fillMaxSize()
          .testTag("flight_logs_list"),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 80.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
      ) {
        items(
          items = logs,
          key = { it.id }
        ) { log ->
          FlightLogCard(
            log = log,
            onToggleBookmark = { onToggleBookmark(log) },
            onOpenSourceDoc = onOpenSourceDoc,
            onPassengerClick = onPassengerClick
          )
        }
      }
    }
  }
}
