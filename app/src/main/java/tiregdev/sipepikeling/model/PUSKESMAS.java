package tiregdev.sipepikeling.model;

/**
 * Created by SONY-VAIO on 9/17/2017.
 */

public class PUSKESMAS {
    private String namaPuskesmas;
    private String namaPimpinan;
    private String idPetugas;
    private String waktu;
    private String alamat;
    private String koordinat;
    private double totalNilai;
    private String status;

    public PUSKESMAS(String namaPuskesmas, String namaPimpinan, String idPetugas, String waktu, String alamat, String koordinat, double totalNilai, String status) {
        this.namaPuskesmas = namaPuskesmas;
        this.namaPimpinan = namaPimpinan;
        this.idPetugas = idPetugas;
        this.waktu = waktu;
        this.alamat = alamat;
        this.koordinat = koordinat;
        this.totalNilai = totalNilai;
        this.status = status;
    }

    public String getNamaPuskesmas() {
        return namaPuskesmas;
    }

    public void setNamaPuskesmas(String namaPuskesmas) {
        this.namaPuskesmas = namaPuskesmas;
    }

    public String getNamaPimpinan() {
        return namaPimpinan;
    }

    public void setNamaPimpinan(String namaPimpinan) {
        this.namaPimpinan = namaPimpinan;
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
