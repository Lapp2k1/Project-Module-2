package ra.entity;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Order {
    private long orderId; // Khóa chính, tự động tăng
    private String serialNumber; // Mã đơn hàng, UUID ngẫu nhiên
    private long userId; // ID người đặt hàng, không được null
    private BigDecimal totalPrice; // Tổng tiền
    private OrderStatus status; // Trạng thái đơn hàng
    private String note; // Ghi chú
    private String receiveName; // Tên người nhận
    private String receiveAddress; // Địa chỉ người nhận
    private String receivePhone; // Số điện thoại người nhận
    private Date createdAt; // Ngày tạo đơn hàng, mặc định là ngày hiện tại
    private Date receivedAt; // Thời gian giao hàng dự kiến, 4 ngày sau khi đặt hàng

    // Enum để định nghĩa trạng thái đơn hàng
    public enum OrderStatus {
        WAITING, CONFIRM, DELIVERY, SUCCESS, CANCEL, DENIED
    }

    // Constructor
    public Order(long userId, BigDecimal totalPrice, OrderStatus status, String note, String receiveName, String receiveAddress, String receivePhone) {
        this.orderId = generateOrderId(); // Tạo ID đơn hàng
        this.serialNumber = generateSerialNumber(); // Tạo UUID ngẫu nhiên cho mã đơn hàng
        try {
            setUserId(userId); // Validate userId
            setTotalPrice(totalPrice); // Validate tổng tiền
            setStatus(status); // Validate trạng thái đơn hàng
            setNote(note); // Ghi chú có thể null
            setReceiveName(receiveName); // Validate tên người nhận
            setReceiveAddress(receiveAddress); // Validate địa chỉ người nhận
            setReceivePhone(receivePhone); // Validate số điện thoại người nhận
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
        this.createdAt = new Date(); // Ngày hiện tại
        this.receivedAt = calculateReceivedAt(); // Ngày dự kiến giao hàng (4 ngày sau)
    }

    // Phương thức tạo ID đơn hàng
    private long generateOrderId() {
        return System.currentTimeMillis(); // ID dựa trên thời gian hiện tại
    }

    // Phương thức tạo serial number ngẫu nhiên bằng UUID
    private String generateSerialNumber() {
        return UUID.randomUUID().toString();
    }

    // Phương thức tính ngày dự kiến giao hàng (4 ngày sau)
    private Date calculateReceivedAt() {
        Date date = new Date();
        long time = date.getTime();
        return new Date(time + 4 * 24 * 60 * 60 * 1000); // Thêm 4 ngày
    }

    // Getter và Setter cho userId
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        if (userId <= 0) {
            throw new IllegalArgumentException("ID người đặt hàng không được để trống hoặc nhỏ hơn 0");
        }
        this.userId = userId;
    }

    // Getter và Setter cho totalPrice
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        if (totalPrice == null || totalPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Tổng tiền không được nhỏ hơn 0");
        }
        this.totalPrice = totalPrice;
    }

    // Getter và Setter cho status
    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Trạng thái đơn hàng không được để trống");
        }
        this.status = status;
    }

    // Getter và Setter cho note
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        if (note != null && note.length() > 100) {
            throw new IllegalArgumentException("Ghi chú không được vượt quá 100 ký tự");
        }
        this.note = note; // Ghi chú có thể để trống
    }

    // Getter và Setter cho receiveName
    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        if (receiveName == null || receiveName.isEmpty()) {
            throw new IllegalArgumentException("Tên người nhận không được để trống");
        }
        if (receiveName.length() > 100) {
            throw new IllegalArgumentException("Tên người nhận không được vượt quá 100 ký tự");
        }
        this.receiveName = receiveName;
    }

    // Getter và Setter cho receiveAddress
    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        if (receiveAddress == null || receiveAddress.isEmpty()) {
            throw new IllegalArgumentException("Địa chỉ người nhận không được để trống");
        }
        if (receiveAddress.length() > 255) {
            throw new IllegalArgumentException("Địa chỉ không được vượt quá 255 ký tự");
        }
        this.receiveAddress = receiveAddress;
    }

    // Getter và Setter cho receivePhone
    public String getReceivePhone() {
        return receivePhone;
    }

    public void setReceivePhone(String receivePhone) {
        if (receivePhone == null || !receivePhone.matches("\\d{10,15}")) {
            throw new IllegalArgumentException("Số điện thoại phải có từ 10 đến 15 chữ số");
        }
        this.receivePhone = receivePhone;
    }

    // Getter và Setter cho createdAt
    public Date getCreatedAt() {
        return createdAt;
    }

    // Getter và Setter cho receivedAt
    public Date getReceivedAt() {
        return receivedAt;
    }

    // Phương thức hiển thị thông tin đơn hàng
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return "Order{" +
                "orderId=" + orderId +
                ", serialNumber='" + serialNumber + '\'' +
                ", userId=" + userId +
                ", totalPrice=" + totalPrice +
                ", status=" + status +
                ", note='" + note + '\'' +
                ", receiveName='" + receiveName + '\'' +
                ", receiveAddress='" + receiveAddress + '\'' +
                ", receivePhone='" + receivePhone + '\'' +
                ", createdAt=" + dateFormat.format(createdAt) +
                ", receivedAt=" + dateFormat.format(receivedAt) +
                '}';
    }
}
