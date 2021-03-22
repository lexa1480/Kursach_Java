package kurs.models;

public class Bron {

    private int id;

    private int u_id;

    private int r_id;

    private String data1;

    private String data2;

    private int price;



    private String NameH;

    private Integer NumR;


    public Bron() {
    }

    public Bron(int id, int u_id, int r_id, String data1, String data2, int price, String NameH, Integer NumR) {
        this.id = id;
        this.u_id = u_id;
        this.r_id = r_id;
        this.data1 = data1;
        this.data2 = data2;
        this.price = price;
        this.NameH = NameH;
        this.NumR = NumR;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public int getR_id() {
        return r_id;
    }

    public void setR_id(int r_id) {
        this.r_id = r_id;
    }

    public String getData1() {
        return data1;
    }

    public void setData1(String data1) {
        this.data1 = data1;
    }

    public String getData2() {
        return data2;
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

    public String getNameH() {
        return NameH;
    }

    public void setNameH(String nameH) {
        NameH = nameH;
    }

    public Integer getNumR() {
        return NumR;
    }

    public void setNumR(Integer numR) {
        NumR = numR;
    }
}
