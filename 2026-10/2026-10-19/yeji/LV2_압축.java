import java.util.*;

class Solution {
    public int[] solution(String msg) {
        ArrayList<Integer> list = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();

        // 사전 초기화
        for (char c = 'A'; c <= 'Z'; c++) {
            map.put(String.valueOf(c), c - 'A' + 1);
        }

        int start = 0;
        int end = start + 1;

        while (end <= msg.length()) {
            String w = msg.substring(start, end);

            if (map.containsKey(w)) {
                end++;
            } else {
                String pre = msg.substring(start, end - 1);
                list.add(map.get(pre));

                map.put(w, map.size() + 1);

                start = end - 1;
                end = start + 1;
            }
        }

        if (start < msg.length()) {
            list.add(map.get(msg.substring(start)));
        }

        // ArrayList를 int[]로 변환
        int[] answer = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }
}
