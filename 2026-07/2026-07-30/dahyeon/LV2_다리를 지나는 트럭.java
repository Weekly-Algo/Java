import java.util.*;

// 브릿지 길이가 정해져 있고, 최대 무게 ..?
// 덱의 크기를 어떻게 설정하지가 최대 고민이었음
class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {

        Deque<Integer> q = new ArrayDeque<>();

        for (int i = 0; i < bridge_length; i++) {
            q.offer(0);
        }
        // 현재 다리의 무게
        int bridgeWeight = 0;
        int second = 0;

        for (int w : truck_weights) {
            while (true) {
                second++;
                bridgeWeight -= q.poll();

                if (bridgeWeight + w <= weight) {
                    q.offer(w);
                    bridgeWeight += w;
                    break;
                }

                q.offer(0);

            }
        }

        return second + bridge_length;


    }
}