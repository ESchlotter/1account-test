import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class WebCrawlerTest {
    WebCrawler webCrawler = new WebCrawler();

    @Test
    public void searchTest() throws IOException {
        webCrawler.search("wikipedia");
    }

    @Test
    public void searchNullTest() throws IOException {
        webCrawler.search(null);
    }

    @Test
    public void searchRelatedTest() throws IOException {
        Map<String, Integer> map = new HashMap<>();
        webCrawler.countLibraries("http://www.cnn.com", map);
    }
}