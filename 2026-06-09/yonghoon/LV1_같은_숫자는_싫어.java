import java.util.List;

import java.util.*;

public class LV1_같은_숫자는_싫어 {
    public int[] solution(int []arr) {
        List<Integer> list = new ArrayList<>();
        list.add(arr[0]); // 첫 원소는 넣기
        int idx = 0; // 체크 인덱스

        for(int i = 1; i < arr.length; i++) {
            // 다르다면 배열에 넣음
            if(list.get(idx) != arr[i]) {
                list.add(arr[i]);
                idx++;
            }
        }

        // 다시 배열로 변환
        int[] answer = new int[list.size()];
        for(int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }
}