package ac.id.unram.week8storage

import ac.id.unram.week8storage.sqlite.MyDB
import ac.id.unram.week8storage.sqlite.MahasiswaDBHelper
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteOpenHelper

class StorageSQLite : StorageMahasiswaInterface {
    private var dbHelper : SQLiteOpenHelper

    constructor(context: Context) {
        dbHelper = MahasiswaDBHelper(context)
    }

    override fun saveMahasiswa(mahasiswa: Mahasiswa) {
        // Gets the data repository in write mode
        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {
            put(MyDB.TableMahasiswa.COLUMN_NIM, mahasiswa.nim)
            put(MyDB.TableMahasiswa.COLUMN_NAMA, mahasiswa.nama)
        }

        db?.insert(MyDB.TableMahasiswa.TABLE_NAME, null, values)
    }

    override fun getMahasiswa(): Mahasiswa {
        val db = dbHelper.readableDatabase

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        val projection = arrayOf(
            MyDB.TableMahasiswa.COLUMN_NIM,
            MyDB.TableMahasiswa.COLUMN_NAMA)

        // Contoh: Filter results WHERE "id" = 'id'
//        val selection = "${BaseColumns._ID} = ?"
//        val selectionArgs = arrayOf("1")

        val selection = null
        val selectionArgs = null

        // How you want the results sorted in the resulting Cursor
        val sortOrder = "${MyDB.TableMahasiswa.COLUMN_NIM} DESC"

        val cursor = db.query(
            MyDB.TableMahasiswa.TABLE_NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            selection,              // The columns for the WHERE clause
            selectionArgs,          // The values for the WHERE clause
            null,           // don't group the rows
            null,            // don't filter by row groups
            sortOrder               // The sort order
        )


        var nim = ""
        var nama = ""
        with(cursor) {
            while (moveToNext()) {
                nim = getString(getColumnIndexOrThrow(MyDB.TableMahasiswa.COLUMN_NIM))
                nama = getString(getColumnIndexOrThrow(MyDB.TableMahasiswa.COLUMN_NAMA))
            }
        }
        cursor.close()

        val mahasiswa = Mahasiswa(nim, nama)
        return mahasiswa
    }

    override fun deleteMahasiswa() {
        val db = dbHelper.writableDatabase

        // Contoh: Define 'where' part of query.
        // val selection = "${BaseColumns._ID} = ?"
        val selection = null

        // Contoh: Specify arguments in placeholder order.
        // val selectionArgs = arrayOf("1")
        val selectionArgs = null

        // Issue SQL statement.
        db.delete(MyDB.TableMahasiswa.TABLE_NAME, selection, selectionArgs)
    }
}