import java.util.*;

class Solution {
    ArrayList<String> list = new ArrayList<>();
    char[] arr = {'A', 'E', 'I', 'O', 'U'};

    public int solution(String word) {
        dfs("");

        return list.indexOf(word) + 1;
    }

    void dfs(String str) {
        // 6글자부터 ㄴ
        if (str.length() > 5) {
            return;
        }

        // 빈문자열 ㄴㄴ
        if (str.length() > 0) {
            list.add(str);
        }

        for (int i=0; i < arr.length; i++) {
            dfs(str + arr[i]);
        }
    } // dfs
}