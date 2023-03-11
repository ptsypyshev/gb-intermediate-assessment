package toys_raffle.storages;

import java.util.Map;

import toys_raffle.models.Toy;

/**
 * External storage interface
 */
public interface Storage {
    /**
     * Loads data from external storage to Toys map
     * @return  Toys map
     */
    public Map<Integer,Toy> load();

    /**
     * Saves data from Toys map to external storage
     * @param toys  Toys map
     * @return      true if the data was saved sucessfully
     */
    public boolean save(Map<Integer,Toy> toys);
}
