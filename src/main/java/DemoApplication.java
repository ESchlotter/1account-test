import java.io.IOException;
import java.util.Scanner;

public class DemoApplication {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a search term.");
        String searchTerm = sc.nextLine();

        WebCrawler webCrawler = new WebCrawler();
        try {
            webCrawler.search(searchTerm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
