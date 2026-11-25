
package com.student.gutenbergbookexplorer;

import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GutenbergBookExplorer {

    // Scanner for keyboard input
    static Scanner input = new Scanner(System.in);

    // Website address
    static final String WEBSITE =
            "https://books.toscrape.com/";

    // Fixed-size arrays (no ArrayList)
    static Book[] books = new Book[100];
    static Book[] readingList = new Book[20];

    static int bookCount = 0;
    static int readingCount = 0;

    // Book information
    static class Book {
        String title;
        String author;
        String price;
        String rating;
        String availability;
        String url;

        Book(String title, String author, String price,
             String rating, String availability, String url) {

            this.title = title;
            this.author = author;
            this.price = price;
            this.rating = rating;
            this.availability = availability;
            this.url = url;
        }
    }

    // Main method
    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println("       GUTENBERG BOOK EXPLORER");
        System.out.println("====================================");
        System.out.println("Practice website: Books to Scrape");

        loadBooks();

        int choice;

        do {
            System.out.println("\n========== MAIN MENU ==========");
            System.out.println("1. Explore Books");
            System.out.println("2. Search by Title");
            System.out.println("3. Search by Author");
            System.out.println("4. Add Book to Reading List");
            System.out.println("5. View Reading List");
            System.out.println("6. Remove Book");
            System.out.println("7. Display Book Details");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            choice = readNumber();

            switch (choice) {

                case 1:
                    exploreBooks();
                    break;

                case 2:
                    System.out.print("Enter book title: ");
                    String title = input.nextLine();
                    searchBook(title);
                    break;

                case 3:
                    System.out.print("Enter author name: ");
                    String author = input.nextLine();
                    searchBook(author, true);
                    break;

                case 4:
                    addBook();
                    break;

                case 5:
                    viewReadingList();
                    break;

                case 6:
                    removeBook();
                    break;

                case 7:
                    displayBookDetails();
                    break;

                case 0:
                    System.out.println("Thank you for using the application!");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 0);

        input.close();
    }

    // Read and validate numbers
    static int readNumber() {

        while (true) {
            String value = input.nextLine();

            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }

    // Connect to website and retrieve books
    static void loadBooks() {

        if (bookCount > 0) {
            return;
        }

        try {
            System.out.println("\nConnecting to Books to Scrape...");

            Document doc = Jsoup.connect(WEBSITE)
                    .userAgent("Mozilla/5.0")
                    .timeout(120000)
                    .followRedirects(true)
                    .get();

            Elements bookElements =
                    doc.select("article.product_pod");

            for (Element element : bookElements) {

                if (bookCount >= books.length) {
                    break;
                }

                Element link = element.selectFirst("h3 a");

                if (link == null) {
                    continue;
                }

                String title = link.attr("title");

                if (title.isEmpty()) {
                    title = link.text();
                }

                Element priceElement =
                        element.selectFirst(".price_color");

                Element ratingElement =
                        element.selectFirst(".star-rating");

                Element availabilityElement =
                        element.selectFirst(".availability");

                String price = priceElement == null
                        ? "Not available"
                        : priceElement.text();

                String rating = "Not available";

                if (ratingElement != null
                        && ratingElement.classNames().size() > 1) {
                    rating = ratingElement.classNames()
                            .toArray(new String[0])[1];
                }

                String availability =
                        availabilityElement == null
                        ? "Not available"
                        : availabilityElement.text().trim();

                String url = link.absUrl("href");

                // Books to Scrape does not provide authors
                String author = "Not provided by this website";

                books[bookCount] = new Book(
                        title,
                        author,
                        price,
                        rating,
                        availability,
                        url
                );

                bookCount++;
            }

            System.out.println("Connection successful!");
            System.out.println("Books retrieved: " + bookCount);

            if (bookCount == 0) {
                System.out.println(
                        "No books were found on the page.");
            }

        } catch (Exception e) {

            System.out.println("Connection failed!");
            System.out.println("Reason: " + e.getMessage());
            System.out.println(
                    "Check your internet connection and try again.");
        }
    }

    // 1. Explore books
    static void exploreBooks() {

        loadBooks();

        System.out.println("\n========== AVAILABLE BOOKS ==========");

        if (bookCount == 0) {
            System.out.println("No books available.");
            return;
        }

        for (int i = 0; i < bookCount; i++) {

            System.out.println("\nBook Number: " + (i + 1));
            System.out.println("Title: " + books[i].title);
            System.out.println("Price: " + books[i].price);
            System.out.println("Rating: " + books[i].rating);
            System.out.println("Availability: "
                    + books[i].availability);
            System.out.println("URL: " + books[i].url);

            System.out.println("--------------------------------");
        }
    }

    // 2. Search by title (overloaded method 1)
    static void searchBook(String title) {

        loadBooks();

        boolean found = false;

        System.out.println("\n========== SEARCH RESULTS ==========");

        for (int i = 0; i < bookCount; i++) {

            if (books[i].title.toLowerCase()
                    .contains(title.toLowerCase())) {

                System.out.println("Book Number: " + (i + 1));
                System.out.println("Title: " + books[i].title);
                System.out.println("Price: " + books[i].price);
                System.out.println("URL: " + books[i].url);

                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching book found.");
        }
    }

    // 3. Overloaded search method 2
    static void searchBook(String keyword,
                           boolean searchByAuthor) {

        if (searchByAuthor) {

            System.out.println("\nAuthor search is unavailable.");
            System.out.println(
                    "Books to Scrape does not provide author names.");
            System.out.println(
                    "Use Project Gutenberg for author searching.");

        } else {
            searchBook(keyword);
        }
    }

    // 4. Add a book to reading list
    static void addBook() {

        loadBooks();

        if (bookCount == 0) {
            System.out.println("No books available to add.");
            return;
        }

        if (readingCount >= readingList.length) {
            System.out.println("Reading list is full!");
            return;
        }

        exploreBooks();

        System.out.print("\nEnter book number to add: ");
        int number = readNumber();

        if (number < 1 || number > bookCount) {
            System.out.println("Invalid book number.");
            return;
        }

        Book selected = books[number - 1];

        // Check for duplicates
        for (int i = 0; i < readingCount; i++) {

            if (readingList[i].url.equals(selected.url)) {
                System.out.println(
                        "This book is already in your reading list.");
                return;
            }
        }

        readingList[readingCount] = selected;
        readingCount++;

        System.out.println("Book added successfully!");
    }

    // 5. View reading list
    static void viewReadingList() {

        System.out.println("\n========== MY READING LIST ==========");

        if (readingCount == 0) {
            System.out.println("Your reading list is empty.");
            return;
        }

        for (int i = 0; i < readingCount; i++) {

            System.out.println("\nBook Number: " + (i + 1));
            System.out.println("Title: "
                    + readingList[i].title);
            System.out.println("Price: "
                    + readingList[i].price);
            System.out.println("Rating: "
                    + readingList[i].rating);
            System.out.println("URL: "
                    + readingList[i].url);

            System.out.println("--------------------------------");
        }
    }

    // 6. Remove a book from reading list
    static void removeBook() {

        if (readingCount == 0) {
            System.out.println("Your reading list is empty.");
            return;
        }

        viewReadingList();

        System.out.print("\nEnter book number to remove: ");
        int number = readNumber();

        if (number < 1 || number > readingCount) {
            System.out.println("Invalid book number.");
            return;
        }

        // Move remaining books one position left
        for (int i = number - 1; i < readingCount - 1; i++) {

            readingList[i] = readingList[i + 1];
        }

        readingList[readingCount - 1] = null;
        readingCount--;

        System.out.println("Book removed successfully!");
    }

    // 7. Display book details
    static void displayBookDetails() {

        loadBooks();

        if (bookCount == 0) {
            System.out.println("No books available.");
            return;
        }

        exploreBooks();

        System.out.print("\nEnter book number for details: ");
        int number = readNumber();

        if (number < 1 || number > bookCount) {
            System.out.println("Invalid book number.");
            return;
        }

        Book selected = books[number - 1];

        System.out.println("\n========== BOOK DETAILS ==========");
        System.out.println("Title: " + selected.title);
        System.out.println("Author: " + selected.author);
        System.out.println("Price: " + selected.price);
        System.out.println("Rating: " + selected.rating);
        System.out.println("Availability: "
                + selected.availability);
        System.out.println("Book URL: " + selected.url);
        System.out.println("==================================");
    }
}
