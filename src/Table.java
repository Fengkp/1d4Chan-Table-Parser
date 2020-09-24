import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Table {
    Map results = new HashMap();
    String tableName;

    public Table(String name) {
        tableName = name;
    }


    public void addResult(ArrayList<Integer> rolls, String result) {
        if (rolls.size() > 1)
            for (int i = rolls.get(0); i <= rolls.get(1); i++)
                results.put(i, result);
        else
            results.put(rolls.get(0), result);
    }

    public void display() {
//        for (Row row : rows)
//            row.display();
//        System.out.println();
    }
}
