package prosper.thelast.DTO.Orders;

public class OrdersDTO {
    private String id;
    private String productName;
    private String pointSales;
    private int quantity;

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
}
