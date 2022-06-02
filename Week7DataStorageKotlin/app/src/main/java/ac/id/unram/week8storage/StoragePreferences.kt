package ac.id.unram.week8storage

import android.content.Context
import android.content.SharedPreferences

class StoragePreferences : StorageMahasiswaInterface{
    private var pref : SharedPreferences

    constructor(context: Context) {
        pref = context.getSharedPreferences(
            PREF_NAME,
            Context.MODE_PRIVATE
        )
    }

    override fun saveMahasiswa(mahasiswa: Mahasiswa) {
        with(pref.edit()) {
            putString(DATA_NIM, mahasiswa.nim)
            putString(DATA_NAMA, mahasiswa.nama)
            apply()
        }
    }

    override fun getMahasiswa(): Mahasiswa {
        val nim = pref.getString(DATA_NIM, "")!!
        val nama = pref.getString(DATA_NAMA, "")!!
        return Mahasiswa(nim, nama)
    }

    // hapus data di shared preference
    override fun deleteMahasiswa() {
        pref.edit().clear().apply()
    }

    companion object {
        private const val PREF_NAME = "data_pref"
        private const val DATA_NIM = "nim"
        private const val DATA_NAMA = "nama"
    }
}