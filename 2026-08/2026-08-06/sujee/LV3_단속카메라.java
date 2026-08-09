import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;

        // 진출 지점(끝점) 기준 오름차순 정렬
        Arrays.sort(routes, (a, b) -> a[1] - b[1]);

        int last = Integer.MIN_VALUE; // 마지막으로 설치한 카메라 위치

        for (int[] r : routes) {
            int s = r[0], e = r[1];

            // 닫힌 구간이라 경계 포함! s가 last보다 "커야만" 카메라가 없는 상태
            // s <= last 면 이미 이 차량은 마지막 카메라를 만난 것
            if (s > last) {
                answer++;
                last = e; // 이 차량의 진출 지점에 카메라 설치 (뒤차들도 최대한 같이 잡히게)
            }
        }

        return answer;
    }
}
 