package kurs.models;

public class Room {

    private int id;

    private int numb;

    private int quantity;

    private String buff;

    private int price;

    private int h_id;

    public Room() {
    }

    public Room(int id,int numb, int quantity, String buff, int price,int h_id) {
        this.id = id;
        this.numb = numb;
        this.quantity = quantity;
        this.buff = buff;
        this.price = price;
        this.h_id = h_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumb() { return numb; }

    public void setNumb(int numb) {
        this.numb = numb;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBuff() {
        return buff;
    }

    public void setBuff(String buff) {
        this.buff = buff;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getH_id() { return h_id; }

    public void setH_id(int h_id) { this.h_id = h_id; }
}
