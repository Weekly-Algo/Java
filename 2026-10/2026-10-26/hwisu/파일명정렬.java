import java.util.*;

class Solution {
    public String[] solution(String[] files) {
        
        // 정렬
        Arrays.sort(files, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                String headA = getHead(a).toLowerCase();
                String headB = getHead(b).toLowerCase();
                
                // HEAD 비교
                int cmp = headA.compareTo(headB);
                if (cmp != 0) {
                    return cmp;
                }
                
                // NUMBER 비교
                int numA = getNumber(a);
                int numB = getNumber(b);
                return numA - numB;
            } // compare
        });
        
        return files;
        
    } // solution
    
    // HEAD 구하기
    static String getHead(String file) {
        int i = 0;
        while (i<file.length() && !Character.isDigit(file.charAt(i))) {
            i++;
        } // while
        return file.substring(0, i);
    } // getHead
    
    // NUMBER 구하기
    static int getNumber(String file) {
        int start = getHead(file).length();
        int end = start;
        while (end<file.length() && end-start<5 && Character.isDigit(file.charAt(end))) {
            end++;
        } // while
        return Integer.parseInt(file.substring(start, end));
    } // getNumber
} // Solution