/*
 HW1 Taboo problem class.
 Taboo encapsulates some rules about what objects
 may not follow other objects.
 (See handout).
*/

import java.util.*;

public class Taboo<T> {

    private List<T> rules;
	
	/**
	 * Constructs a new Taboo using the given rules (see handout.)
	 * @param rules rules for new Taboo
	 */
	public Taboo(List<T> rules) {
        this.rules = rules;
	}
	
	/**
	 * Returns the set of elements which should not follow
	 * the given element.
	 * @param t0
	 * @return elements which should not follow the given element
	 */
	public Set<T> noFollow(T t0) {

        Set<T> set = new TreeSet<>();
        T prev = null;
        for(T t: rules) {
            if (prev != null && prev.equals(t0)) { set.add(t); }
            prev = t;
        }
		return set;
	}
	
	/**
	 * Removes elements from the given list that
	 * violate the rules (see handout).
	 * @param list collection to reduce
	 */
	public void reduce(List<T> list) {

        T t;
        T prev = null;
        Iterator<T> iterator = list.iterator();

        while (iterator.hasNext()){

            t = iterator.next();
            if (prev != null && noFollow(prev).contains(t)) {
                iterator.remove();
            } else {
                prev = t;
            }
        }
	}

    public static void main(String[] args) {

        List<String> rules = Arrays.asList("a", "c", "a", "b");
        Taboo taboo = new Taboo(rules);

        List<String> list = new ArrayList<>(Arrays.asList("a", "c", "b", "x", "c", "a"));
        taboo.reduce(list);
        System.out.println(list);
    }
}














