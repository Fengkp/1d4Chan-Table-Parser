import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {
    public static void main(String[] args) throws MalformedURLException, IOException {
        URL url = new URL("https://1d4chan.org/wiki/Stormtrooper_Company_Creation_Tables");
        Document doc = Jsoup.parse(url, 3000);
        Elements tables = doc.select("table");
        for (Element table : tables) {
            Elements rows = table.select("tr");
            for (int i = 1; i < rows.size(); i++) {
                Element row = rows.get(i);
                Elements columns = row.select("td");
                String col1, col2;
                col1 = columns.get(0).text();
                if (columns.size() < 2)
                    col2 = row.select("th").get(0).text();
                else
                    col2 = columns.get(1).text();
                System.out.println(col1 + ", " + col2);
            }
            System.out.println();
        }
    }
}
