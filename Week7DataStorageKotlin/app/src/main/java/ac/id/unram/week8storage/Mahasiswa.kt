package ac.id.unram.week8storage

data class Mahasiswa(
    val nim: String = "",
    val nama: String = "",
) {
    fun isAvailable() : Boolean {
        return !this.nim.isEmpty()
    }
}
