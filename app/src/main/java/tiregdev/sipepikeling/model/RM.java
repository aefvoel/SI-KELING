package tiregdev.sipepikeling.model;

/**
 * Created by SONY-VAIO on 8/21/2017.
 */

public class RM {
    private String namaTempat;
    private String namaPemilik;
    private String jumlahKaryawan;
    private String jumlahPenjamah;
    private String noIzinUsaha;
    private String waktu;
    private String alamat;
    private String koordinat;
    private String idPetugas;

    public RM(String namaTempat, String namaPemilik, String jumlahKaryawan, String jumlahPenjamah, String noIzinUsaha, String waktu, String alamat, String koordinat, String idPetugas, double totalNilai, String status) {
        this.namaTempat = namaTempat;
        this.namaPemilik = namaPemilik;
        this.jumlahKaryawan = jumlahKaryawan;
        this.jumlahPenjamah = jumlahPenjamah;
        this.noIzinUsaha = noIzinUsaha;
        this.waktu = waktu;
        this.alamat = alamat;
        this.koordinat = koordinat;
        this.idPetugas = idPetugas;
        this.totalNilai = totalNilai;
        this.status = status;
    }

    private double totalNilai;
    private String status;

    public String getNamaTempat() {
        return namaTempat;
    }

    public void setNamaTempat(String namaTempat) {
        this.namaTempat = namaTempat;
    }

    public String getNamaPemilik() {
        return namaPemilik;
    }

    public void setNamaPemilik(String namaPemilik) {
        this.namaPemilik = namaPemilik;
    }

    public String getJumlahKaryawan() {
        return jumlahKaryawan;
    }

    public void setJumlahKaryawan(String jumlahKaryawan) {
        this.jumlahKaryawan = jumlahKaryawan;
    }

    public String getJumlahPenjamah() {
        return jumlahPenjamah;
    }

    public void setJumlahPenjamah(String jumlahPenjamah) {
        this.jumlahPenjamah = jumlahPenjamah;
    }

    public String getNoIzinUsaha() {
        return noIzinUsaha;
    }

    public void setNoIzinUsaha(String noIzinUsaha) {
        this.noIzinUsaha = noIzinUsaha;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKoordinat() {
        return koordinat;
    }

    public void setKoordinat(String koordinat) {
        this.koordinat = koordinat;
    }

    public String getIdPetugas() {
        return idPetugas;
    }

    public void setIdPetugas(String idPetugas) {
        this.idPetugas = idPetugas;
    }

    public double getTotalNilai() {
        return totalNilai;
    }

    public void setTotalNilai(double totalNilai) {
        this.totalNilai = totalNilai;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
