package ra.entity;

public class Category {
    private long categoryId; // Khóa chính, tự động tăng
    private String categoryName; // Tên danh mục, không được để trống
    private String description; // Mô tả danh mục
    private boolean status; // Trạng thái danh mục (True: Active, False: Inactive)

    // Constructor
    public Category(String categoryName, String description, boolean status) {
        this.categoryId = generateCategoryId();
        try {
            setCategoryName(categoryName);
            setDescription(description);
            setStatus(status);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    // Phương thức tạo ID cho danh mục (có thể từ cơ sở dữ liệu hoặc tăng tự động)
    private long generateCategoryId() {
        return System.currentTimeMillis(); // Giả lập ID tự động tăng bằng thời gian hiện tại
    }

    // Getter và Setter cho categoryName
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        if (categoryName == null || categoryName.isEmpty()) {
            throw new IllegalArgumentException("Tên danh mục không được để trống");
        }
        if (categoryName.length() > 100) {
            throw new IllegalArgumentException("Tên danh mục không được vượt quá 100 ký tự");
        }
        this.categoryName = categoryName;
    }

    // Getter và Setter cho description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description; // Mô tả có thể để trống
    }

    // Getter và Setter cho status
    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status; // True: Active, False: Inactive
    }

    // Hiển thị thông tin danh mục
    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", description='" + description + '\'' +
                ", status=" + (status ? "Active" : "Inactive") +
                '}';
    }
}
