import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> bridge = new LinkedList<>(); // 다리 위 트럭들의 무게
        int time = 0;
        int currentWeight = 0; // 현재 다리 위 총 무게
        int truckIndex = 0; // 다음에 올려야 할 트럭의 인덱스
        int totalTrucks = truck_weights.length;

        // 다리를 완전히 채우기 위해 0(빈 칸)으로 초기화
        for (int i = 0; i < bridge_length; i++) {
            bridge.offer(0);
        }

        // 마지막 트럭이 다리를 건너기 전까지 반복
        while (truckIndex < totalTrucks) {
            time++;

            // 1. 맨 앞 트럭이 다리를 다 건넘 -> 큐에서 제거
            currentWeight -= bridge.poll();

            // 2. 다음 트럭을 올릴 수 있는지 확인
            int nextTruck = truck_weights[truckIndex];
            if (currentWeight + nextTruck <= weight) {
                bridge.offer(nextTruck);
                currentWeight += nextTruck;
                truckIndex++;
            } else {
                // 못 올리면 빈 칸(0)을 넣어서 다리 위 자리만 채움
                bridge.offer(0);
            }
        }

        // 다리 위에 남아있는 트럭들이 모두 건너는 시간 추가
        time += bridge_length;

        return time;
    }
}