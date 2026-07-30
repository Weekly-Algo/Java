import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> truckQueue = new ArrayDeque<>();
        Queue<Integer> bridgeQueue = new ArrayDeque<>();

        for(int i = 0; i < truck_weights.length; i++) {
            truckQueue.offer(truck_weights[i]);
        }

        for(int i = 0; i < bridge_length; i++) {
            bridgeQueue.offer(0);
        }

        int time = 0;
        int totalWeight = 0;
        while(!truckQueue.isEmpty() || totalWeight > 0) {
            int out = bridgeQueue.poll(); //다리큐에서 빼기
            totalWeight -= out; //무게 계산하기
            if(!truckQueue.isEmpty() && totalWeight + truckQueue.peek() <= weight) { //현재 남은 트럭이 있고 다리의 무게가 10kg 이하라면
                int truck = truckQueue.poll(); //트럭큐에서 빼기
                bridgeQueue.offer(truck); //다리큐에 넣기
                totalWeight += truck; //무게 계산하기
            } else { //10kg 초과라면
                bridgeQueue.offer(0); //0 넣기
            }
            time++;
        }
        return time;
    }
}