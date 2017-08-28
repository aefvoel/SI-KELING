package tiregdev.sipepikeling.model;

/**
 * Created by SONY-VAIO on 8/25/2017.
 */

public class KK {

    private String alamat;
    private String namaKK;
    private String jumlahAnggota;
    private String noRumah;
    private String idPetugas;

    public KK(String alamat, String namaKK, String jumlahAnggota, String noRumah, String idPetugas) {
        this.alamat = alamat;
        this.namaKK = namaKK;
        this.jumlahAnggota = jumlahAnggota;
        this.noRumah = noRumah;
        this.idPetugas = idPetugas;
    }

    public String getIdPetugas() {
        return idPetugas;
    }

    public void setIdPetugas(String idPetugas) {
        this.idPetugas = idPetugas;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNamaKK() {
        return namaKK;
    }

    public void setNamaKK(String namaKK) {
        this.namaKK = namaKK;
    }

    public String getJumlahAnggota() {
        return jumlahAnggota;
    }

    public void setJumlahAnggota(String jumlahAnggota) {
        this.jumlahAnggota = jumlahAnggota;
    }

    public String getNoRumah() {
        return noRumah;
    }

    public void setNoRumah(String noRumah) {
        this.noRumah = noRumah;
    }
}
