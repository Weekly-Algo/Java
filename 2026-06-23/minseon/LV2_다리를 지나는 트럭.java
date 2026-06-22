import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        // 다리 위 상태를 저장하는 큐
        Queue<Integer> bridge = new LinkedList<>();

        // 처음 다리는 비어 있으므로 다리 길이만큼 0으로 채워
        for (int i = 0; i < bridge_length; i++) {
            bridge.offer(0);
        }

        int time = 0;
        int currentWeight = 0;
        // 다음에 다리에 올릴 트럭의 인덱스
        int index = 0;

        // 모든 트럭이 다리에 올라갈 때까지 반복
        while (index < truck_weights.length) {
            // 시간이 1초 흐름
            time++;

            // 다리 맨 앞 칸이 빠져나감
            // 빠져나간 값이 트럭이면 현재 무게에서 빠짐
            currentWeight -= bridge.poll();

            // 다음 트럭을 올렸을 때 최대 무게를 넘지 않는지 확인
            if (currentWeight + truck_weights[index] <= weight) {
                // 다음 트럭을 다리에 올림
                bridge.offer(truck_weights[index]);

                // 다리 위 무게에 새 트럭 무게를 더함
                currentWeight += truck_weights[index];

                // 다음 트럭으로 이동
                index++;
            } else {
                // 무게 초과라 트럭을 못 올리면 빈 칸을 넣음
                bridge.offer(0);
            }
        }

        // 마지막 트럭이 다리에 올라가고
        // 완전히 빠져나가는 시간까지 더함
        return time + bridge_length;
    }
}