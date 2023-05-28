import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class ReservationSystem {
    private Map<String, Boolean> reservations;

    public ReservationSystem() {
        reservations = new HashMap<>();
    }

    public boolean makeReservation(String username) {
        if (!reservations.containsKey(username)) {
            reservations.put(username, true);
            return true;
        }
        return false;
    }

    public boolean cancelReservation(String username) {
        if (reservations.containsKey(username)) {
            reservations.remove(username);
            return true;
        }
        return false;
    }

    public void printReservations() {
        System.out.println("Current Reservations:");
        for (String username : reservations.keySet()) {
            System.out.println(username);
        }
        System.out.println();
    }
}

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

class ReservationApp {
    private Map<String, User> users;
    private User currentUser;
    private ReservationSystem reservationSystem;

    public ReservationApp() {
        users = new HashMap<>();
        currentUser = null;
        reservationSystem = new ReservationSystem();
    }

    public void registerUser(String username, String password) {
        User user = new User(username, password);
        users.put(username, user);
    }

    public boolean login(String username, String password) {
        if (users.containsKey(username)) {
            User user = users.get(username);
            if (user.getPassword().equals(password)) {
                currentUser = user;
                return true;
            }
        }
        return false;
    }

    public void logout() {
        currentUser = null;
    }

    public void makeReservation() {
        if (currentUser != null) {
            String username = currentUser.getUsername();
            if (reservationSystem.makeReservation(username)) {
                System.out.println("Reservation successfully made for user: " + username);
            } else {
                System.out.println("Reservation already exists for user: " + username);
            }
        } else {
            System.out.println("Please login to make a reservation.");
        }
    }

    public void cancelReservation() {
        if (currentUser != null) {
            String username = currentUser.getUsername();
            if (reservationSystem.cancelReservation(username)) {
                System.out.println("Reservation successfully canceled for user: " + username);
            } else {
                System.out.println("No reservation found for user: " + username);
            }
        } else {
            System.out.println("Please login to cancel a reservation.");
        }
    }

    public void printReservations() {
        reservationSystem.printReservations();
    }
}

public class Onlinereservationsystem {
    public static void main(String[] args) {
        ReservationApp app = new ReservationApp();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Logout");
            System.out.println("4. Make Reservation");
            System.out.println("5. Cancel Reservation");
            System.out.println("6. Print Reservations");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 0:
                    System.out.println("Exiting the program...");
                    System.exit(0);
                    break;
                case 1:
                    System.out.print("Enter username: ");
                    String registerUsername = scanner.next();
                    System.out.print("Enter password: ");
                    String registerPassword = scanner.next();
                    app.registerUser(registerUsername, registerPassword);
                    System.out.println("Registration successful!");
                    break;
                case 2:
                    System.out.print("Enter username: ");
                    String loginUsername = scanner.next();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.next();
                    if (app.login(loginUsername, loginPassword)) {
                        System.out.println("Login successful!");
                    } else {
                        System.out.println("Invalid username or password.");
                    }
                    break;
                case 3:
                    app.logout();
                    System.out.println("Logged out successfully!");
                    break;
                case 4:
                    app.makeReservation();
                    break;
                case 5:
                    app.cancelReservation();
                    break;
                case 6:
                    app.printReservations();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            System.out.println();
        }
    }
}
