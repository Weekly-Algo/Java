import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;

        // 몸무게 순서로 정렬
        Arrays.sort(people);

        int left = 0; // 왼쪽 인덱스
        int right = people.length - 1; // 오른쪽 인덱스

        while(left <= right){
            // 몸무게 제한 안넘는 경우
            if(people[left] + people[right] <= limit){
                left++;
                right--;
            }
            // 몸무게 제한 넘는 경우 -> 무게 많이 나가는 사람만 보내버려
            else {
                right--;
            }
            answer++;
        }

        return answer;
    }
}