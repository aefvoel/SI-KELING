package tiregdev.sipepikeling.model;

/**
 * Created by SONY-VAIO on 9/17/2017.
 */

public class RUMAHSAKIT {
    private String namaRS;
    private String jumlahTempatTidur;
    private String kelasRS;
    private String idPetugas;
    private String waktu;
    private String alamat;
    private String koordinat;
    private double totalNilai;
    private String status;

    public RUMAHSAKIT(String namaRS, String jumlahTempatTidur, String kelasRS, String idPetugas, String waktu, String alamat, String koordinat, double totalNilai, String status) {
        this.namaRS = namaRS;
        this.jumlahTempatTidur = jumlahTempatTidur;
        this.kelasRS = kelasRS;
        this.idPetugas = idPetugas;
        this.waktu = waktu;
        this.alamat = alamat;
        this.koordinat = koordinat;
        this.totalNilai = totalNilai;
        this.status = status;
    }

    public String getNamaRS() {
        return namaRS;
    }

    public void setNamaRS(String namaRS) {
        this.namaRS = namaRS;
    }

    public String getJumlahTempatTidur() {
        return jumlahTempatTidur;
    }

    public void setJumlahTempatTidur(String jumlahTempatTidur) {
        this.jumlahTempatTidur = jumlahTempatTidur;
    }

    public String getKelasRS() {
        return kelasRS;
    }

    public void setKelasRS(String kelasRS) {
        this.kelasRS = kelasRS;
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
