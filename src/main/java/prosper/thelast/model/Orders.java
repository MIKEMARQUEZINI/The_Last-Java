package prosper.thelast.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Orders {

    private String id;
    private String productName;
    private String pointSales;
    private int quantity;

    @Override
    public String toString() {
        return "Orders{" +
                "productName:'" + productName + '\'' +
                ", quantity:'" + quantity + '\'' +
                ", pointSales:" + pointSales + '}';
    }

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
