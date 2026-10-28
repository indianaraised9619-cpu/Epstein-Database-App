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
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PersonSearch
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
import com.example.data.local.NamedEntity
import com.example.ui.components.EntityCard
import com.example.ui.theme.ArchiveBgDark
import com.example.ui.theme.ArchiveBorder
import com.example.ui.theme.ArchiveSurfaceCard
import com.example.ui.theme.ArchiveSurfaceDark
import com.example.ui.theme.ArchiveSurfaceElevated
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.SlateTextPrimary
import com.example.ui.theme.SlateTextSecondary
import com.example.ui.theme.SlateTextTertiary

@Composable
fun EntitiesScreen(
  entities: List<NamedEntity>,
  searchQuery: String,
  onSearchQueryChange: (String) -> Unit,
  selectedRole: String,
  onRoleSelected: (String) -> Unit,
  onEntityClick: (NamedEntity) -> Unit,
  onToggleFavorite: (NamedEntity) -> Unit,
  onFilterDocuments: (NamedEntity) -> Unit,
  modifier: Modifier = Modifier
) {
  val roles = listOf(
    "All",
    "Plaintiff",
    "Co-defendant / Convicted",
    "Primary Subject",
    "Witness / Deposition",
    "Flight Passenger / Mentioned",
    "Pilot / Staff",
    "Legal Counsel"
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
          .testTag("entity_search_input"),
        placeholder = {
          Text(
            text = "Search person by name or testimony role...",
            color = SlateTextTertiary,
            fontSize = 14.sp
          )
        },
        leadingIcon = {
          Icon(
            imageVector = Icons.Default.PersonSearch,
            contentDescription = "Search Names",
            tint = GoldAccent
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
          focusedBorderColor = GoldAccent,
          unfocusedBorderColor = ArchiveBorder,
          focusedTextColor = SlateTextPrimary,
          unfocusedTextColor = SlateTextPrimary,
          cursorColor = GoldAccent
        )
      )

      Spacer(modifier = Modifier.height(10.dp))

      // Horizontal Role Filter Chips
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        roles.forEach { role ->
          val isSelected = role.equals(selectedRole, ignoreCase = true)
          FilterChip(
            selected = isSelected,
            onClick = { onRoleSelected(role) },
            label = {
              Text(
                text = role,
                fontSize = 12.sp,
                color = if (isSelected) GoldAccent else SlateTextSecondary
              )
            },
            colors = FilterChipDefaults.filterChipColors(
              containerColor = ArchiveSurfaceDark,
              labelColor = SlateTextSecondary,
              selectedContainerColor = ArchiveSurfaceElevated,
              selectedLabelColor = GoldAccent
            ),
            border = FilterChipDefaults.filterChipBorder(
              enabled = true,
              selected = isSelected,
              borderColor = ArchiveBorder,
              selectedBorderColor = GoldAccent,
              borderWidth = if (isSelected) 1.5.dp else 1.dp
            ),
            shape = RoundedCornerShape(20.dp)
          )
        }
      }

      Spacer(modifier = Modifier.height(10.dp))

      // Results count
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = "${entities.size} Key Named Figures Indexed",
          fontSize = 12.sp,
          fontWeight = FontWeight.SemiBold,
          color = SlateTextSecondary
        )

        if (searchQuery.isNotEmpty() || selectedRole != "All") {
          Text(
            text = "Reset",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = GoldAccent,
            modifier = Modifier
              .clickable {
                onSearchQueryChange("")
                onRoleSelected("All")
              }
              .padding(4.dp)
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(8.dp))

    // List of Entities
    if (entities.isEmpty()) {
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
            imageVector = Icons.Default.Person,
            contentDescription = null,
            tint = SlateTextTertiary,
            modifier = Modifier.size(48.dp)
          )
          Spacer(modifier = Modifier.height(12.dp))
          Text(
            text = "No Person Found Matching Query",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = SlateTextPrimary
          )
          Spacer(modifier = Modifier.height(16.dp))
          Button(
            onClick = {
              onSearchQueryChange("")
              onRoleSelected("All")
            },
            colors = ButtonDefaults.buttonColors(
              containerColor = GoldAccent,
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
          .testTag("entities_list"),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 80.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
      ) {
        items(
          items = entities,
          key = { it.id }
        ) { entity ->
          EntityCard(
            entity = entity,
            onClick = { onEntityClick(entity) },
            onToggleFavorite = { onToggleFavorite(entity) },
            onFilterDocuments = { onFilterDocuments(entity) }
          )
        }
      }
    }
  }
}
