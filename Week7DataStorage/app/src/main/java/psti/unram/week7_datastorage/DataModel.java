package psti.unram.week7_datastorage;

public class DataModel {
    String nim, nama;

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public boolean isAvailable() {
        return !this.nim.isEmpty();
    }
}
