package ra.entity;

public class Address {
    private long addressId; // ID địa chỉ, khóa chính, tự động tăng
    private long userId; // ID người dùng, khóa ngoại
    private String fullAddress; // Địa chỉ chi tiết
    private String phone; // Số điện thoại
    private String receiveName; // Tên người nhận

    // Constructor
    public Address(long userId, String fullAddress, String phone, String receiveName) {
        this.addressId = generateAddressId(); // ID tự động tăng
        try {
            setUserId(userId); // Validate userId
            setFullAddress(fullAddress); // Validate địa chỉ
            setPhone(phone); // Validate số điện thoại
            setReceiveName(receiveName); // Validate tên người nhận
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    // Phương thức tạo ID địa chỉ tự động tăng (giả lập)
    private long generateAddressId() {
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

    // Getter và Setter cho fullAddress
    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        if (fullAddress == null || fullAddress.isEmpty()) {
            throw new IllegalArgumentException("Địa chỉ chi tiết không được để trống");
        }
        if (fullAddress.length() > 255) {
            throw new IllegalArgumentException("Địa chỉ chi tiết không được vượt quá 255 ký tự");
        }
        this.fullAddress = fullAddress;
    }

    // Getter và Setter cho phone
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone == null || phone.isEmpty()) {
            throw new IllegalArgumentException("Số điện thoại không được để trống");
        }
        if (!phone.matches("\\d{10,15}")) {
            throw new IllegalArgumentException("Số điện thoại không hợp lệ, phải chứa từ 10 đến 15 chữ số");
        }
        this.phone = phone;
    }

    // Getter và Setter cho receiveName
    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        if (receiveName == null || receiveName.isEmpty()) {
            throw new IllegalArgumentException("Tên người nhận không được để trống");
        }
        if (receiveName.length() > 50) {
            throw new IllegalArgumentException("Tên người nhận không được vượt quá 50 ký tự");
        }
        this.receiveName = receiveName;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", userId=" + userId +
                ", fullAddress='" + fullAddress + '\'' +
                ", phone='" + phone + '\'' +
                ", receiveName='" + receiveName + '\'' +
                '}';
    }
}
