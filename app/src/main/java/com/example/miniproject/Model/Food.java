package com.example.miniproject.Model;

public class Food {
     private String description, discount, image, menuId, name, price;
     public Food(){

     }
     public Food(String name1,String image1,String description1,String price1, String discount1, String menuId1){
         name =name1;
         image = image1;
         description = description1;
         price = price1;
         discount = discount1;
         menuId = menuId1;
     }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public String getDiscount() {
        return discount;
    }

    public String getMenuId() {
        return menuId;
    }

    public String getPrice() {
        return price;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
