package ra.entity;

public class Role {
    private long roleId;
    private RoleName roleName;


    public enum RoleName {
        ADMIN, USER, MANAGER;
    }

    // Constructor
    public Role(RoleName roleName) {
        this.roleId = generateRoleId();
        try {
            setRoleName(roleName);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    // Phương thức tạo ID cho quyền (có thể từ cơ sở dữ liệu hoặc tăng tự động)
    private long generateRoleId() {
        return System.currentTimeMillis(); // Giả lập ID tự động tăng bằng thời gian hiện tại
    }

    // Getter và Setter cho roleName
    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        if (roleName == null) {
            throw new IllegalArgumentException("Tên quyền không được để trống");
        }
        this.roleName = roleName;
    }

    // Hiển thị thông tin quyền
    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName=" + roleName +
                '}';
    }
}
