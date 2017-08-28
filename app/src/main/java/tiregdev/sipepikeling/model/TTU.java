package tiregdev.sipepikeling.model;

/**
 * Created by SONY-VAIO on 8/21/2017.
 */

public class TTU {
    private String namaTempat;
    private String namaPengurus;
    private String jumlahJamaah;
    private String idPetugas;
    private String waktu;
    private String alamat;
    private String koordinat;
    private double totalNilai;
    private String status;

    public TTU(String namaTempat, String namaPengurus, String jumlahJamaah, String idPetugas, String waktu, String alamat, String koordinat, double totalNilai, String status) {
        this.namaTempat = namaTempat;
        this.namaPengurus = namaPengurus;
        this.jumlahJamaah = jumlahJamaah;
        this.idPetugas = idPetugas;
        this.waktu = waktu;
        this.alamat = alamat;
        this.koordinat = koordinat;
        this.totalNilai = totalNilai;
        this.status = status;
    }

    public String getNamaTempat() {
        return namaTempat;
    }

    public void setNamaTempat(String namaTempat) {
        this.namaTempat = namaTempat;
    }

    public String getNamaPengurus() {
        return namaPengurus;
    }

    public void setNamaPengurus(String namaPengurus) {
        this.namaPengurus = namaPengurus;
    }

    public String getJumlahJamaah() {
        return jumlahJamaah;
    }

    public void setJumlahJamaah(String jumlahJamaah) {
        this.jumlahJamaah = jumlahJamaah;
    }

    public String getIdPetugas() {
        return idPetugas;
    }

    public void setIdPetugas(String idPetugas) {
        this.idPetugas = idPetugas;
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
