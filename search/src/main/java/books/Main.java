package books;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Console cons = System.console();
        String fileName = args[0];
        // Relative path: "search/books.txt"
        Path path = Paths.get("./" + fileName);
        List<String> bookList = new ArrayList<>();
        Reader reader = new Reader();

        // Once app starts, load books.txt
        bookList = reader.load(path);

        // Start to receive user input
        boolean stop = false;

        while (!stop) {
            // Get user command
            String userInput = cons.readLine(">> What would you like to do? \n");
            String[] splitString = userInput.split(" ", 2); // input: search java god | output: [search, java god]
            String userCommand = splitString[0].toLowerCase();
            String keyWord = splitString[1];

            switch (userCommand) {
                case "search":
                    List<String> searchResult = reader.search(bookList, keyWord);
                    for (int i = 0; i < searchResult.size(); i++) {
                        System.out.printf("%d. %s\n", i + 1, searchResult.get(i));
                    }
                    break;
                case "count":
                    List<String> countResult = reader.search(bookList, keyWord);
                    int count = 0;
                    for (int i = 0; i < countResult.size(); i++) {
                        count++;
                    }
                    System.out.printf("There are %d books with th phrase %s.\n", count, keyWord);

                    break;
                case "exit":
                    System.out.println("Hope we helped :)\n");
                    stop = true; // Stopping the while-loop
                    break;

                default:
                    break;
            }
        }

    }
}
