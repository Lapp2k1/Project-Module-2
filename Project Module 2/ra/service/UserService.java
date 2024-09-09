package ra.service;

import ra.entity.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserService {
    private static final Scanner scanner = new Scanner(System.in);
    private static Map<String, User> users = new HashMap<>();

    public void registerUser() {
        System.out.print("Nhập username: ");
        String username = scanner.nextLine();
        System.out.print("Nhập email: ");
        String email = scanner.nextLine();
        System.out.print("Nhập password: ");
        String password = scanner.nextLine();

        if (users.containsKey(username)) {
            System.out.println("Tài khoản đã tồn tại!");
            return;
        }

        // Create a new user and add to the map
        User user = new User((long) (users.size() + 1), username, email, password, "USER", false);
        users.put(username, user);
        System.out.println("Đăng ký thành công!");
    }

    public boolean loginUser() {
        System.out.print("Nhập username: ");
        String username = scanner.nextLine();
        System.out.print("Nhập password: ");
        String password = scanner.nextLine();

        User user = users.get(username);
        if (user == null) {
            System.out.println("Tài khoản không tồn tại!");
            return false;
        }
        if (user.isBlocked()) {
            System.out.println("Tài khoản bị khóa!");
            return false;
        }
        if (user.getPassword().equals(password)) {
            System.out.println("Đăng nhập thành công!");
            return true;
        } else {
            System.out.println("Mật khẩu không đúng!");
            return false;
        }
    }

    public void updateUserDetails() {
        System.out.print("Nhập username cần cập nhật: ");
        String username = scanner.nextLine();
        User user = users.get(username);

        if (user == null) {
            System.out.println("Tài khoản không tồn tại!");
            return;
        }

        System.out.print("Nhập email mới: ");
        String email = scanner.nextLine();
        System.out.print("Nhập mật khẩu mới: ");
        String password = scanner.nextLine();
        System.out.print("Nhập vai trò mới (USER/ADMIN): ");
        String role = scanner.nextLine();

        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);
        System.out.println("Cập nhật thành công!");
    }

    public void blockUser() {
        System.out.print("Nhập username cần khóa: ");
        String username = scanner.nextLine();
        User user = users.get(username);

        if (user == null) {
            System.out.println("Tài khoản không tồn tại!");
            return;
        }

        user.setBlocked(true);
        System.out.println("Khóa tài khoản thành công!");
    }

    public void viewUsers() {
        if (users.isEmpty()) {
            System.out.println("Không có người dùng nào!");
            return;
        }
        users.values().forEach(System.out::println);
    }
}
