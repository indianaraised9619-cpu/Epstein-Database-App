package com.example.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val ArchiveColorScheme = darkColorScheme(
  primary = GoldAccent,
  onPrimary = ArchiveBgDark,
  primaryContainer = ArchiveSurfaceElevated,
  onPrimaryContainer = GoldAccentLight,
  secondary = CategoryCourt,
  onSecondary = ArchiveBgDark,
  secondaryContainer = CategoryCourtBg,
  onSecondaryContainer = CategoryCourt,
  tertiary = CrimsonStamp,
  onTertiary = SlateTextPrimary,
  background = ArchiveBgDark,
  onBackground = SlateTextPrimary,
  surface = ArchiveSurfaceDark,
  onSurface = SlateTextPrimary,
  surfaceVariant = ArchiveSurfaceCard,
  onSurfaceVariant = SlateTextSecondary,
  outline = ArchiveBorder,
  outlineVariant = ArchiveSurfaceElevated
)

@Composable
fun MyApplicationTheme(
  content: @Composable () -> Unit,
) {
  val view = LocalView.current
  if (!view.isInEditMode) {
    SideEffect {
      val window = (view.context as? Activity)?.window
      if (window != null) {
        window.statusBarColor = ArchiveBgDark.toArgb()
        window.navigationBarColor = ArchiveBgDark.toArgb()
        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = false
      }
    }
  }

  MaterialTheme(
    colorScheme = ArchiveColorScheme,
    typography = Typography,
    content = content
  )
}
