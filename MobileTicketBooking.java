package MobileTicketBooking;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class User {
    private String name;
    private String email;
    private String password;
    private List<String> bookingHistory;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.bookingHistory = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void addToBookingHistory(String bookingDetails) {
        bookingHistory.add(bookingDetails);
    }

    public void viewBookingHistory() {
        System.out.println("Booking History for " + name + ":");
        for (String booking : bookingHistory) {
            System.out.println(booking);
        }
    }
}

class Movie {
    private String title;
    private String description;
    private double rating;

    public Movie(String title, String description, double rating) {
        this.title = title;
        this.description = description;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public double getRating() {
        return rating;
    }
}

class MovieTicketBookingSystem {
    private List<User> users;
    private List<Movie> movies;

    public MovieTicketBookingSystem() {
        users = new ArrayList<>();
        movies = new ArrayList<>();
    }

    public void registerUser(String name, String email, String password) {
        User newUser = new User(name, email, password);
        users.add(newUser);
        System.out.println("User registered successfully!");
    }

    public void addMovie(String title, String description, double rating) {
        Movie newMovie = new Movie(title, description, rating);
        movies.add(newMovie);
        System.out.println("Movie added successfully!");
    }

    public void viewMovies() {
        System.out.println("Available Movies:");
        for (Movie movie : movies) {
            System.out.println("Title: " + movie.getTitle());
            System.out.println("Description: " + movie.getDescription());
            System.out.println("Rating: " + movie.getRating());
            System.out.println("-------------------------");
        }
    }

    public void bookTicket(User user, Movie movie, String showtime, int numSeats) {
        String bookingDetails = "Movie: " + movie.getTitle() +
                "\nShowtime: " + showtime +
                "\nNumber of Seats: " + numSeats +
                "\nUser: " + user.getName() +
                "\nEmail: " + user.getEmail();

        user.addToBookingHistory(bookingDetails);
        System.out.println("Ticket booked successfully!");
    }

    public void viewBookingHistory(User user) {
        user.viewBookingHistory();
    }
public class MobileTicketBooking{
    public static void main(String[] args) {
        MovieTicketBookingSystem bookingSystem = new MovieTicketBookingSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to the Movie Ticket Booking System!");
            System.out.println("1. Register User");
            System.out.println("2. Add Movie");
            System.out.println("3. View Movies");
            System.out.println("4. Book Ticket");
            System.out.println("5. View Booking History");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();

                    bookingSystem.registerUser(name, email, password);
                    break;

                case 2:
                    System.out.print("Enter movie title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter movie description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter movie rating: ");
                    double rating = scanner.nextDouble();
                    scanner.nextLine(); // Consume the newline character

                    bookingSystem.addMovie(title, description, rating);
                    break;

                case 3:
                    bookingSystem.viewMovies();
                    break;

                case 4:
                    System.out.print("Enter your email: ");
                    String userEmail = scanner.nextLine();
                    User currentUser = null;
                    for (User user : bookingSystem.users) {
                        if (user.getEmail().equals(userEmail)) {
                            currentUser = user;
                            break;
                        }
                    }
                    if (currentUser == null) {
                        System.out.println("User not found. Please register first.");
                        break;
                    }

                    System.out.print("Enter movie title: ");
                    String movieTitle = scanner.nextLine();
                    Movie selectedMovie = null;
                    for (Movie movie : bookingSystem.movies) {
                        if (movie.getTitle().equals(movieTitle)) {
                            selectedMovie = movie;
                            break;
                        }
                    }
                    if (selectedMovie == null) {
                        System.out.println("Movie not found. Please try again.");
                        break;
                    }

                    System.out.print("Enter showtime: ");
                    String showtime = scanner.nextLine();
                    System.out.print("Enter number of seats: ");
                    int numSeats = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    bookingSystem.bookTicket(currentUser, selectedMovie, showtime, numSeats);
                    break;

                case 5:
                    System.out.print("Enter your email: ");
                    String emailInput = scanner.nextLine();
                    User selectedUser = null;
                    for (User user : bookingSystem.users) {
                        if (user.getEmail().equals(emailInput)) {
                            selectedUser = user;
                            break;
                        }
                    }
                    if (selectedUser == null) {
                        System.out.println("User not found. Please register first.");
                        break;
                    }

                    bookingSystem.viewBookingHistory(selectedUser);
                    break;

                case 0:
                    System.out.println("Thank you for using the Movie Ticket Booking System. Goodbye!");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            System.out.println(); // Add a new line for readability
        }
    }
}
}

