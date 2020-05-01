package com.example.wanghui.choosemenu.model;

public class Food {

    private  String name;
    private  int  price;
    private  int pic;
    private  boolean isHotFood;
    private  boolean  isFishFood;
    private  boolean  isSourFood;

    public Food(String name, int price, int pic, boolean isHotFood, boolean isFishFood, boolean isSourFood) {
        this.name = name;
        this.price = price;
        this.pic = pic;
        this.isHotFood = isHotFood;
        this.isFishFood = isFishFood;
        this.isSourFood = isSourFood;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getPic() {
        return pic;
    }

    public boolean isHotFood() {
        return isHotFood;
    }

    public boolean isFishFood() {
        return isFishFood;
    }

    public boolean isSourFood() {
        return isSourFood;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public void setHotFood(boolean hotFood) {
        isHotFood = hotFood;
    }

    public void setFishFood(boolean fishFood) {
        isFishFood = fishFood;
    }

    public void setSourFood(boolean sourFood) {
        isSourFood = sourFood;
    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", pic=" + pic +
                ", isHotFood=" + isHotFood +
                ", isFishFood=" + isFishFood +
                ", isSourFood=" + isSourFood +
                '}';
    }
}
