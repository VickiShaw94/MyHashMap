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
    public MyHashMap(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must be positive!");
        }
        load = 0.75; //the load factor allows a good tradeoff between time and space cost
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
    public V set(K key, V value) {
        if (key == null) {
            System.out.println("Key cannot be set if null.");
            return null;
        }

        if ((double) (size + 1) >= (table.length * load)) {
            resizeBackingTable(2 * table.length + 1);
        }

        int quad = 1;
        int index = Math.abs(key.hashCode()) % table.length;
        MapEntry<K, V> entry = new MapEntry<>(key, value);
        int newIndex = index;
        while (true) {
            if (table[newIndex] == null) {
                table[newIndex] = entry;
                size++;
                return null;
            } else if (table[newIndex].getKey().equals(key)) {
                V returnValue = table[newIndex].getValue();
                table[newIndex].setValue(value); //entry?
                if (table[newIndex].isRemoved()) {
                    size++;
                    table[newIndex].setRemoved(false);
                    return null;
                }
                table[newIndex].setRemoved(false);
                return returnValue;
            } else if (!table[newIndex].getKey().equals(key)) {
                if (table[newIndex].isRemoved()) {

                    //store index
                    int ogIndex = newIndex;
                    //if has duplicate
                    V oldValue = duplicates(key, value, newIndex, quad);
                    if (oldValue == null) { //no duplicates
                        table[ogIndex] = entry;
                        size++;
                        return null;
                    } else {
                        return oldValue;
                    }
                }
            }

            newIndex = (index + quad * quad) % table.length;

            if ((quad) > table.length) {
                resizeBackingTable(2 * table.length + 1);
            }
            quad++;
        }
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

    /**
     * Resizes backing array if load capacity is exceeded. The load value (0.75) 
     * provides a good tradeoff between time and space complexity
     * 
     * @param initialCapacity initial capacity of the backing array
     * @return object value associated with key, if any
     */
    private void resizeBackingTable(int length) {
        if (length < size) {
            System.out.println("Not a valid length");
        }
        MapEntry[] newTable = table;
        
        size = 0;

        table = new MapEntry[length];
        //Do not include removed flagged entries
        for (MapEntry<K, V> aTable : newTable) {
            if (aTable != null && !aTable.isRemoved()) {
                set(aTable.getKey(), aTable.getValue());
            }
        }
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
