package kurs.models;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class Search {

    private int id;

    private List<Integer> rooms;

    private int quantity;

    @NotEmpty(message = "LUL")
    private String data1;

    @NotEmpty(message = "LUL")
    private String data2;

    private int price;

    public Search(){

    }

    public Search(int id) {
        this.id = id;
    }

    public Search(int id, List<Integer> hotels, int quantity, String data1, String data2,int price){
        this.id = id;
        this.rooms = hotels;
        this.quantity = quantity;
        this.data1 = data1;
        this.data2 = data2;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getRooms() {
        return rooms;
    }

    public void setRooms(List<Integer> rooms) {
        this.rooms = rooms;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getData1() {
        return data1;
    }

    public String getData2() {
        return data2;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setData1(String data1) {
        this.data1 = data1;
    }

    public void setData2(String data2) {
        this.data2 = data2;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
