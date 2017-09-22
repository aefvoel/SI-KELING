package tiregdev.sipepikeling.model;

/**
 * Created by SONY-VAIO on 9/17/2017.
 */

public class PESANTREN {
    private String namaPesantren;
    private String namaPengelola;
    private String jumlahSantri;
    private String idPetugas;
    private String waktu;
    private String alamat;
    private String koordinat;
    private double totalNilai;
    private String status;

    public PESANTREN(String namaPesantren, String namaPengelola, String jumlahSantri, String idPetugas, String waktu, String alamat, String koordinat, double totalNilai, String status) {
        this.namaPesantren = namaPesantren;
        this.namaPengelola = namaPengelola;
        this.jumlahSantri = jumlahSantri;
        this.idPetugas = idPetugas;
        this.waktu = waktu;
        this.alamat = alamat;
        this.koordinat = koordinat;
        this.totalNilai = totalNilai;
        this.status = status;
    }

    public String getNamaPesantren() {
        return namaPesantren;
    }

    public void setNamaPesantren(String namaPesantren) {
        this.namaPesantren = namaPesantren;
    }

    public String getNamaPengelola() {
        return namaPengelola;
    }

    public void setNamaPengelola(String namaPengelola) {
        this.namaPengelola = namaPengelola;
    }

    public String getJumlahSantri() {
        return jumlahSantri;
    }

    public void setJumlahSantri(String jumlahSantri) {
        this.jumlahSantri = jumlahSantri;
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
