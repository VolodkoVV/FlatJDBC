package vyacheslav.volodko;

public class Flat {
    private int id;
    private String district;
    private String street;
    private int room;
    private double prise;

    public Flat(String district, String street, int room, double prise) {
        this.district = district;
        this.street = street;
        this.room = room;
        this.prise = prise;
    }

    public Flat() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public double getPrise(double aDouble) {
        return prise;
    }

    public void setPrise(double prise) {
        this.prise = prise;
    }

    @Override
    public String toString() {
        return "Flat{" +
                "id=" + id +
                ", district='" + district + '\'' +
                ", street='" + street + '\'' +
                ", room=" + room +
                ", prise=" + prise +
                '}';
    }
}
