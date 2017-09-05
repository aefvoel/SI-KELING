package tiregdev.sipepikeling.model;

/**
 * Created by SONY-VAIO on 9/4/2017.
 */

public class PKL {
    private String nama;
    private String umur;
    private String jenisKelamin;
    private String alamat;
    private String kasus;
    private String jenis;
    private String namaTempat;
    private String idPetugas;
    private String koordinat;
    private String waktu;


    public PKL(String nama, String umur, String jenisKelamin, String alamat, String kasus, String jenis, String namaTempat, String idPetugas, String koordinat, String waktu) {
        this.nama = nama;
        this.umur = umur;
        this.jenisKelamin = jenisKelamin;
        this.alamat = alamat;
        this.kasus = kasus;
        this.jenis = jenis;
        this.namaTempat = namaTempat;
        this.idPetugas = idPetugas;
        this.koordinat = koordinat;
        this.waktu = waktu;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKasus() {
        return kasus;
    }

    public void setKasus(String kasus) {
        this.kasus = kasus;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getNamaTempat() {
        return namaTempat;
    }

    public void setNamaTempat(String namaTempat) {
        this.namaTempat = namaTempat;
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
}
