package ac.id.unram.week8storage

import ac.id.unram.week8storage.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var mahasiswa : Mahasiswa
    private lateinit var storage : StorageMahasiswaInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // coba salah satu metode penyimpanan dengan uncomment
        // salah satu mode penyimpanan di bawah ini
        // =================================================================
        setMode(MODE_SHARED_PREFERENCES) //mode peyimpanan sharedPreferences
//        setMode(MODE_APP_SPESIFIC) //mode peyimpanan File Spesific
//        setMode(MODE_SQLITE_DB) //mode peyimpanan SQLite Database
//        setMode(MODE_ROOM_DB) //mode peyimpanan room database

        // tampilakan hasil data mahasiswa
        showMahasiswa()

        binding.btnSave.setOnClickListener {
            val nim = binding.etNim.text.toString()
            val nama = binding.etNama.text.toString()
            val mahasiswa = Mahasiswa(nim, nama)

            storage.saveMahasiswa(mahasiswa)
            showMahasiswa()
        }

        binding.btnDelete.setOnClickListener {
            storage.deleteMahasiswa()
            showMahasiswa()
        }
    }

    private fun showMahasiswa() {
        mahasiswa = storage.getMahasiswa()
        if (mahasiswa.isAvailable()) {
            binding.tvResult.text = "Nim: ${mahasiswa.nim}\nNama: ${mahasiswa.nama}"
        } else {
            binding.tvResult.text = "Belum ada data"
        }
    }

    private fun setMode(mode : String) {
        when(mode) {
            MODE_SHARED_PREFERENCES -> storage = StoragePreferences(this)
            MODE_APP_SPESIFIC -> storage = StorageSpesific(this)
            MODE_SQLITE_DB -> storage = StorageSQLite(this)
            MODE_ROOM_DB -> storage = StorageRoom(this)
            else -> throw IllegalArgumentException("Mode tidak ditemukan!")
        }
        binding.mode.text = "Mode $mode"
    }

    companion object {
        val MODE_SHARED_PREFERENCES = "SharedPreferences"
        val MODE_APP_SPESIFIC = "App-Spesific"
        val MODE_SQLITE_DB = "SQLite Database"
        val MODE_ROOM_DB = "Room Database"

    }
}