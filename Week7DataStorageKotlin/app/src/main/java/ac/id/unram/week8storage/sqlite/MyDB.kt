package ac.id.unram.week8storage.sqlite

import android.provider.BaseColumns

object MyDB {
    // Table contents are grouped together in an anonymous object.
    object TableMahasiswa : BaseColumns {
        const val TABLE_NAME = "mahasiswa"
        const val COLUMN_NIM = "nim"
        const val COLUMN_NAMA = "nama"
    }

    const val SQL_CREATE_ENTRIES =
        "CREATE TABLE ${TableMahasiswa.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${TableMahasiswa.COLUMN_NIM} TEXT," +
                "${TableMahasiswa.COLUMN_NAMA} TEXT)"

    const val SQL_DELETE_ENTRIES =
        "DROP TABLE IF EXISTS ${TableMahasiswa.TABLE_NAME}"
}