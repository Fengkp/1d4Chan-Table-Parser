import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {
    public static void main(String[] args) throws MalformedURLException, IOException {
        URL url = new URL("https://1d4chan.org/wiki/Imperial_Guard_Regiment_Creation_Tables");
        Document doc = Jsoup.parse(url, 3000);
        Elements tables = doc.select("table");
        ArrayList<Table> regimentTables = new ArrayList<>();

        for (Element table : tables) {
            Table newTable = new Table();
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

                if (Pattern.matches("[a-zA-Z]", col1.substring(0, 1))) {
                    ArrayList<Integer> rolls = new ArrayList<>();
                    Pattern p = Pattern.compile("\\d+");
                    Matcher m = p.matcher(col2);
                    while (m.find())
                        rolls.add(Integer.parseInt(m.group()));
                    newTable.addRow(new Row(rolls, col1));
                }
            }
            regimentTables.add(newTable);
        }
        System.out.println();
    }
}
