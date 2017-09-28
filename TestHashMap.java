public class TestHashMap {
	private static int size = 11;
    public static void main(String[] args) {
		MyHashMap<String, Object> test = new MyHashMap<String, Object>(size);

        System.out.println();
        System.out.println("TEST SIMPLE SET");
        test.set("a", 1);
        test.set("b", 2);
        test.set("c", 3);
        test.set("d", 4);
        System.out.println("a: " + test.get("a"));
        System.out.println("b: " + test.get("b"));
        System.out.println("c: " + test.get("c"));
        System.out.println("d: " + test.get("d"));
        System.out.println();

        System.out.println("Proper Load?");
        System.out.println(((float) 4 / (float) size) == test.load());
        System.out.println();

        System.out.println("REPLACE VALUE");
        System.out.println("Old value for a: " + test.set("a", 2));
        System.out.println("New value for a: " + test.get("a"));
        System.out.println();

        System.out.println("size: " + test.getSize());
        System.out.println();

        System.out.println("DELETE KEY");
        System.out.println("Existing value: " + test.delete("b"));
        System.out.println("Getting key: " + test.get("b"));
        System.out.println();

        System.out.println("size: " + test.getSize());
        System.out.println();
 
        System.out.println("SET NULL KEY");
        System.out.println(test.set(null, 1));
        System.out.println();

        System.out.println("DELETE NONEXISTENT KEY");
        System.out.println(test.delete("m"));
        System.out.println();

        System.out.println("Proper Load?");
        System.out.println("size: " + test.getSize());
        System.out.println(((float) 3 / (float) size) == test.load());
        System.out.println();


        System.out.println("Resize Properly?");
     	MyHashMap<String, Object> hashMap = new MyHashMap<>(size);

    	for (int i = 0; i < 60; i++) {
    		hashMap.set("a" + Integer.toString(i), i);	
    	}

    	// ((11*2+1)*2+1)*2+1 == 95 <--total array size
    	System.out.println(hashMap.getTable().length == 95);
    	System.out.println();

    	System.out.println("Correct size?");
    	System.out.println(hashMap.getSize() == 60);	
    }
}
