package tiregdev.sipepikeling.model;

/**
 * Created by SONY-VAIO on 8/23/2017.
 */

public class SAB {
    private String namaPuskesmas;
    private String kodeSarana;
    private String pemilikSarana;
    private String sudahDiambil;
    private String kodeSampel;
    private String golongan;
    private String kategori;
    private String waktu;
    private String alamat;
    private String koordinat;
    private String idPetugas;
    private int totalNilai;
    private String status;
    private String idRS;

    public SAB(String namaPuskesmas, String kodeSarana, String pemilikSarana, String sudahDiambil, String kodeSampel, String golongan, String kategori, String waktu, String alamat, String koordinat, String idPetugas, int totalNilai, String status, String idRS) {
        this.namaPuskesmas = namaPuskesmas;
        this.kodeSarana = kodeSarana;
        this.pemilikSarana = pemilikSarana;
        this.sudahDiambil = sudahDiambil;
        this.kodeSampel = kodeSampel;
        this.golongan = golongan;
        this.kategori = kategori;
        this.waktu = waktu;
        this.alamat = alamat;
        this.koordinat = koordinat;
        this.idPetugas = idPetugas;
        this.totalNilai = totalNilai;
        this.status = status;
        this.idRS = idRS;
    }

    public SAB(String kategori, String waktu, String alamat, String koordinat, String idPetugas, String idRS) {
        this.kategori = kategori;
        this.waktu = waktu;
        this.alamat = alamat;
        this.koordinat = koordinat;
        this.idPetugas = idPetugas;
        this.idRS = idRS;
    }

    public String getNamaPuskesmas() {
        return namaPuskesmas;
    }

    public void setNamaPuskesmas(String namaPuskesmas) {
        this.namaPuskesmas = namaPuskesmas;
    }

    public String getKodeSarana() {
        return kodeSarana;
    }

    public void setKodeSarana(String kodeSarana) {
        this.kodeSarana = kodeSarana;
    }

    public String getPemilikSarana() {
        return pemilikSarana;
    }

    public void setPemilikSarana(String pemilikSarana) {
        this.pemilikSarana = pemilikSarana;
    }

    public String getSudahDiambil() {
        return sudahDiambil;
    }

    public void setSudahDiambil(String sudahDiambil) {
        this.sudahDiambil = sudahDiambil;
    }

    public String getKodeSampel() {
        return kodeSampel;
    }

    public void setKodeSampel(String kodeSampel) {
        this.kodeSampel = kodeSampel;
    }

    public String getGolongan() {
        return golongan;
    }

    public void setGolongan(String golongan) {
        this.golongan = golongan;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
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

    public String getIdRS() {
        return idRS;
    }

    public void setIdRS(String idRS) {
        this.idRS = idRS;
    }
}
