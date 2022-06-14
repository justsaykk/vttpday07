package books;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Reader {

    public List<String> load(Path path) throws IOException {
        List<String> booksList = new ArrayList<>();
        File file = path.toFile();
        String str;
        BufferedReader br = new BufferedReader(new FileReader(file));
        while ((str = br.readLine()) != null) {
            booksList.add(str);
        }
        br.close();
        return booksList;
    }

    public List<String> search(List<String> bookList, String keyWord) {
        List<String> result = bookList.stream()
                .filter(el -> el.trim().toLowerCase().contains(keyWord.toLowerCase()))
                .toList();
        return result;
    }
}