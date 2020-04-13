package Db;


public class Record {
    private int id;
    private String date;
    private float muniWas;
    private float muniNow;
    private float muniBatarei;
    private float masloWas;
    private float masloNow;
    private float ro;
    private float suhoyOstatok;
    private float addMaslo;
    private float addPolimer;
    private String usrN;

    public Record(int id, String date, float muniWas, float muniNow, float muniBatarei, float masloWas, float masloNow, float ro, float suhoyOstatok, float addMaslo, float addPolimer, String usrN) {
        this.id = id;
        this.date = date;
        this.muniWas = muniWas;
        this.muniNow = muniNow;
        this.muniBatarei = muniBatarei;
        this.masloWas = masloWas;
        this.masloNow = masloNow;
        this.ro = ro;
        this.suhoyOstatok = suhoyOstatok;
        this.addMaslo = addMaslo;
        this.addPolimer = addPolimer;
        this.usrN = usrN;
    }

    public Record(float muniWas, float muniNow, float muniBatarei, float masloWas, float masloNow, float ro, float suhoyOstatok, float addMaslo, float addPolimer, String usrN) {
        this.muniWas = muniWas;
        this.muniNow = muniNow;
        this.muniBatarei = muniBatarei;
        this.masloWas = masloWas;
        this.masloNow = masloNow;
        this.ro = ro;
        this.suhoyOstatok = suhoyOstatok;
        this.addMaslo = addMaslo;
        this.addPolimer = addPolimer;
        this.usrN = usrN;
    }

    public String getUsrN() {
        return usrN;
    }

    public void setUsrN(String usrN) {
        this.usrN = usrN;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", muniWas=" + muniWas +
                ", muniNow=" + muniNow +
                ", muniBatarei=" + muniBatarei +
                ", masloWas=" + masloWas +
                ", masloNow=" + masloNow +
                ", ro=" + ro +
                ", suhoyOstatok=" + suhoyOstatok +
                ", addMaslo=" + addMaslo +
                ", addPolimer=" + addPolimer +
                ", usrN='" + usrN + '\'' +
                '}' + System.lineSeparator();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getMuniWas() {
        return muniWas;
    }

    public void setMuniWas(float muniWas) {
        this.muniWas = muniWas;
    }

    public float getMuniNow() {
        return muniNow;
    }

    public void setMuniNow(float muniNow) {
        this.muniNow = muniNow;
    }

    public float getMuniBatarei() {
        return muniBatarei;
    }

    public void setMuniBatarei(float muniBatarei) {
        this.muniBatarei = muniBatarei;
    }

    public float getMasloWas() {
        return masloWas;
    }

    public void setMasloWas(float masloWas) {
        this.masloWas = masloWas;
    }

    public float getMasloNow() {
        return masloNow;
    }

    public void setMasloNow(float masloNow) {
        this.masloNow = masloNow;
    }

    public float getRo() {
        return ro;
    }

    public void setRo(float ro) {
        this.ro = ro;
    }

    public float getSuhoyOstatok() {
        return suhoyOstatok;
    }

    public void setSuhoyOstatok(float suhoyOstatok) {
        this.suhoyOstatok = suhoyOstatok;
    }

    public float getAddMaslo() {
        return addMaslo;
    }

    public void setAddMaslo(float addMaslo) {
        this.addMaslo = addMaslo;
    }

    public float getAddPolimer() {
        return addPolimer;
    }

    public void setAddPolimer(float addPolimer) {
        this.addPolimer = addPolimer;
    }
}
