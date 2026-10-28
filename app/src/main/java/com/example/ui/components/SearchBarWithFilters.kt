package com.example.ui.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.ArchiveBorder
import com.example.ui.theme.ArchiveSurfaceCard
import com.example.ui.theme.ArchiveSurfaceDark
import com.example.ui.theme.ArchiveSurfaceElevated
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.SlateTextPrimary
import com.example.ui.theme.SlateTextSecondary
import com.example.ui.theme.SlateTextTertiary

@Composable
fun SearchBarWithFilters(
  query: String,
  onQueryChange: (String) -> Unit,
  selectedCategory: String,
  onCategorySelected: (String) -> Unit,
  categories: List<String>,
  modifier: Modifier = Modifier,
  placeholder: String = "Search documents, names, keywords...",
  onFilterClick: (() -> Unit)? = null
) {
  Column(modifier = modifier.fillMaxWidth()) {
    // Search input field
    OutlinedTextField(
      value = query,
      onValueChange = onQueryChange,
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp)
        .testTag("archive_search_bar"),
      placeholder = {
        Text(
          text = placeholder,
          color = SlateTextTertiary,
          fontSize = 14.sp
        )
      },
      leadingIcon = {
        Icon(
          imageVector = Icons.Default.Search,
          contentDescription = "Search",
          tint = GoldAccent
        )
      },
      trailingIcon = {
        Row(verticalAlignment = Alignment.CenterVertically) {
          if (query.isNotEmpty()) {
            IconButton(
              onClick = { onQueryChange("") },
              modifier = Modifier.size(36.dp)
            ) {
              Icon(
                imageVector = Icons.Default.Clear,
                contentDescription = "Clear search",
                tint = SlateTextSecondary
              )
            }
          }
          if (onFilterClick != null) {
            IconButton(
              onClick = onFilterClick,
              modifier = Modifier.size(36.dp)
            ) {
              Icon(
                imageVector = Icons.Default.FilterList,
                contentDescription = "Additional filters",
                tint = GoldAccent
              )
            }
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

    // Horizontal category chips
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .horizontalScroll(rememberScrollState())
        .padding(horizontal = 16.dp),
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      categories.forEach { category ->
        val isSelected = category.equals(selectedCategory, ignoreCase = true)
        FilterChip(
          selected = isSelected,
          onClick = { onCategorySelected(category) },
          label = {
            Text(
              text = category,
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
          shape = RoundedCornerShape(20.dp),
          modifier = Modifier.testTag("filter_chip_$category")
        )
      }
    }
  }
}
