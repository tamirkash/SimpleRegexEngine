import static org.junit.Assert.*;

public class Main {
    public static void main(String[] args) {
        assertTrue(MyRegex.isMatch("", ""));
        assertTrue(MyRegex.isMatch("abc", "*"));
        assertTrue(MyRegex.isMatch("abc", "*****"));
        assertTrue(MyRegex.isMatch("abc", "***+**+"));
        assertTrue(MyRegex.isMatch("", "*"));
        assertTrue(MyRegex.isMatch("a", "a"));
        assertTrue(MyRegex.isMatch("abc", "*c"));
        assertTrue(MyRegex.isMatch("abc", "++c"));
        assertTrue(MyRegex.isMatch("abc", "a++"));
        assertTrue(MyRegex.isMatch("abc", "++++c"));
        assertTrue(MyRegex.isMatch("abc", "abc++++"));
        assertTrue(MyRegex.isMatch("abcdefg", "*++b+d++g*"));
        assertTrue(MyRegex.isMatch("abcdefg", "*"));
        assertTrue(MyRegex.isMatch("abcdefg", "++a++d*"));
        assertTrue(MyRegex.isMatch("aa", "+a"));
        assertTrue(MyRegex.isMatch("aa", "+++++a"));
        assertTrue(MyRegex.isMatch("abc", "a+c"));
        assertTrue(MyRegex.isMatch("abc", "+++++abc"));
        assertTrue(MyRegex.isMatch("abcabc", "+++abc"));
        assertTrue(MyRegex.isMatch("abcabc", "+++++++abc+++abc++"));
        assertTrue(MyRegex.isMatch("a", "*a*"));
        assertTrue(MyRegex.isMatch("aa", "*a"));
        assertTrue(MyRegex.isMatch("aa", "a*"));
        assertTrue(MyRegex.isMatch("abcabcabc", "*c"));
        assertTrue(MyRegex.isMatch("abcabcabc", "a++c*ab++"));
        assertTrue(MyRegex.isMatch("blablabla", "*ab*l++a++a"));
        assertTrue(MyRegex.isMatch("aaabbbabc", "*abc"));
        assertTrue(MyRegex.isMatch("aaabbbabc", "a*bc"));
        assertTrue(MyRegex.isMatch("aa", "++"));
        assertTrue(MyRegex.isMatch("aa", "+++"));
        assertTrue(MyRegex.isMatch("ab", "+++"));

        assertFalse(MyRegex.isMatch("aaa", "+a"));
        assertFalse(MyRegex.isMatch("abcabcabc", "abc+++++"));
        assertFalse(MyRegex.isMatch("", "a"));
        assertFalse(MyRegex.isMatch("", "+++c"));
        assertFalse(MyRegex.isMatch("", "*c"));
        assertFalse(MyRegex.isMatch("abcdefg", "+"));
        assertFalse(MyRegex.isMatch("abc", "c*"));
        assertFalse(MyRegex.isMatch("abc", "dabc"));
        assertFalse(MyRegex.isMatch("abc", "abcd"));
        assertFalse(MyRegex.isMatch("abc", "a+"));
        assertFalse(MyRegex.isMatch("abcd", "a+b++d++c+"));
        assertFalse(MyRegex.isMatch("abcdefg", ""));
        assertFalse(MyRegex.isMatch(null, "*a"));
        assertFalse(MyRegex.isMatch("bla", null));
        assertFalse(MyRegex.isMatch("blablabla", "*blad"));
        assertFalse(MyRegex.isMatch("aa", "+"));
    }
}