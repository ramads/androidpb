package ac.id.unram.week8storage.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Mahasiswa")
data class MahasiswaEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "nim") val nim: String?,
    @ColumnInfo(name = "nama") val nama: String?
)
