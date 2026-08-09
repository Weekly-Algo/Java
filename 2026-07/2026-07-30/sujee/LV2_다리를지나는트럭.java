import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        Queue<Integer> bridge = new ArrayDeque<>(); // 다리 위 상황
        int count_truck = 0; // 다리를 이미 지난 트럭 수

        // 모든 트럭이 길을 건널때까지 반복
        while(count_truck != truck_weights.length) {
            answer++; // 일단 1초 더한다.
            // 다리가 비어있으면 일단 트럭 하나 넣는다.
            if(bridge.size() == 0) {
                bridge.offer(truck_weights[idx++]);
                continue;
            }

            // 다리가 꽉 찬 경우 트럭을 하나 뺀다.
            if(bridge.size() == bridge_length) {
                int tmp = bridge.poll();
                if(tmp != 0) count_truck++;
            }

            int sum = 0;
            for(int w : bridge) {
                sum += w;
            }
            // weight를 안넘어가는 선에서 트럭을 다리에 올린다.
            if(idx < truck_weights.length && sum + truck_weights[idx] <= weight) {
                bridge.offer(truck_weights[idx++]);
                continue;
            }

            // 트럭을 더이상 올릴수 없다면 (무게제한) 0을 추가해 한칸 밀어준다.
            bridge.offer(0);
        }
        return answer;
    }
}