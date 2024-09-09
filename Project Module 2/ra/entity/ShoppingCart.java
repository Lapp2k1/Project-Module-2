package ra.entity;

public class ShoppingCart {
    private int shoppingCartId; // Khóa chính, tự động tăng
    private long productId; // ID sản phẩm, khóa ngoại
    private long userId; // ID người dùng, khóa ngoại
    private int orderQuantity; // Số lượng, giá trị > 0

    // Constructor
    public ShoppingCart(long productId, long userId, int orderQuantity) {
        this.shoppingCartId = generateShoppingCartId(); // Tạo ID tự động tăng
        try {
            setProductId(productId); // Validate productId
            setUserId(userId); // Validate userId
            setOrderQuantity(orderQuantity); // Validate số lượng đặt hàng
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    // Phương thức tạo ID giỏ hàng tự động tăng (giả lập)
    private int generateShoppingCartId() {
        return (int) (System.currentTimeMillis() % Integer.MAX_VALUE); // ID tự động tăng dựa trên thời gian
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

    // Getter và Setter cho userId
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        if (userId <= 0) {
            throw new IllegalArgumentException("ID người dùng không được nhỏ hơn hoặc bằng 0");
        }
        this.userId = userId;
    }

    // Getter và Setter cho orderQuantity
    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        if (orderQuantity <= 0) {
            throw new IllegalArgumentException("Số lượng phải lớn hơn 0");
        }
        this.orderQuantity = orderQuantity;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "shoppingCartId=" + shoppingCartId +
                ", productId=" + productId +
                ", userId=" + userId +
                ", orderQuantity=" + orderQuantity +
                '}';
    }
}
