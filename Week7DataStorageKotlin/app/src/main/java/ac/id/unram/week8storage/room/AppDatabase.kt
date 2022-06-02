package ac.id.unram.week8storage.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MahasiswaEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mahasiswaDao(): MahasiswaDao
}