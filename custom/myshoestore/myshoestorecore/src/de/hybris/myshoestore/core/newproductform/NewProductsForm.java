package de.hybris.myshoestore.core.newproductform;

public class NewProductsForm {

    private String code;
    private String name;
    private String description;
    private String size;
    private String color;

    public Integer getPurchaseDate() {
        return PurchaseDate;
    }

    public void setPurchaseDate(Integer purchaseDate) {
        PurchaseDate = purchaseDate;
    }

    private Integer PurchaseDate;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
