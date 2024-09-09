package ra.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class User {
    private long userId;
    private String username;
    private String email;
    private String fullname;
    private boolean status;
    private String password;
    private String avatar;
    private String phone;
    private String address;
    private Date createdAt;
    private Date updatedAt;
    public boolean isDeleted; // Mặc định là 0: Hoạt động, 1: Bị xóa


    public User(String username, String email, String fullname, String password, String phone, String address) {
        this.userId = generateUserId();
        try {
            setUsername(username);
            setEmail(email);
            setFullname(fullname);
            setPassword(password);
            setPhone(phone);
            setAddress(address);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
        this.avatar = "";
        this.status = true;
        this.createdAt = new Date();
        this.updatedAt = new Date();
        this.isDeleted = false;
    }


    // Method tạo ID người dùng (có thể là từ CSDL hoặc tăng tự động)
    private long generateUserId() {
        return System.currentTimeMillis(); // Tạo ID dựa trên thời gian hiện tại
    }

    // Getter và Setter cho username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username.length() < 6 || username.length() > 100) {
            throw new IllegalArgumentException("Username phải từ 6 đến 100 ký tự");
        }
        if (!Pattern.matches("[a-zA-Z0-9]+", username)) {
            throw new IllegalArgumentException("Username không được có ký tự đặc biệt");
        }
        this.username = username;
    }

    // Getter và Setter cho email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (!Pattern.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", email)) {
            throw new IllegalArgumentException("Email không hợp lệ");
        }
        this.email = email;
    }

    // Getter và Setter cho fullname
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        if (fullname == null || fullname.isEmpty()) {
            throw new IllegalArgumentException("Fullname không được để trống");
        }
        this.fullname = fullname;
    }

    // Getter và Setter cho password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password.length() < 6) {
            throw new IllegalArgumentException("Password phải có ít nhất 6 ký tự");
        }
        this.password = encryptPassword(password);
    }

    // Giả lập phương thức mã hóa mật khẩu
    private String encryptPassword(String password) {
        return Integer.toHexString(password.hashCode()); // Mã hóa đơn giản bằng hash
    }

    // Getter và Setter cho phone
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (!Pattern.matches("^(0|\\+84)(3|5|7|8|9)[0-9]{8}$", phone)) {
            throw new IllegalArgumentException("Số điện thoại không hợp lệ");
        }
        this.phone = phone;
    }

    // Getter và Setter cho address
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address == null || address.isEmpty()) {
            throw new IllegalArgumentException("Địa chỉ không được để trống");
        }
        this.address = address;
    }


    public void update() {
        this.updatedAt = new Date(); // Ngày hiện tại
    }

    // Hiển thị thông tin người dùng
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", fullname='" + fullname + '\'' +
                ", status=" + (status ? "Active" : "Blocked") +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", createdAt=" + dateFormat.format(createdAt) +
                ", updatedAt=" + dateFormat.format(updatedAt) +
                ", isDeleted=" + (isDeleted ? "Deleted" : "Active") +
                '}';
    }
}
