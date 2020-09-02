package del.ac.id.main4;

public abstract class MovingObject {
    private float beratKendaraan, percepatan;

    public void setBeratKendaraan(float value){
        beratKendaraan = value;
    }

    public void setPercepatan(float percepatan) {
        this.percepatan = percepatan;
    }

    public float getBeratKendaraan() {
        return beratKendaraan;
    }

    public float getPercepatan() {
        return percepatan;
    }

    public float getGayaPergerakan(){
        return getBeratKendaraan() * getPercepatan();
    }

    abstract void caraMerakitObjekBergerak();
}
