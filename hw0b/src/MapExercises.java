import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapExercises {
    /** Returns a map from every lower case letter to the number corresponding to that letter, where 'a' is
     * 1, 'b' is 2, 'c' is 3, ..., 'z' is 26.
     */
    public static Map<Character, Integer> letterToNum() {
        // DONE: Fill in this function.
        return new TreeMap<>() {{
            for (char c = 'a'; c <= 'z'; c++) {
                put(c, c - 'a' + 1);
            }
        }};
    }

    /** Returns a map from the integers in the list to their squares. For example, if the input list
     *  is [1, 3, 6, 7], the returned map goes from 1 to 1, 3 to 9, 6 to 36, and 7 to 49.
     */
    public static Map<Integer, Integer> squares(List<Integer> nums) {
        // DONE: Fill in this function.
        return nums.stream().collect(TreeMap::new, (m, n) -> m.put(n, n * n), TreeMap::putAll);
    }

    /** Returns a map of the counts of all words that appear in a list of words. */
    public static Map<String, Integer> countWords(List<String> words) {
        // DONE: Fill in this function.
        return words.stream().collect(TreeMap::new, (m, w) -> m.put(w, m.getOrDefault(w, 0) + 1), TreeMap::putAll);
    }
}
