import java.util.ArrayList;

public class Table {
    ArrayList<Row> rows;

    public Table() {
        rows = new ArrayList<>();
    }

    public void addRow(Row row) {
        rows.add(row);
    }
}
