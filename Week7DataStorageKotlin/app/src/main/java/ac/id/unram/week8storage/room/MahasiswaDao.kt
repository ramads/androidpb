package ac.id.unram.week8storage.room

import ac.id.unram.week8storage.Mahasiswa
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MahasiswaDao {
    @Query("SELECT * FROM Mahasiswa")
    fun getAll(): List<Mahasiswa>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(vararg mahasiswa: MahasiswaEntity)

    @Query("DELETE FROM Mahasiswa")
    fun delete()
}