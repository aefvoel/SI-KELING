package tiregdev.sipepikeling.model;

/**
 * Created by SONY-VAIO on 9/14/2017.
 */

public class PASAR {
    private String namaPasar;
    private String namaPengelola;
    private String jumlahKios;
    private String jumlahPedagang;
    private String jumlahAsosiasi;
    private String idPetugas;
    private String waktu;
    private String alamat;
    private String koordinat;
    private double totalNilai;
    private String status;

    public PASAR(String namaPasar, String namaPengelola, String jumlahKios, String jumlahPedagang, String jumlahAsosiasi, String idPetugas, String waktu, String alamat, String koordinat, double totalNilai, String status) {
        this.namaPasar = namaPasar;
        this.namaPengelola = namaPengelola;
        this.jumlahKios = jumlahKios;
        this.jumlahPedagang = jumlahPedagang;
        this.jumlahAsosiasi = jumlahAsosiasi;
        this.idPetugas = idPetugas;
        this.waktu = waktu;
        this.alamat = alamat;
        this.koordinat = koordinat;
        this.totalNilai = totalNilai;
        this.status = status;
    }

    public String getNamaPasar() {
        return namaPasar;
    }

    public void setNamaPasar(String namaPasar) {
        this.namaPasar = namaPasar;
    }

    public String getNamaPengelola() {
        return namaPengelola;
    }

    public void setNamaPengelola(String namaPengelola) {
        this.namaPengelola = namaPengelola;
    }

    public String getJumlahKios() {
        return jumlahKios;
    }

    public void setJumlahKios(String jumlahKios) {
        this.jumlahKios = jumlahKios;
    }

    public String getJumlahPedagang() {
        return jumlahPedagang;
    }

    public void setJumlahPedagang(String jumlahPedagang) {
        this.jumlahPedagang = jumlahPedagang;
    }

    public String getJumlahAsosiasi() {
        return jumlahAsosiasi;
    }

    public void setJumlahAsosiasi(String jumlahAsosiasi) {
        this.jumlahAsosiasi = jumlahAsosiasi;
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
