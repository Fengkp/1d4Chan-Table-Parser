import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseTable {
    public ArrayList<Table> pullTables(String link) throws IOException {
        ArrayList<Table> newTables = new ArrayList<>();
        URL url = new URL(link);
        Document doc = Jsoup.parse(url, 3000);
        Elements tables = doc.select("table");
        for (Element table : tables) {
            if (newTables.size() == tables.size() - 1)
                break;
            Elements rows = table.select("tr");
            Table newTable = new Table(rows.get(0).select("th").text());
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
                    newTable.addResult(rolls, col1);
                }
            }
            newTables.add(newTable);
        }
        displayTableContents(newTables);
        return newTables;
    }

    private void displayTableContents(ArrayList<Table> tables) {
        for (Table table : tables)
            table.display();
    }
}
