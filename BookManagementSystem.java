import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BookManagementSystem {
    private static final String ADMIN_PASSWORD = "admin123"; // Simple password for admin
    private static List<Book> books = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Are you an 'Administrator' or a 'Guest'? (Enter 'Exit' to close the system)");
            String userType = scanner.nextLine();

            if ("Administrator".equalsIgnoreCase(userType)) {
                System.out.println("Please enter the administrator password:");
                String password = scanner.nextLine();
                if (!ADMIN_PASSWORD.equals(password)) {
                    System.out.println("Incorrect password.");
                    continue;
                }
                boolean adminSession = true;
                while (adminSession) {
                    System.out.println("Choose an option: \n1. Add Book \n2. Modify Book \n3. Delete Book \n0. Exit");
                    int adminChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline left-over
                    switch (adminChoice) {
                        case 1:
                            addBook(scanner);
                            break;
                        case 2:
                            modifyBook(scanner);
                            break;
                        case 3:
                            deleteBook(scanner);
                            break;
                        case 0:
                            adminSession = false;
                            break;
                        default:
                            System.out.println("Invalid option.");
                    }
                }
            } else if ("Guest".equalsIgnoreCase(userType)) {
                boolean guestSession = true;
                while (guestSession) {
                    System.out.println("Choose an option: \n1. Search for Book \n2. Exit");
                    int guestChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline left-over
                    switch (guestChoice) {
                        case 1:
                            searchForBook(scanner);
                            break;
                        case 2:
                            guestSession = false;
                            break;
                        case 0:
                            guestSession = false;
                            break;
                        default:
                            System.out.println("Invalid option.");
                    }
                }
            } else if ("Exit".equalsIgnoreCase(userType)) {
                break;
            } else {
                System.out.println("Invalid user type.");
            }
        }
        scanner.close();
        System.out.println("System closed.");
    }

    private static void addBook(Scanner scanner) {
        System.out.println("Enter book category:");
        String category = scanner.nextLine();
        System.out.println("Enter book name:");
        String name = scanner.nextLine();
        System.out.println("Enter author:");
        String author = scanner.nextLine();
        System.out.println("Enter date of creation (e.g., 2023-11-08):");
        String dateOfCreation = scanner.nextLine();
        System.out.println("Enter ISAN:");
        String isan = scanner.nextLine();

        Book newBook = new Book(category, name, author, dateOfCreation, isan);
        books.add(newBook);
        System.out.println("Book added successfully!");
    }

    private static void modifyBook(Scanner scanner) {
        System.out.println("Enter the name of the book you want to modify:");
        String name = scanner.nextLine();
        Book bookToModify = findBookByName(name);
        if (bookToModify == null) {
            System.out.println("Book not found.");
            return;
        }
        System.out.println("Enter new category (or press Enter to skip):");
        String category = scanner.nextLine();
        if (!category.isEmpty()) bookToModify.setCategory(category);

        System.out.println("Enter new author (or press Enter to skip):");
        String author = scanner.nextLine();
        if (!author.isEmpty()) bookToModify.setAuthor(author);

        System.out.println("Enter new date of creation (or press Enter to skip):");
        String date = scanner.nextLine();
        if (!date.isEmpty()) bookToModify.setDateOfCreation(date);

        System.out.println("Enter new ISAN (or press Enter to skip):");
        String isan = scanner.nextLine();
        if (!isan.isEmpty()) bookToModify.setIsan(isan);

        System.out.println("Book modified successfully!");
    }

    private static void deleteBook(Scanner scanner) {
        System.out.println("Enter the name of the book to delete:");
        String name = scanner.nextLine();
        Book bookToDelete = findBookByName(name);
        if (bookToDelete == null) {
            System.out.println("Non-existing book.");
            return;
        }
        books.remove(bookToDelete);
        System.out.println("Book deleted successfully!");
    }

    private static void searchForBook(Scanner scanner) {
        System.out.println("Choose search option: \n1. Search by Category \n2. Search by Book Name \n0. Exit");
        int searchChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over
        switch (searchChoice) {
            case 1:
                System.out.println("Enter category:");
                String category = scanner.nextLine();
                List<Book> foundBooks = searchByCategory(category);
                if (foundBooks.isEmpty()) {
                    System.out.println("No books found in this category.");
                } else {
                    for (Book book : foundBooks) {
                        System.out.println(book);
                    }
                }
                break;
            case 2:
                System.out.println("Enter book name:");
                String name = scanner.nextLine();
                Book book = findBookByName(name);
                if (book == null) {
                    System.out.println("Book not found.");
                } else {
                    System.out.println(book);
                }
                break;
            case 0:
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    private static Book findBookByName(String name) {
        for (Book book : books) {
            if (book.getName().equalsIgnoreCase(name)) {
                return book;
            }
        }
        return null;
    }

    private static List<Book> searchByCategory(String category) {
        return books.stream()
                .filter(book -> book.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    // Simple Book class to hold book data
    static class Book {
        private String category;
        private String name;
        private String author;
        private String dateOfCreation;
        private String isan;

        public Book(String category, String name, String author, String dateOfCreation, String isan) {
            this.category = category;
            this.name = name;
            this.author = author;
            this.dateOfCreation = dateOfCreation;
            this.isan = isan;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getName() {
            return name;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getDateOfCreation() {
            return dateOfCreation;
        }

        public void setDateOfCreation(String dateOfCreation) {
            this.dateOfCreation = dateOfCreation;
        }

        public String getIsan() {
            return isan;
        }

        public void setIsan(String isan) {
            this.isan = isan;
        }

        @Override
        public String toString() {
            return "Book{" +
                    "category='" + category + '\'' +
                    ", name='" + name + '\'' +
                    ", author='" + author + '\'' +
                    ", dateOfCreation='" + dateOfCreation + '\'' +
                    ", isan='" + isan + '\'' +
                    '}';
        }
    }
}
