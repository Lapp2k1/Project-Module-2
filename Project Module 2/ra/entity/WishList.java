package ra.entity;

public class WishList {
    private long wishListId; // ID chi tiết yêu thích, khóa chính, tự động tăng
    private long userId; // ID người dùng, khóa ngoại
    private long productId; // ID sản phẩm, khóa ngoại

    // Constructor
    public WishList(long userId, long productId) {
        this.wishListId = generateWishListId(); // ID tự động tăng
        try {
            setUserId(userId); // Validate userId
            setProductId(productId); // Validate productId
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    // Phương thức tạo ID danh sách yêu thích tự động tăng (giả lập)
    private long generateWishListId() {
        return System.currentTimeMillis(); // ID tự động tăng dựa trên thời gian
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

    @Override
    public String toString() {
        return "WishList{" +
                "wishListId=" + wishListId +
                ", userId=" + userId +
                ", productId=" + productId +
                '}';
    }
}
