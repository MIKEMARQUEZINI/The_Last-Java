package prosper.thelast.DTO.Orders;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class OrdersDTO {
    private String id;
    @NotBlank(message = "Enter the orders for product name")
    private String productName;
    @Size(min = 10, message = "the pointSales must contain at least 10 characters ")
    private String pointSales;
    @DecimalMin(value = "0", message = "the quantity must be greater than 0")
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
