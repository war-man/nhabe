package vn.com.mattana.model.api.order;

import com.google.gson.annotations.SerializedName;

/**
 * Created by HAI on 2/26/2018.
 */

public class ProductInfo {

    @SerializedName("id")
    private String id ;
    @SerializedName("code")
    private String code ;
    @SerializedName("name")
    private String name;
    @SerializedName("price")
    private double price ;

    @SerializedName("type")
    private String type;

    private int quantityBuy;

    @SerializedName("size")
    private String size;

    @SerializedName("mainCode")
    private String mainCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantityBuy() {
        return quantityBuy;
    }

    public void setQuantityBuy(int quantityBuy) {
        this.quantityBuy = quantityBuy;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMainCode() {
        return mainCode;
    }

    public void setMainCode(String mainCode) {
        this.mainCode = mainCode;
    }
}
