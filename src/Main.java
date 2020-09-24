import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws  IOException {
        //URL url = new URL("https://1d4chan.org/wiki/Planet_generator"); // Broken, 3 columns in some tables
        //URL url = new URL("https://1d4chan.org/wiki/System_generator"); // Broken, ^^^
        ParseTable parser = new ParseTable();
        ArrayList<Table> regimentTables = parser.pullTables("https://1d4chan.org/wiki/Imperial_Guard_Regiment_Creation_Tables");
        ArrayList<Table> stormtrooperTables = parser.pullTables("https://1d4chan.org/wiki/Stormtrooper_Company_Creation_Tables");

        System.out.println("Imperial Guard:");
        generateResults(regimentTables);
        System.out.println("\n-----------------------\n");
        System.out.println("Tempestus Scions:");
        generateResults(stormtrooperTables);
    }

    public static int roll(int size) {
        Random die = new Random();
        return die.nextInt(size) + 1;
    }

    public static void generateResults(ArrayList<Table> tables) {
        for (Table table : tables) {
            int roll = roll(table.results.size());
            System.out.println(table.tableName + ": " + table.results.get(roll));
        }
    }

}

