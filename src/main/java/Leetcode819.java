import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Leetcode819 {


    public String mostCommonWord(String paragraph, String[] banned) {
        Set<String> bannedSet = new HashSet<>();
        for (String b : banned) {
            bannedSet.add(b.toLowerCase());
        }
        String[] ss = paragraph.toLowerCase().split("[\\s\\.,;?!']");
        Map<String, Integer> freqMap = new HashMap<>();
        for (String s : ss) {
            s = s.trim();
            if (s.isEmpty()) {
                continue;
            }
            if (bannedSet.contains(s)) {
                continue;
            }
            Integer freq = freqMap.getOrDefault(s, 0);
            freqMap.put(s, freq+1);
        }
        String max = null;
        int maxFreq = -1;
        for (Map.Entry<String, Integer> e : freqMap.entrySet()) {
            if (e.getValue() > maxFreq) {
                max = e.getKey();
                maxFreq = e.getValue();
            }
        }
        return max;
    }

}
