import java.util.*;
// 제곱의 합이 최소가 되는 경우가 문제의 관건
// 그러면 가장 큰 수에서 빼는게..?
// 그러면 가장 큰 수에서 빼고 또 빼고 그거의 합이 n이 되면 되지 않을까?

class Solution {
    public long solution(int n, int[] works) {

        PriorityQueue<Integer> pq =
                new PriorityQueue<>();


        int total = 0;
        for (int w : works) {
            pq.offer(-w);
            // 여기는 3번 을 고려하지 못해 추후에 추가함..ㅠ
            total += w;

        }

        if (total <= n) return 0;

        for (int i = 1; i <= n; i++) {
            pq.offer(pq.poll() + 1);
        }

        long ans = 0;

        // work 변수 선언에서 처음에 int 실수를 함!! 다음부터 주의하기!
        while (!pq.isEmpty()) {
            long work = -pq.poll();
            ans += work * work;
        }

        return ans;
    }
}