import java.util.*;

class LV2_구명보트 {
    public int solution(int[] people, int limit) {
        int answer = 0; // 구명보트의 개수

        // 가장 무거운 사람이 가장 가벼운 사람과 탈 수 없다면 혼자 타야함
        // 사람 무게 오름차순으로 정렬
        Arrays.sort(people);

        int left = 0; // 가장 가벼운 사람
        int right = people.length - 1; // 가장 무거운 사람

        while(left <= right) {
            // 둘이 같이 탈 수 있음
            if(people[left] + people[right] <= limit) {
                left++;
            }

            // 가장 무거운 사람은 무조건 타야하므로 
            right--;
            answer++;
        }

        return answer;
    }
}