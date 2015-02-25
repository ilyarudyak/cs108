import java.util.*;

public class Appearances {


	/**
	 * Returns the number of elements that appear the same number
	 * of times in both collections. Static method. (see handout).
	 * @return number of same-appearance elements
	 */
	public static <T> int sameCount(Collection<T> a, Collection<T> b) {

        int count = 0;
        Map<T, Integer> mapA = fillMap(a);
        Map<T, Integer> mapB = fillMap(b);

        for(T key: mapA.keySet()) {

            if (mapB.containsKey(key) &&
                    mapA.get(key).equals(mapB.get(key))) {
                count++;
            }
        }

		return count;
	}

    private static <T> Map<T, Integer> fillMap(Collection<T> a) {
        Map<T, Integer> map = new HashMap<>();

        for(T t: a) {
            if (map.containsKey(t)) { map.put(t, map.get(t)+1); }
            else { map. put(t, 1); }
        }
        return map;
    }

    public static void main(String[] args) {

        List<Integer> a = Arrays.asList(1, 2, 3, 1, 2, 3, 5);
        List<Integer> b = Arrays.asList(1, 2, 3, 1, 2, 3, 5);
        System.out.println(sameCount(a, b));
    }

}
