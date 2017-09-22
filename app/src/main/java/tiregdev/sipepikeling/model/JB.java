package tiregdev.sipepikeling.model;

/**
 * Created by SONY-VAIO on 9/14/2017.
 */

public class JB {
    private String namaTempat;
    private String noTelp;
    private String namaPengusaha;
    private String idPetugas;
    private String waktu;
    private String alamat;
    private String koordinat;
    private double totalNilai;
    private String status;

    public JB(String namaTempat, String noTelp, String namaPengusaha, String idPetugas, String waktu, String alamat, String koordinat, double totalNilai, String status) {
        this.namaTempat = namaTempat;
        this.noTelp = noTelp;
        this.namaPengusaha = namaPengusaha;
        this.idPetugas = idPetugas;
        this.waktu = waktu;
        this.alamat = alamat;
        this.koordinat = koordinat;
        this.totalNilai = totalNilai;
        this.status = status;
    }

    public String getNamaTempat() {
        return namaTempat;
    }

    public void setNamaTempat(String namaTempat) {
        this.namaTempat = namaTempat;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getNamaPengusaha() {
        return namaPengusaha;
    }

    public void setNamaPengusaha(String namaPengusaha) {
        this.namaPengusaha = namaPengusaha;
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
