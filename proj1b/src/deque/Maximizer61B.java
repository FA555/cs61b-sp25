package deque;

import java.util.Comparator;

public class Maximizer61B {
    /**
     * Returns the maximum element from the given iterable of comparables.
     * You may assume that the iterable contains no nulls.
     *
     * @param iterable the Iterable of T
     * @return the maximum element
     */
    public static <T extends Comparable<T>> T max(Iterable<T> iterable) {
        if (iterable == null)
            throw new IllegalArgumentException("Iterable must not be null.");

        if (!iterable.iterator().hasNext())
            return null;

        T maxElement = iterable.iterator().next();
        for (T element : iterable)
            if (element.compareTo(maxElement) > 0)
                maxElement = element;
        return maxElement;
    }

    /**
     * Returns the maximum element from the given iterable according to the specified comparator.
     * You may assume that the iterable contains no nulls.
     *
     * @param iterable the Iterable of T
     * @param comp     the Comparator to compare elements
     * @return the maximum element according to the comparator
     */
    public static <T> T max(Iterable<T> iterable, Comparator<T> comp) {
        if (iterable == null || comp == null)
            throw new IllegalArgumentException("Iterable and Comparator must not be null.");

        if (!iterable.iterator().hasNext())
            return null;

        T maxElement = iterable.iterator().next();
        for (T element : iterable)
            if (comp.compare(element, maxElement) > 0)
                maxElement = element;
        return maxElement;
    }

}
