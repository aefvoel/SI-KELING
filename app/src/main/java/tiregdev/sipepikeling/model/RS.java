package tiregdev.sipepikeling.model;

/**
 * Created by SONY-VAIO on 8/18/2017.
 */

public class RS {

    private String koordinat;
    private String waktu;
    private String idPetugas;
    private int totalNilai;
    private String status;
    private String jamban;
    private String spal;
    private String pjb;
    private String sampah;
    private String idSAB;
    private String rt;
    private String rw;
    private String alamat;
    private String noRumah;
    private int jumlahAnggota;
    private String namaKK;
    private String statusRumah;


    public RS(String koordinat, String waktu, String idPetugas, int totalNilai, String status, String jamban, String spal, String pjb, String sampah, String idSAB, String rt, String rw, String alamat, String noRumah, int jumlahAnggota, String namaKK, String statusRumah) {
        this.koordinat = koordinat;
        this.waktu = waktu;
        this.idPetugas = idPetugas;
        this.totalNilai = totalNilai;
        this.status = status;
        this.jamban = jamban;
        this.spal = spal;
        this.pjb = pjb;
        this.sampah = sampah;
        this.idSAB = idSAB;
        this.rt = rt;
        this.rw = rw;
        this.alamat = alamat;
        this.noRumah = noRumah;
        this.jumlahAnggota = jumlahAnggota;
        this.namaKK = namaKK;
        this.statusRumah = statusRumah;
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

    public String getJamban() {
        return jamban;
    }

    public void setJamban(String jamban) {
        this.jamban = jamban;
    }

    public String getSpal() {
        return spal;
    }

    public void setSpal(String spal) {
        this.spal = spal;
    }

    public String getPjb() {
        return pjb;
    }

    public void setPjb(String pjb) {
        this.pjb = pjb;
    }

    public String getSampah() {
        return sampah;
    }

    public void setSampah(String sampah) {
        this.sampah = sampah;
    }

    public String getIdSAB() {
        return idSAB;
    }

    public void setIdSAB(String idSAB) {
        this.idSAB = idSAB;
    }

    public String getRt() {
        return rt;
    }

    public void setRt(String rt) {
        this.rt = rt;
    }

    public String getRw() {
        return rw;
    }

    public void setRw(String rw) {
        this.rw = rw;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoRumah() {
        return noRumah;
    }

    public void setNoRumah(String noRumah) {
        this.noRumah = noRumah;
    }

    public int getJumlahAnggota() {
        return jumlahAnggota;
    }

    public void setJumlahAnggota(int jumlahAnggota) {
        this.jumlahAnggota = jumlahAnggota;
    }

    public String getNamaKK() {
        return namaKK;
    }

    public void setNamaKK(String namaKK) {
        this.namaKK = namaKK;
    }

    public String getStatusRumah() {
        return statusRumah;
    }

    public void setStatusRumah(String statusRumah) {
        this.statusRumah = statusRumah;
    }
}
