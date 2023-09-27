import java.util.HashMap;
import java.util.Map;

public class Solution {
    public String minWindow(String s, String t) {
        Map<Character, Integer> charCount = new HashMap<>();
        int left = 0, minLen = Integer.MAX_VALUE, minStart = 0, count = 0;
        
        // Initialize the character count map for string t
        for (char c : t.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }
        
        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            
            // Update character count for rightChar in the map
            if (charCount.containsKey(rightChar)) {
                charCount.put(rightChar, charCount.get(rightChar) - 1);
                if (charCount.get(rightChar) >= 0) {
                    count++;
                }
            }
            
            // Try to minimize the window size
            while (count == t.length()) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minStart = left;
                }
                
                char leftChar = s.charAt(left);
                
                // Update character count for leftChar in the map
                if (charCount.containsKey(leftChar)) {
                    charCount.put(leftChar, charCount.get(leftChar) + 1);
                    if (charCount.get(leftChar) > 0) {
                        count--;
                    }
                }
                
                left++;
            }
        }
        
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }
}
