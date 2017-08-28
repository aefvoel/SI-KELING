package tiregdev.sipepikeling.model;

/**
 * Created by SONY-VAIO on 8/15/2017.
 */

public class DAM {
    private String namaPemilik;
    private String namaDepot;
    private String alamat;
    private String idPetugas;
    private String koordinat;
    private String waktu;
    private int totalNilai;
    private String status;

    public DAM(String namaPemilik, String namaDepot, String alamat, String idPetugas, String koordinat, String waktu, int totalNilai, String status) {
        this.namaPemilik = namaPemilik;
        this.namaDepot = namaDepot;
        this.alamat = alamat;
        this.idPetugas = idPetugas;
        this.koordinat = koordinat;
        this.waktu = waktu;
        this.totalNilai = totalNilai;
        this.status = status;
    }

    public String getNamaPemilik() {
        return namaPemilik;
    }

    public void setNamaPemilik(String namaPemilik) {
        this.namaPemilik = namaPemilik;
    }

    public String getNamaDepot() {
        return namaDepot;
    }

    public void setNamaDepot(String namaDepot) {
        this.namaDepot = namaDepot;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getIdPetugas() {
        return idPetugas;
    }

    public void setIdPetugas(String idPetugas) {
        this.idPetugas = idPetugas;
    }

    public String getKoordinat() {
        return koordinat;
    }

    public void setKoordinat(String koordinat) {
        this.koordinat = koordinat;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public int getTotalNilai() {
        return totalNilai;
    }

    public void setTotalNilai(int totalNilai) {
        this.totalNilai = totalNilai;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
