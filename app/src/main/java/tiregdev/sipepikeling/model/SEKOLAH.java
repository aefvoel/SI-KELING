package tiregdev.sipepikeling.model;

/**
 * Created by SONY-VAIO on 9/17/2017.
 */

public class SEKOLAH {
    private String namaSekolah;
    private String namaKepalaSekolah;
    private String jumlahMuridPerTahun;
    private String tahunBerdiri;
    private String luasBangunan;
    private String luasTanah;
    private String idPetugas;
    private String waktu;
    private String alamat;
    private String koordinat;
    private double totalNilai;
    private String status;

    public SEKOLAH(String namaSekolah, String namaKepalaSekolah, String jumlahMuridPerTahun, String tahunBerdiri, String luasBangunan, String luasTanah, String idPetugas, String waktu, String alamat, String koordinat, double totalNilai, String status) {
        this.namaSekolah = namaSekolah;
        this.namaKepalaSekolah = namaKepalaSekolah;
        this.jumlahMuridPerTahun = jumlahMuridPerTahun;
        this.tahunBerdiri = tahunBerdiri;
        this.luasBangunan = luasBangunan;
        this.luasTanah = luasTanah;
        this.idPetugas = idPetugas;
        this.waktu = waktu;
        this.alamat = alamat;
        this.koordinat = koordinat;
        this.totalNilai = totalNilai;
        this.status = status;
    }

    public String getNamaSekolah() {
        return namaSekolah;
    }

    public void setNamaSekolah(String namaSekolah) {
        this.namaSekolah = namaSekolah;
    }

    public String getNamaKepalaSekolah() {
        return namaKepalaSekolah;
    }

    public void setNamaKepalaSekolah(String namaKepalaSekolah) {
        this.namaKepalaSekolah = namaKepalaSekolah;
    }

    public String getJumlahMuridPerTahun() {
        return jumlahMuridPerTahun;
    }

    public void setJumlahMuridPerTahun(String jumlahMuridPerTahun) {
        this.jumlahMuridPerTahun = jumlahMuridPerTahun;
    }

    public String getTahunBerdiri() {
        return tahunBerdiri;
    }

    public void setTahunBerdiri(String tahunBerdiri) {
        this.tahunBerdiri = tahunBerdiri;
    }

    public String getLuasBangunan() {
        return luasBangunan;
    }

    public void setLuasBangunan(String luasBangunan) {
        this.luasBangunan = luasBangunan;
    }

    public String getLuasTanah() {
        return luasTanah;
    }

    public void setLuasTanah(String luasTanah) {
        this.luasTanah = luasTanah;
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
