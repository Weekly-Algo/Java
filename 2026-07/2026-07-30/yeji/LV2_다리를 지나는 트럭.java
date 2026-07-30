import java.util.*;

class Solution {

    public int solution(int bridge_length, int weight, int[] truck_weights) {

        // 대기 중인 트럭
        ArrayDeque<Integer> waitingTrucks = new ArrayDeque<>();

        // 트럭의 무게와 다리에서 나가는 시간을 큐에 저장
        // int[0]: 트럭 무게
        // int[1]: 다리에서 나가는 시간
        ArrayDeque<int[]> bridge = new ArrayDeque<>();

        // 대기 중인 트럭 큐에 저장
        for (int truck : truck_weights) {
            waitingTrucks.offer(truck);
        }

        int answer = 0;        // 경과 시간
        int bridgeWeight = 0;  // 다리 위에 있는 트럭의 총 무게

        // 대기 트럭이 남아 있거나 다리 위에 트럭이 있으면 반복
        while (!waitingTrucks.isEmpty() || !bridge.isEmpty()) {

            // 1초 경과
            answer++;

            // 현재 시간에 다리를 빠져나가는 트럭이 있으면 제거
            if (!bridge.isEmpty() && bridge.peek()[1] == answer) {

                int[] out = bridge.poll();

                // 빠져나간 트럭 무게를 다리 위 총무게에서 제거
                bridgeWeight -= out[0];
            }

            // 대기 중인 트럭이 있고, 무게 제한을 넘지 않으면 진입
            if (!waitingTrucks.isEmpty()
                    && bridgeWeight + waitingTrucks.peek() <= weight) {

                // 대기 중인 다음 트럭을 꺼냄
                int truck = waitingTrucks.poll();

                // 다리 위 총무게에 트럭 무게 추가
                bridgeWeight += truck;

                // 트럭의 무게와 다리에서 나가는 시간을 큐에 추가
                bridge.offer(new int[]{
                    truck,
                    answer + bridge_length
                });
            }
        }

        return answer;
    }
}