package algo.backTracking;

public class IndexOf {

    /**
     * Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null || haystack.length() < needle.length()) {
            return - 1;
        }
        else {
            if ("".equals(needle)) {
                return 0;
            }
            else{
                for (int i = 0; i < haystack.length() - needle.length(); i++) {
                    int j = i;
                    for (; j < i + needle.length(); j++) {
                        if (haystack.charAt(j) != needle.charAt(j-i)) {
                            break;
                        }
                    }
                    if(j == i + needle.length()){
                        return i;
                    }
                }
                return -1;
            }
        }
    }
}
