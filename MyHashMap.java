
/**
 * KPCB application for single class implementation of hashmap using primitives
 *
 * @author Vicki Shaw
 */
public class MyHashMap<K, V> {
    public MapEntry<K, V>[] table;
    public int size;
    public double load;

    /**
     * Creates a hash map with no entries. The backing array has an initial
     * capacity of {@code initialCapacity}.
     *
     * @param initialCapacity initial capacity of the backing array
     */
    //@SuppressWarnings("unchecked")
    public MyHashMap(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must be positive!");
        }
        table = new MapEntry[initialCapacity];
        size = 0;
    }

    /**
     * Adds new entry into hashmap, with quadratic probine
     * with specified key mapped to specified value, allows null values
     * 
     * @param key to add into the hashmap
     * @param value to add to hashmap, corresponding to key
     * @return old value if duplicate key
    */ 
    public boolean set(K key, V value) {
        if (key == null) {
            System.out.println("Key cannot be set if null.");
            return false;
        }

        if (size == table.length) {
            System.out.println("Hashmap is full!");
            return false;
        }

        int quad = 1;
        int index = Math.abs(key.hashCode()) % table.length;
        MapEntry<K, V> entry = new MapEntry<>(key, value);
        int newIndex = index;
        while (true) {
            if (table[newIndex] == null) {
                table[newIndex] = entry;
                size++;
                return true;
            } else if (table[newIndex].getKey().equals(key)) {
                table[newIndex].setValue(value); //entry?
                if (table[newIndex].isRemoved()) {
                    size++;
                    table[newIndex].setRemoved(false);
                    return true;
                }
                table[newIndex].setRemoved(false);
                return true;
            } else if (!table[newIndex].getKey().equals(key)) {
                if (table[newIndex].isRemoved()) {

                    //store index
                    int ogIndex = newIndex;
                    //if has duplicate
                    V oldValue = duplicates(key, value, newIndex, quad);
                    if (oldValue == null) { //no duplicates
                        table[ogIndex] = entry;
                        size++;
                        return true;
                    } else {
                        return false;
                    }
                }
            }

            newIndex = (index + quad * quad) % table.length;

            if ((quad++) > table.length) {
                // hashmap is full
                break;
            }
        }
        return false;
    }

    /**
     * Helper method to identify duplicate key, returns the old value
     * 
     * @param key key to look for duplicates
     * @param value new value to replace old value
     * @param index start of where to start looking
     * @param quad incrementation of offset from index
     * @return value of the duplicate, returns null if no duplicate found
     */
    private V duplicates(K key, V value, int index, int quad) {
        int newIndex;
        newIndex = (index + quad * quad) % table.length;
        quad++;
        while (table[newIndex] != null && quad * quad <= table.length) {
            if (table[newIndex].getKey().equals(key)) {
                V duplicateValue = table[newIndex].getValue();
                table[newIndex].setValue(value);
                return duplicateValue;
            } else {
                newIndex = (index + quad * quad) % table.length;
                quad++;
            }
        }
        return null;
    }


    /**
     * Removes entry specified by key from hashmap
     * 
     * @param key to remove from hashmap
     * @return removed object value associated with key, if any
     */
    public V delete(K key) {
        if (key == null) return null;
        int index = Math.abs(key.hashCode()) % table.length;
        int probeCount = 0;
        int quad = 1;
        int newIndex = index;
        while (table[newIndex] != null && probeCount < table.length) {
            if (table[newIndex].getKey().equals(key) && !table[newIndex].isRemoved()) {
                table[newIndex].setRemoved(true);
                size--;
                return table[newIndex].getValue();
            }
            probeCount++;
            newIndex = (index + quad * quad) % table.length;
            quad++;
        }
        
        System.out.println("Key does not exist");
        return null;
    }

    /**
     * Gets MyHashMap entry value from key. Searches through entries using
     * quadratic probing.
     * 
     * @param initialCapacity initial capacity of the backing array
     * @return object value associated with key, if any
     */
    public V get(K key) {
        if (key == null) return null;
        int startIndex = Math.abs(key.hashCode()) % table.length;
        int probeCount = 0;
        int quad = 1; //quadratic probing starts with 1.
        int newIndex = startIndex;
        
        while (table[newIndex] != null && probeCount < table.length) {
            if (table[newIndex].getKey().equals(key) && !table[newIndex].isRemoved()) {
                return table[newIndex].getValue();
            }
            probeCount++;
            newIndex = (startIndex + quad * quad) % table.length;
            quad++;
        }

        System.out.println("Key not found");
        return null;
    }


    /**
     * Calculates the load factor of the hashmap by dividing size by table 
     * length
     * 
     * @return float denoting the load of the hashmap
     */
    public float load() {
        return (float) (size) / (float) table.length;
    }

    public int getSize() {
        return size;
    }

    /**
    * Gets the backing array for testing purposes
    * @return backing array of hashmap
    */   
    public MapEntry<K, V>[] getTable() {
        return table;
    }

    /**
    * Inner class (static) to denote map entries. 
    */
    public class MapEntry<K, V> {
        private boolean removed;
        private K key;
        private V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public boolean isRemoved() {
            return removed;
        }

        public void setRemoved(boolean removed) {
            this.removed = removed;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }
}
