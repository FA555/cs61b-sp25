import java.util.List;

public class ListExercises {

    /** Returns the total sum in a list of integers */
    public static int sum(List<Integer> L) {
        // DONE: Fill in this function.
        return L.stream().reduce(0, Integer::sum);
    }

    /** Returns a list containing the even numbers of the given list */
    public static List<Integer> evens(List<Integer> L) {
        // DONE: Fill in this function.
        return L.stream().filter(i -> i % 2 == 0).toList();
    }

    /** Returns a list containing the common item of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        // DONE: Fill in this function.
        return L1.stream().distinct().filter(L2::contains).toList();
    }

    /** Returns the number of occurrences of the given character in a list of strings. */
    public static int countOccurrencesOfC(List<String> words, char c) {
        // DONE: Fill in this function.
        return words.stream().mapToInt(word -> (int) word.chars().filter(ch -> ch == c).count()).sum();
    }
}
