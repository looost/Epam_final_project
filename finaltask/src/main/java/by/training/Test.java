package by.training;


import java.util.HashMap;
import java.util.Map;

public class Test extends Thread {
    public static void main(String[] args) {
        System.out.println(get("dsf"));
        System.out.println(get("one"));
    }

    static String get(String value) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");

        return map.values().stream().filter(s -> s.equals(value)).findFirst().orElse("");

    }
}

