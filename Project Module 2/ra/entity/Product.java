package ra.entity;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Product {
    private long productId; // Khóa chính, tự động tăng
    private String sku; // Mã sản phẩm, không trùng nhau, UUID.randomUUID()
    private String productName; // Tên sản phẩm, không trùng nhau, không được để trống
    private String description; // Mô tả sản phẩm
    private BigDecimal unitPrice; // Đơn giá
    private int stockQuantity; // Số lượng tồn kho, >= 0
    private String image; // Hình ảnh sản phẩm
    private long categoryId; // Mã danh mục sản phẩm
    private Date createdAt; // Ngày tạo, mặc định là ngày hiện tại
    private Date updatedAt; // Ngày cập nhật, format dd/MM/yyyy

    // Constructor
    public Product(String productName, String description, BigDecimal unitPrice, int stockQuantity, String image, long categoryId) {
        this.productId = generateProductId(); // Tạo ID sản phẩm
        this.sku = generateSku(); // Tạo SKU ngẫu nhiên
        try {
            setProductName(productName); // Validate tên sản phẩm
            setDescription(description);
            setUnitPrice(unitPrice); // Validate đơn giá
            setStockQuantity(stockQuantity); // Validate số lượng tồn kho
            setImage(image);
            setCategoryId(categoryId); // Validate mã danh mục
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
        this.createdAt = new Date(); // Ngày hiện tại
        this.updatedAt = new Date(); // Ngày hiện tại
    }

    // Phương thức tạo ID sản phẩm
    private long generateProductId() {
        return System.currentTimeMillis(); // Tạo ID dựa trên thời gian hiện tại
    }

    // Phương thức tạo SKU ngẫu nhiên bằng UUID
    private String generateSku() {
        return UUID.randomUUID().toString();
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

    // Getter và Setter cho description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description; // Mô tả có thể để trống
    }

    // Getter và Setter cho unitPrice
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        if (unitPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Đơn giá không được nhỏ hơn 0");
        }
        this.unitPrice = unitPrice;
    }

    // Getter và Setter cho stockQuantity
    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        if (stockQuantity < 0) {
            throw new IllegalArgumentException("Số lượng tồn kho không được nhỏ hơn 0");
        }
        this.stockQuantity = stockQuantity;
    }

    // Getter và Setter cho image
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        if (image != null && image.length() > 255) {
            throw new IllegalArgumentException("Đường dẫn hình ảnh không được vượt quá 255 ký tự");
        }
        this.image = image; // Hình ảnh có thể để trống
    }

    // Getter và Setter cho categoryId
    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        if (categoryId <= 0) {
            throw new IllegalArgumentException("Mã danh mục phải là số dương");
        }
        this.categoryId = categoryId;
    }

    // Getter và Setter cho createdAt
    public Date getCreatedAt() {
        return createdAt;
    }

    // Getter và Setter cho updatedAt
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void update() {
        this.updatedAt = new Date(); // Cập nhật ngày hiện tại
    }

    // Hiển thị thông tin sản phẩm
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return "Product{" +
                "productId=" + productId +
                ", sku='" + sku + '\'' +
                ", productName='" + productName + '\'' +
                ", description='" + description + '\'' +
                ", unitPrice=" + unitPrice +
                ", stockQuantity=" + stockQuantity +
                ", image='" + image + '\'' +
                ", categoryId=" + categoryId +
                ", createdAt=" + dateFormat.format(createdAt) +
                ", updatedAt=" + dateFormat.format(updatedAt) +
                '}';
    }
}
