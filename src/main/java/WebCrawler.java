import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class WebCrawler {
    String googleUrl = "https://www.google.com/search?q=";
    String relatedUrl = googleUrl + "related:";

    public void search(String searchTerm) throws IOException {
        System.out.println("Searching Google for: " + searchTerm);
        Elements elements = Jsoup.connect(googleUrl + searchTerm).get().select("a[href]");

        // Synchronized map because normal HashMap is not thread safe.
        Map<String, Integer> libraryCount = Collections.synchronizedMap(new HashMap<>());

        // Multi threading via parallelStream
        elements.parallelStream().forEach(link -> {
            String title = link.text();
            String url = link.absUrl("href");

            if (url.startsWith(relatedUrl)) {
                String subUrl = url.substring(relatedUrl.length());
                System.out.println("Sub URL:" + subUrl);

                countLibraries(subUrl, libraryCount);
            }
        });


    }

    protected void countLibraries(String url, Map<String, Integer> libraryCount) {
        Document doc = null;
        try {
            doc = Jsoup.connect(URLDecoder.decode(url, "UTF-8")).timeout(10000)
                    .userAgent("Mozilla/5.0").get();
        } catch (IOException e) {
            // Every URL from google seems to get stuck here
            e.printStackTrace();
            return;
        }
        Elements scriptElements = doc.getElementsByTag("script");
        for (Element element : scriptElements ){
            // May need to get dom to actually find out libraries
            System.out.println(element);
            // Get name of library and increment count
        }
    }
}
