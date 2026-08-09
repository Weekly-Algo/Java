import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> bridge = new LinkedList<>();

        // 다리를 빈 공간(0)으로 채운다.
        for (int i = 0; i < bridge_length; i++) {
            bridge.offer(0);
        }

        int time = 0;
        int currentWeight = 0;
        int truckIndex = 0;

        // 모든 트럭이 다리에 진입할 때까지 반복한다.
        while (truckIndex < truck_weights.length) {
            time++;

            // 다리 맨 앞의 트럭 또는 빈 공간을 제거한다.
            int exitedTruck = bridge.poll();
            currentWeight -= exitedTruck;

            int nextTruck = truck_weights[truckIndex];

            // 다음 트럭이 다리에 올라갈 수 있는 경우
            if (currentWeight + nextTruck <= weight) {
                bridge.offer(nextTruck);
                currentWeight += nextTruck;
                truckIndex++;
            } else {
                // 트럭이 올라갈 수 없다면 빈 공간을 추가한다.
                bridge.offer(0);
            }
        }

        /*
         * 마지막 트럭이 다리에 올라간 뒤에도
         * 다리 길이만큼 이동해야 완전히 빠져나온다.
         */
        return time + bridge_length;
    }
}