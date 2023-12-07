package prosper.thelast.DTO.Orders;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

public class OrdersDTO {
    private String id;
    @NotBlank(message = "Enter the product name")
    private String productName;
    private String pointSales;
    @DecimalMin(value = "0", message = "the price must be greater than 0")
    private int quantity;
    private Number latitude;
    private Number longitude;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPointSales() {
        return pointSales;
    }

    public void setPointSales(String pointSales) {
        this.pointSales = pointSales;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Number getLatitude() {
        return latitude;
    }

    public void setLatitude(Number latitude) {
        this.latitude = latitude;
    }

    public Number getLongitude() {
        return longitude;
    }

    public void setLongitude(Number longitude) {
        this.longitude = longitude;
    }
}
