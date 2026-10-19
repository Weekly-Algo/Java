
import java.util.*;

class Solution {
    public int[] solution(String msg) {
        Map<String, Integer> dict = new HashMap<>();

        // A~Z를 1~26번으로 등록
        for (int i = 0; i < 26; i++)
            dict.put("" + (char)('A' + i), i + 1);

        int[] temp = new int[msg.length()];
        int count = 0;
        int num = 27;
        int i = 0;

        while (i < msg.length()) {
            String w = "";

            // 사전에 있는 가장 긴 문자열 찾기
            for (int len = 1; i + len <= msg.length(); len++) {
                String s = msg.substring(i, i + len);

                if (!dict.containsKey(s))
                    break;

                w = s;
            }

            // 찾은 문자열의 번호 저장
            temp[count++] = dict.get(w);

            // 다음 글자까지 합친 문자열을 사전에 추가
            if (i + w.length() < msg.length()) {
                String next = w + msg.charAt(i + w.length());
                dict.put(next, num++);
            }

            // 처리한 글자만큼 이동
            i += w.length();
        }

        // 정답 길이만큼 배열 생성 후 복사
        int[] answer = new int[count];
        for (int j = 0; j < count; j++)
            answer[j] = temp[j];

        return answer;
    }
}
