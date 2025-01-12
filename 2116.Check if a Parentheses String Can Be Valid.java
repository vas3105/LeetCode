/*A parentheses string is a non-empty string consisting only of '(' and ')'. It is valid if any of the following conditions is true:

It is ().
It can be written as AB (A concatenated with B), where A and B are valid parentheses strings.
It can be written as (A), where A is a valid parentheses string.
You are given a parentheses string s and a string locked, both of length n. locked is a binary string consisting only of '0's and '1's. For each index i of locked,

If locked[i] is '1', you cannot change s[i].
But if locked[i] is '0', you can change s[i] to either '(' or ')'.
Return true if you can make s a valid parentheses string. Otherwise, return false.
Input: s = "))()))", locked = "010100"
Output: true
Explanation: locked[1] == '1' and locked[3] == '1', so we cannot change s[1] or s[3].
We change s[0] and s[4] to '(' while leaving s[2] and s[5] unchanged to make s valid.
Example 2:

Input: s = "()()", locked = "0000"
Output: true
Explanation: We do not need to make any changes because s is already valid.*/
public class Solution {
    public boolean canBeValid(String s, String locked) {
        int n = s.length();
        if (n % 2 != 0) {
            return false; // Odd-length strings cannot be valid
        }
        
        int openMin = 0, openMax = 0;
        
        // Forward pass
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            char lock = locked.charAt(i);
            
            if (lock == '1') {
                if (c == '(') {
                    openMin++;
                    openMax++;
                } else { // c == ')'
                    openMin--;
                    openMax--;
                }
            } else { // lock == '0', flexible
                openMin--; // Treat as ')'
                openMax++; // Treat as '('
            }
            
            openMin = Math.max(openMin, 0); // Cannot have negative balance
            if (openMax < 0) {
                return false; // Too many closing parentheses
            }
        }
        
        // Backward pass
        openMin = 0;
        openMax = 0;
        for (int i = n - 1; i >= 0; i--) {
            char c = s.charAt(i);
            char lock = locked.charAt(i);
            
            if (lock == '1') {
                if (c == ')') {
                    openMin++;
                    openMax++;
                } else { // c == '('
                    openMin--;
                    openMax--;
                }
            } else { // lock == '0', flexible
                openMin--; // Treat as '('
                openMax++; // Treat as ')'
            }
            
            openMin = Math.max(openMin, 0); // Cannot have negative balance
            if (openMax < 0) {
                return false; // Too many opening parentheses
            }
        }
        
        return openMin == 0;
    }
}
