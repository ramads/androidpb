package ac.id.unram.week8storage

import ac.id.unram.week8storage.room.AppDatabase
import ac.id.unram.week8storage.room.MahasiswaEntity
import android.content.Context
import androidx.room.Room

class StorageRoom : StorageMahasiswaInterface {
    private var db : AppDatabase

    constructor(context: Context) {
        db = Room.databaseBuilder(
            context,
            AppDatabase::class.java, "mahasiswa_db"
        ).allowMainThreadQueries().build()
    }
    override fun saveMahasiswa(mahasiswa: Mahasiswa) {
        val entity = MahasiswaEntity(
            nim = mahasiswa.nim,
            nama = mahasiswa.nama
        )
        db.mahasiswaDao().insert(entity)
    }

    override fun getMahasiswa(): Mahasiswa {
        val list : List<Mahasiswa> = db.mahasiswaDao().getAll()

        if (!list.isEmpty()) {
            val mahasiswa : Mahasiswa = list.get(list.size - 1)
            return mahasiswa
        }
        return Mahasiswa()
    }

    override fun deleteMahasiswa() {
        db.mahasiswaDao().delete()
    }
}