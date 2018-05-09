public class MyRegex {
    /*
        s     = a...z
        regex = a...z, + = 0/1, * = 0...inf
        examples: s=abc
                  false: a*d , a+
                  true:  a*, a*c, *c, *, abc*, ab+, abc+
    */
    public static boolean isMatch(String s, String regex) {
        if (!isaValidInput(s, regex) || (regex.isEmpty() && !s.isEmpty())){
            return false;
        }

        if (s.isEmpty()) {
            return regex.isEmpty() || isRegOnlyWildcards(regex);
        }

        if (regex.charAt(0) == '+') {
            //check this is no case of +...+*
            regex = removeExtraWildcards(regex);

            if (regex.charAt(0) == '+') {
                return isMatchWithOrWithoutFirstChar(s, regex);
            }
        }

        if (regex.charAt(0) == '*') {
            //remove any + or * following this *
            regex = removeExtraWildcards(regex);

            return isMatchAfterAsterisk(s, regex);
        }

        return s.charAt(0) == regex.charAt(0) && isMatch(s.substring(1), regex.substring(1));
    }

    private static boolean isMatchAfterAsterisk(String s, String regex) {
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

    private static String removeExtraWildcards(String regex) {
        return regex.charAt(0) == '*' ? removeExtraWildcardsAfterAsterisk(regex) :
                removeExtraWildcardsBeforeAsterisk(regex);
    }

    //e.g **+++*+_ => *_
    private static String removeExtraWildcardsAfterAsterisk(String regex) {
        int i = 1;

        while (i < regex.length() && isCharWildcard(regex.charAt(i))) {
            i++;
        }

        return i < regex.length() ? regex.substring(0, 1) + regex.substring(i) : regex.substring(0, 1);
    }

    //e.g +++*_ => *_
    private static String removeExtraWildcardsBeforeAsterisk(String regex) {
        int i = 1;

        while (i < regex.length() && regex.charAt(i) == '+') {
            i++;
        }

        return i < regex.length() && regex.charAt(i) == '*' ? regex.substring(i) : regex;
    }

    private static boolean isMatchWithOrWithoutFirstChar(String s, String regex) {
        return isMatch(s, regex.substring(1)) || isMatch(s.substring(1), regex.substring(1));
    }

    private static boolean isRegOnlyWildcards(String regex) {
        for(int i = 0; i < regex.length(); i++){
            if(!isCharWildcard(regex.charAt(i))){
                return false;
            }
        }

        return true;
    }

    private static boolean isCharWildcard(char c) {
        return c == '+' || c == '*';
    }

    private static boolean isaValidInput(String s, String regex) {
        return s != null && regex != null;
    }
}
