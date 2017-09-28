import java.util.Arrays;
public class TestHashMap {

    private static int size;
    private static MyHashMap<String, Object> test;

    private static void setup() {
        size = 11;
    	test = new MyHashMap<String, Object>(size);
    }

    public static void main(String[] args) {
    	//initialize
    	setup();

        System.out.println("Resize Properly");
        System.out.println(testResize());

        System.out.println();
        System.out.println("Testing simple set");
        test.set("a", 1);
        test.set("b", 2);
        test.set("c", 3);
        test.set("d", 4);
        System.out.println("a: " + test.get("a"));
        System.out.println("b: " + test.get("b"));
        System.out.println("c: " + test.get("c"));
        System.out.println("d: " + test.get("d"));
        System.out.println();

        System.out.println("Replacing values");
        System.out.println("Old value for a: " + test.set("a", 2));
        System.out.println("New value for a: " + test.get("a"));
        System.out.println();

        System.out.println("Deleting keys (entries)");
        System.out.println("Existing value: " + test.delete("b"));
        System.out.println("Getting deleted key: " + test.get("b"));
        System.out.println();

        System.out.println("Setting null value");
        System.out.println(test.set("k", null));
        System.out.println();

        System.out.println("Setting null key");
        System.out.println("Setting null key: " + test.set(null, 1));
        System.out.println("Getting nonexistent key: " + test.get("k"));
        System.out.println();
    }

    public static boolean testResize() {
    	for (int i = 0; i < 60; i++) {
    		test.set("a" + Integer.toString(i), i);	
    	}
    	return test.getSize() == 60;	
    }
}
