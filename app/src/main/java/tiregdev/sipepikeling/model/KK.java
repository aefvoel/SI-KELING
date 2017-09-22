package tiregdev.sipepikeling.model;

/**
 * Created by SONY-VAIO on 8/25/2017.
 */

public class KK {

    private String namaPerKK;
    private String jumlahAnggotaPerKK;
    private String idPetugas;
    private String idRS;
    private String nik;
    private String noKIS;

    public KK(String namaPerKK, String jumlahAnggotaPerKK, String idPetugas, String idRS, String nik) {
        this.namaPerKK = namaPerKK;
        this.jumlahAnggotaPerKK = jumlahAnggotaPerKK;
        this.idPetugas = idPetugas;
        this.idRS = idRS;
        this.nik = nik;
    }

    public String getNamaPerKK() {
        return namaPerKK;
    }

    public void setNamaPerKK(String namaPerKK) {
        this.namaPerKK = namaPerKK;
    }

    public String getJumlahAnggotaPerKK() {
        return jumlahAnggotaPerKK;
    }

    public void setJumlahAnggotaPerKK(String jumlahAnggotaPerKK) {
        this.jumlahAnggotaPerKK = jumlahAnggotaPerKK;
    }

    public String getIdPetugas() {
        return idPetugas;
    }

    public void setIdPetugas(String idPetugas) {
        this.idPetugas = idPetugas;
    }

    public String getIdRS() {
        return idRS;
    }

    public void setIdRS(String idRS) {
        this.idRS = idRS;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNoKIS() {
        return noKIS;
    }

    public void setNoKIS(String noKIS) {
        this.noKIS = noKIS;
    }
}
