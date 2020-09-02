package del.ac.id.yosepri;

public class IdNotFoundEksepsi extends Exception {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
