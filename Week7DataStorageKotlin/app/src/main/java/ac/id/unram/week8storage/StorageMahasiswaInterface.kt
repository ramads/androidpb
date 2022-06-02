package ac.id.unram.week8storage

interface StorageMahasiswaInterface {
    fun saveMahasiswa(mahasiswa: Mahasiswa)
    fun getMahasiswa() : Mahasiswa
    fun deleteMahasiswa()
}