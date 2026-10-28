package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
  entities = [
    ArchiveDocument::class,
    NamedEntity::class,
    FlightLogEntry::class,
    TimelineMilestone::class
  ],
  version = 1,
  exportSchema = false
)
abstract class ArchiveDatabase : RoomDatabase() {
  abstract fun archiveDao(): ArchiveDao

  companion object {
    @Volatile
    private var INSTANCE: ArchiveDatabase? = null

    fun getDatabase(context: Context): ArchiveDatabase {
      return INSTANCE ?: synchronized(this) {
        val instance = Room.databaseBuilder(
          context.applicationContext,
          ArchiveDatabase::class.java,
          "epstein_files_archive.db"
        )
        .fallbackToDestructiveMigration()
        .build()
        INSTANCE = instance
        instance
      }
    }
  }
}
