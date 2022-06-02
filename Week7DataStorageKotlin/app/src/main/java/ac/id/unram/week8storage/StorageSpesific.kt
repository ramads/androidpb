package ac.id.unram.week8storage

import android.content.Context
import java.lang.Exception

class StorageSpesific : StorageMahasiswaInterface {

    private var context : Context

    constructor(context: Context) {
        this.context = context
    }

    override fun saveMahasiswa(mahasiswa: Mahasiswa) {
        val data = "${mahasiswa.nim} ${mahasiswa.nama}"
        context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE).use {
            it.write(data.toByteArray())
        }
    }

    override fun getMahasiswa(): Mahasiswa {
        try {
            val text = context.openFileInput(FILE_NAME).bufferedReader().useLines { lines ->
                lines.fold("") { some, text ->
                    "$text"
                }
            }
            val res = text?.split(" ")

            return Mahasiswa(
                nim = res!!.get(0),
                nama = res!!.get(1)
            )

        } catch (e: Exception) {
            print(e.toString())
            return Mahasiswa()
        }
    }

    override fun deleteMahasiswa() {
        context.deleteFile(FILE_NAME)
    }

    companion object {
        val FILE_NAME = "file_data"
    }
}