import static org.junit.Assert.*;

public class Main {
    public static void main(String[] args) {
        assertTrue(isMatch("", ""));
        assertTrue(isMatch("a", "a"));
        assertTrue(isMatch("abc", "*c"));
        assertTrue(isMatch("abc", "++c"));
        assertTrue(isMatch("abc", "a++"));
        assertTrue(isMatch("abc", "++++c"));
        assertTrue(isMatch("abc", "abc++++"));
        assertTrue(isMatch("abcdefg", "*++b+d++g*"));
        assertTrue(isMatch("abcdefg", "*"));
        assertTrue(isMatch("abcdefg", "++a++d*"));
        assertTrue(isMatch("aa", "+a"));
        assertTrue(isMatch("aa", "+++++a"));
        assertTrue(isMatch("abc", "+++++abc"));
        assertTrue(isMatch("abcabc", "+++abc"));
        assertTrue(isMatch("abcabc", "+++++++abc+++abc++"));
        assertTrue(isMatch("a", "*a*"));
        assertTrue(isMatch("aa", "*a"));
        assertTrue(isMatch("aa", "a*"));
        assertTrue(isMatch("abcabcabc", "*c"));
        assertTrue(isMatch("abcabcabc", "a++c*ab++"));
        assertTrue(isMatch("blablabla", "*ab*l++a++a"));
        assertTrue(isMatch("aaabbbabc", "*abc"));
        assertTrue(isMatch("aaabbbabc", "a*bc"));

        assertFalse(isMatch("aaa", "+a"));
        assertFalse(isMatch("abcabcabc", "abc+++++"));
        assertFalse(isMatch("", "a"));
        assertFalse(isMatch("", "+++c"));
        assertFalse(isMatch("", "*c"));
        assertFalse(isMatch("abcdefg", "+"));
        assertFalse(isMatch("abc", "c*"));
        assertFalse(isMatch("abc", "dabc"));
        assertFalse(isMatch("abc", "abcd"));
        assertFalse(isMatch("abc", "a+"));
        assertFalse(isMatch("abcd", "a+b++d++c+"));
        assertFalse(isMatch("abcdefg", ""));
        assertFalse(isMatch(null, "*a"));
        assertFalse(isMatch("bla", null));
        assertFalse(isMatch("blablabla", "*blad"));
    }

    //s     = a...z
    //regex = a...z, + = 0/1, * = 0...inf
    //examples: s=abc
    //          false: a*d , a+
    //          true:  a*, a*c, *c, *, abc*, ab+, abc+
    public static boolean isMatch(String s, String regex) {
        if(s == null || regex == null){
            return false;
        }

        if (s.isEmpty() && regex.isEmpty()) {
            return true;
        }

        if (regex.isEmpty()) {
            return false;
        }

        if (s.isEmpty()) {
            if (regex.charAt(0) == '+' || regex.charAt(0) == '*') {
                return regex.length() == 1 || isMatch(s, regex.substring(1));
            }

            return false;
        }

        if (regex.charAt(0) == '+') {
            //check this is no case of +...+*
            regex = removeExtraWildcards(regex);

            if (regex.charAt(0) == '+') {
                return isMatch(s, regex.substring(1)) || isMatch(s.substring(1), regex.substring(1));
            }
        }

        if (regex.charAt(0) == '*') {
            //remove any + or * following this *
            regex = removeExtraWildcards(regex);

            if (regex.length() == 1) {
                return true;
            }

            for (int i = 0; i < s.length(); i++) {
                if (regex.charAt(1) == s.charAt(i)) {
                    if(isMatch(s.substring(i + 1), regex.substring(2))){
                        return true;
                    }
                }
            }

            return false;
        }

        return s.charAt(0) == regex.charAt(0) && isMatch(s.substring(1), regex.substring(1));
    }

    private static String removeExtraWildcards(String regex) {
        if (regex.length() == 1) {
            return regex;
        }

        if (regex.charAt(0) == '*') {
            int i = 1;

            while (i < regex.length() && (regex.charAt(i) == '*' || regex.charAt(i) == '+')) {
                i++;
            }

            return i < regex.length() ? regex.substring(0, 1) + regex.substring(i) : regex.substring(0, 1);
        } else {
            int i = 1;

            while (i < regex.length() && regex.charAt(i) == '+') {
                i++;
            }

            if (i < regex.length() && regex.charAt(i) == '*') {
                return regex.substring(i);
            }

            return regex;
        }
    }
}