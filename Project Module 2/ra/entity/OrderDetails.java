package ra.entity;

import java.math.BigDecimal;

public class OrderDetails {
    private long orderId; // Khóa ngoại, khóa chính
    private long productId; // Khóa ngoại, khóa chính
    private String productName; // Tên sản phẩm
    private BigDecimal unitPrice; // Đơn giá
    private int orderQuantity; // Số lượng đặt hàng, giá trị > 0

    // Constructor
    public OrderDetails(long orderId, long productId, String productName, BigDecimal unitPrice, int orderQuantity) {
        try {
            setOrderId(orderId); // Validate orderId
            setProductId(productId); // Validate productId
            setProductName(productName); // Validate tên sản phẩm
            setUnitPrice(unitPrice); // Validate đơn giá
            setOrderQuantity(orderQuantity); // Validate số lượng đặt hàng
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    // Getter và Setter cho orderId
    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        if (orderId <= 0) {
            throw new IllegalArgumentException("ID đơn hàng không được nhỏ hơn hoặc bằng 0");
        }
        this.orderId = orderId;
    }

    // Getter và Setter cho productId
    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        if (productId <= 0) {
            throw new IllegalArgumentException("ID sản phẩm không được nhỏ hơn hoặc bằng 0");
        }
        this.productId = productId;
    }

    // Getter và Setter cho productName
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        if (productName == null || productName.isEmpty()) {
            throw new IllegalArgumentException("Tên sản phẩm không được để trống");
        }
        if (productName.length() > 100) {
            throw new IllegalArgumentException("Tên sản phẩm không được vượt quá 100 ký tự");
        }
        this.productName = productName;
    }

    // Getter và Setter cho unitPrice
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        if (unitPrice == null || unitPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Đơn giá không được nhỏ hơn 0");
        }
        this.unitPrice = unitPrice;
    }

    // Getter và Setter cho orderQuantity
    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        if (orderQuantity <= 0) {
            throw new IllegalArgumentException("Số lượng đặt hàng phải lớn hơn 0");
        }
        this.orderQuantity = orderQuantity;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "orderId=" + orderId +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", unitPrice=" + unitPrice +
                ", orderQuantity=" + orderQuantity +
                '}';
    }
}
