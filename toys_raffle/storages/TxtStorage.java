package toys_raffle.storages;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import toys_raffle.models.Toy;

/**
 * Implementation of external Storage interface. 
 * Uses simple txt file (fields separates by space, objects separates by newLine)
 */
public class TxtStorage implements Storage{
    private String filepathString;

    /**
     * Default constuctor for TxtStorage instance
     * @param filepathString    path to data file
     */
    public TxtStorage(String filepathString) {
        this.filepathString = filepathString;
    }

    @Override
    public Map<Integer, Toy> load() {
        Map<Integer, Toy> toys = new HashMap<Integer, Toy>();
        try {
			Scanner scanner = new Scanner(new File(this.filepathString));

			while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(" ");
                Toy toy = this.parseToyFromLine(line);
                if (toy == null) {
                    continue;
                }
				toys.put(toy.getId(), toy);
			}

			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
        return toys;
    }

    @Override
    public boolean save(Map<Integer, Toy> toys) {
        File file = new File(this.filepathString);
        
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(prepareData(toys));
            fw.close();
            return true;
        } catch (IOException e) {
            System.out.println(e);
        }
        return false;
    }
    
    /**
     * Parses toys parameters from string line
     * @param line  string line where parameters separated by space
     * @return      a Toy instance
     */
    private Toy parseToyFromLine(String[] line) {
        try {
            int id = Integer.parseInt(line[0]);
            String name = line[1];
            int qty = Integer.parseInt(line[2]);
            int chance = Integer.parseInt(line[3]);
            Toy toy = new Toy(id, name, qty, chance);
            return toy;
            
        } catch (NumberFormatException e) {
            System.out.printf("Try to parse bad line, error: %s\n", e);
        }
        return null;
    }

    /**
     * Prepares the data to save into the file
     * @param toys  Toys map
     * @return      Data string that can be written to file
     */
    private String prepareData(Map<Integer, Toy> toys) {
        StringBuilder sb = new StringBuilder();
        for (Toy toy : toys.values()) {
            sb.append(String.format(
                "%d %s %d %d\n",
                toy.getId(),
                toy.getName(),
                toy.getQty(),
                toy.getChance()
                )
            );
        }
        return sb.toString();
    }
}
