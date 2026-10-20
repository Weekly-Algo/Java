import java.util.*;

class LV2_압축 {
    public int[] solution(String msg) {
        Map<String, Integer> map = new HashMap<>();
        List<Integer> result = new ArrayList<>();

        // 1. A~Z 사전 초기화
        for(int i = 0; i < 26; i++) {
            map.put( ('A' + i) + "", i + 1);
        }

        int nextIdx = 27; // map에 다음 추가 idx
        int idx = 0; // 현재 글자 idx

        // 압축 작업
        while(idx < msg.length()) {
            String w = msg.charAt(idx) + "";
            int j = idx + 1;

            // 2. 사전에 존재하는 가장 긴 문자열 찾기
            while (j < msg.length() && map.containsKey(w + msg.charAt(j))) {
                w += msg.charAt(j);
                j++;
            }

            // 3. 현재 문자열의 색인 번호 출력
            result.add(map.get(w));

            // 4. 다음 글자가 있으면 사전에 새 문자열 추가
            if (j < msg.length()) {
                map.put(w + msg.charAt(j), nextIdx++);
            }

            // 5. 처리한 문자열만큼 이동
            idx = j;
        }

        int[] answer = new int[result.size()];

        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }
}