package toys_raffle.models;

/**
 * Class for Toy instance
 */
public class Toy implements Comparable<Toy>{
    private Integer id;
    private String name;
    private int qty;
    private int chance;

    /**
     * Default constructor for Toy instance
     * @param id        Toy identifier
     * @param name      Toy name
     * @param qty       Toy quantity
     * @param chance    Toy chance
     */
    public Toy(int id, String name, int qty, int chance) {
        this.id = id;
        this.name = name;
        this.qty = qty;
        this.chance = this.checkChance(chance);
    }

    /**
     * Getter for id attribute
     * @return this.id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Getter for name attribute
     * @return this.name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter for quantity attribute
     * @return this.qty
     */
    public int getQty() {
        return this.qty;
    }

    /**
     * Decrements quantity attribute
     */
    public void decrQty() {
        this.qty--;
    }

    /**
     * Getter for chance attribute
     * @return this.chance
     */
    public int getChance() {
        return this.chance;
    }

    /**
     * Setter for chance attribute
     * @param chance    new chance value
     */
    public void setChance(int chance) {
        this.chance = this.checkChance(chance);
    }

    /**
     * Chance value checker. 
     * If 0 < chance value < 100 then it will be 50.
     * @param chance    new chance value
     * @return          checked chance value
     */
    private int checkChance(int chance) {
        if (chance < 0 || chance > 100) {
            chance = 50;
        }
        return chance;
    }

    @Override
    public String toString() {
        return String.format(
            "ID: %d, Name: %s, Chance: %d",
            this.getId(), this.getName(), this.getChance()
        );
    }

    @Override
    public int compareTo(Toy o) {
        return Integer.compare(this.getId(), o.getId());
    }
}
