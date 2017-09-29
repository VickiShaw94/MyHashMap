
/**
 * Test file for testing MyHashMap implementation. 
 *
 * @author Vicki Shaw
 */
public class TestHashMap {
	private static int size = 11;
    public static void main(String[] args) {
		MyHashMap<String, Object> test = new MyHashMap<>(size);

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
        System.out.println("size: " + test.getSize());
        System.out.println(((float) 4 / (float) size) == test.load());
        System.out.println();

        System.out.println("REPLACE VALUE");
        System.out.println("Old value for a: " + test.set("a", 2));
        System.out.println("New value for a: " + test.get("a"));
        System.out.println();

        System.out.println("size: " + test.getSize());
        System.out.println();

        System.out.println("DELETE KEY");
        System.out.println("Existing value: " + test.delete("d"));
        System.out.println("Getting key: " + test.get("d"));
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


        System.out.println("FILL REST OF ARRAY");
        System.out.println("adding <d: 4> : " + test.set("d", 4));
        System.out.println("adding <e: 5> : " + test.set("e", 5));
        System.out.println("adding <f: 6> : " +test.set("f", 6));
        System.out.println("adding <g: 7> : " +test.set("g", 7));
        System.out.println("adding <h: 8> : " +test.set("h", 8));

        System.out.println("adding <i: 9> : " +test.set("i", 9));
        System.out.println("adding <j: 10> : " +test.set("j", 10));
        System.out.println("adding <k: 11> : " +test.set("k", 11));
        System.out.println("adding <l: 12> : " +test.set("l", 12)); //should throw error
        //test for full array

        System.out.println("Proper Load?");
        System.out.println("size: " + test.getSize());
        System.out.println(((float) 11 / (float) size) == test.load());
        System.out.println();
    }
}
