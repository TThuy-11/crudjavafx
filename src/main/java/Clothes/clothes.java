package Clothes;

public class clothes {
    public int id;
    public String name;
    public String img;
    public String size;
    public Float price;

    public clothes(int id, String name, String img, String size, float price){
        this.id=id;
        this.name=name;
        this.img=img;
        this.size=size;
        this.price=price;
    }
    public clothes( String name, String img, String size, float price){
        this.name=name;
        this.img=img;
        this.size=size;
        this.price=price;
    }

    public clothes(String name, String img, String size, float price, int id) {
        this.name=name;
        this.img=img;
        this.size=size;
        this.price=price;
        this.id=id;
    }
}
