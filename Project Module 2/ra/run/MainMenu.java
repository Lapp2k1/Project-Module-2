package ra.run;

import ra.service.UserService;
import ra.service.ProductService;
import ra.service.CartService;
import ra.service.OrderService;
import ra.service.WishListService;
import ra.service.AdminService;

import java.util.Scanner;

public class MainMenu {

    private static final Scanner scanner = new Scanner(System.in);
    private static UserService userService = new UserService();
    private static ProductService productService = new ProductService();
    private static CartService cartService = new CartService();
    private static OrderService orderService = new OrderService();
    private static WishListService wishListService = new WishListService();
    private static AdminService adminService = new AdminService();
    private static boolean isLoggedIn = false;
    private static String currentUserRole = "";

    public static void main(String[] args) {
        while (true) {
            if (!isLoggedIn) {
                showInitialMenu();
            } else {
                if ("USER".equals(currentUserRole)) {
                    showUserMenu();
                } else if ("ADMIN".equals(currentUserRole)) {
                    showAdminMenu();
                }
            }
        }
    }

    private static void showInitialMenu() {
        System.out.println("Menu chính:");
        System.out.println("1. Đăng ký");
        System.out.println("2. Đăng nhập");
        System.out.println("3. Hiển thị sản phẩm được bán");
        System.out.println("4. Quên mật khẩu");
        System.out.println("5. Thoát");
        System.out.print("Chọn chức năng: ");
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        switch (choice) {
            case 1:
                userService.registerUser();
                break;
            case 2:
                isLoggedIn = userService.loginUser();
                if (isLoggedIn) {
                    currentUserRole = userService.getCurrentUserRole();
                }
                break;
            case 3:
                productService.displayAvailableProducts();
                break;
            case 4:
                userService.resetPassword();
                break;
            case 5:
                System.out.println("Thoát chương trình.");
                System.exit(0);
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
        }
    }

    private static void showUserMenu() {
        System.out.println("Menu người dùng:");
        System.out.println("1. Hiển thị danh sách sản phẩm");
        System.out.println("2. Xem thông tin cá nhân");
        System.out.println("3. Quản lý giỏ hàng");
        System.out.println("4. Quản lý đơn hàng");
        System.out.println("5. Quản lý danh sách yêu thích");
        System.out.println("6. Đăng xuất");
        System.out.print("Chọn chức năng: ");
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        switch (choice) {
            case 1:
                productService.displayAllProducts();
                break;
            case 2:
                userService.manageProfile();
                break;
            case 3:
                cartService.manageCart();
                break;
            case 4:
                orderService.manageOrders();
                break;
            case 5:
                wishListService.manageWishList();
                break;
            case 6:
                isLoggedIn = false;
                currentUserRole = "";
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
        }
    }

    private static void showAdminMenu() {
        System.out.println("Menu quản trị viên:");
        System.out.println("1. Quản lý danh mục");
        System.out.println("2. Quản lý sản phẩm");
        System.out.println("3. Quản lý đơn hàng");
        System.out.println("4. Quản lý người dùng");
        System.out.println("5. Thống kê");
        System.out.println("6. Đăng xuất");
        System.out.print("Chọn chức năng: ");
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        switch (choice) {
            case 1:
                adminService.manageCategories();
                break;
            case 2:
                adminService.manageProducts();
                break;
            case 3:
                adminService.manageOrders();
                break;
            case 4:
                adminService.manageUsers();
                break;
            case 5:
                adminService.viewStatistics();
                break;
            case 6:
                isLoggedIn = false;
                currentUserRole = "";
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
        }
    }
}
