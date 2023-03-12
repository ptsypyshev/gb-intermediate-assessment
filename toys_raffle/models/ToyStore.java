package toys_raffle.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;

import toys_raffle.exceptions.EmptyQueueException;
import toys_raffle.loggers.Logger;
import toys_raffle.storages.Storage;

/**
 * Class for ToyStore instance. Implements base logic.
 */
public class ToyStore {
    private Map<Integer, Toy> toys;
    private int toysQty;
    private Queue<Toy> queue;
    private Random random;
    private Storage storage;
    private Logger logger;

    /**
     * Default constructor for ToyStore instance
     * 
     * @param storage Storage implementation
     * @param logger  Logger implementation
     */
    public ToyStore(Storage storage, Logger logger) {
        this.storage = storage;
        this.logger = logger;
        this.toys = new HashMap<Integer, Toy>();
        this.queue = new LinkedList<Toy>();
        this.random = new Random();
        this.toysQty = 0;
    }

    /**
     * Loads data from external storage
     * 
     * @return true if the data was sucessfully loaded
     */
    public boolean LoadData() {
        Map<Integer, Toy> loadedToys = storage.load();
        if (loadedToys.isEmpty()) {
            return false;
        }

        this.toys = loadedToys;
        for (Toy toy : this.toys.values()) {
            this.toysQty += toy.getQty();
        }
        return true;
    }

    /**
     * Saves data to external storage
     * 
     * @return true if the data was sucessfully saved
     */
    public boolean SaveData() {
        return this.storage.save(this.toys);
    }

    /**
     * Getter for toys attribute
     * 
     * @return this.toys
     */
    public Map<Integer, Toy> getToys() {
        return this.toys;
    }

    /**
     * Puts a toy to the toy store
     */
    public void Put(Toy toy) {
        this.toys.put(toy.getId(), toy);
        this.toysQty += toy.getQty();
    }

    /**
     * Removes a toy by its id from the toy store
     * 
     * @param id toy's identifier
     */
    public void RemoveByID(int id) {
        int qty = this.toys.get(id).getQty();
        this.toys.remove(id);
        this.toysQty -= qty;
    }

    /**
     * Removes all toys from the toy store
     */
    public void RemoveAll() {
        this.toys.clear();
        this.toysQty = 0;
    }

    /**
     * Changes a chance for toy instance
     * 
     * @param id     toy's identifier
     * @param chance new chance value
     * @return true if the toy's chance value was changed
     */
    public boolean ChangeChance(int id, int chance) {
        if (this.toys.containsKey(id)) {
            this.toys.get(id).setChance(chance);
            this.storage.save(this.toys);
            return true;
        }
        return false;
    }

    /**
     * Adds toys from store to queue
     */
    public void AddToysToQueue() {
        List<Integer> sortedToysIDs = this.getSortedIDs();
        List<Integer> chanceList = this.getChanceList(sortedToysIDs);

        while (this.toysQty > 0) {
            Toy toy = this.getRandomToy(chanceList, sortedToysIDs);
            if (toy.getQty() > 0) {
                this.queue.add(toy);
                toy.decrQty();
                this.toysQty--;
            }
        }
        this.toys.clear();
    }

    /**
     * Prepares sorted list from toys identifiers
     * @return  a sorted list of IDs
     */
    private List<Integer> getSortedIDs() {
        List<Integer> sortedToysIDs = new ArrayList<Integer>();
        for (Integer id : this.toys.keySet()) {
            sortedToysIDs.add(id);
        }
        Collections.sort(sortedToysIDs);
        return sortedToysIDs;
    }

    /**
     * Prepares a chance list due the toys chance value
     * @param ids   a sorted list of IDs
     * @return      a chance list
     */
    private List<Integer> getChanceList(List<Integer> ids) {
        List<Integer> chanceList = new ArrayList<Integer>();
        int stepChanceBorder = 0;
        for (Integer id : ids) {
            if (chanceList.size() != 0) {
                stepChanceBorder += chanceList.get(chanceList.size() - 1);
            }
            chanceList.add(this.toys.get(id).getChance() + stepChanceBorder);
        }
        return chanceList;
    }

    /**
     * Gets random toy from toy store (depends on toys chance value)
     * @param chanceList        a chance list
     * @param sortedToysIDs     a sorted list of IDs
     * @return                  a Toy instance
     */
    private Toy getRandomToy(List<Integer> chanceList, List<Integer> sortedToysIDs) {
        int randInt = random.nextInt(chanceList.get(chanceList.size() - 1));
        int idx = 0;
        for (Integer step : chanceList) {
            if (randInt > step) {
                idx++;
            }
        }
        int id = sortedToysIDs.get(idx);
        return this.toys.get(id);
    }

    /**
     * Gets raffle queue length
     * @return  this.queue length
     */
    public int GetQueueLen() {
        return this.queue.size();
    }

    /**
     * Gets a toy from raffle queue
     * @return  A toy instance
     * @throws EmptyQueueException
     */
    public Toy Get() throws EmptyQueueException {
        if (this.GetQueueLen() == 0) {
            throw new EmptyQueueException();
        }
        Toy toy = this.queue.poll();
        this.logger.Log(toy.toString());
        return toy;
    }

    /**
     * Checks a toy identifier in toy store
     * @param id    a toy identifier
     * @return      true if the toy is present at the store
     */
    public boolean hasToyID(int id) {
        return this.toys.containsKey(id);
    }
}
