import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;

        // 끝나는 지점이 빠른 순서로 정렬
        // 끝점 기준으로 점 찍기 -> 최대한 다른 구간에 걸치기 위함
        // 각구간 끝점 비교해서 정렬
        Arrays.sort(targets, (a, b) -> a[1] - b[1]);

        int last = -1; // 지금까지 찍은 점 중 제일 마지막 위치 -> 기본값 -1

        for (int[] t : targets) {
            int s = t[0];
            int e = t[1];

            // 지금 보고 있는 구간 안에 마지막으로 찍은 점이 있으면 이미 해결된 거니까 패스
            // 시작점 s가 last보다 크거나 같으면 점이 아직 안찍힘 -> 새로 찍는다
            if (s >= last) {
                answer++;
                last = e; // 점은 해당 구간의 끝자리에 찍는다
            }
        }

        return answer;
    }
}