package entity;

public class ApartmentType {
    private int id;
    private String type;
    private Double price;

    public ApartmentType() {
    }

    public ApartmentType(int id, String type, Double price) {
        this.id = id;
        this.type = type;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ApartmentType{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", price=" + price +
                '}';
    }
}
