import java.util.*;

class LV2_다리를_지나는_트럭 {
    static class Truck {
        int weight;
        int exitTime;

        public Truck(int weight, int exitTime) {
            this.weight = weight;
            this.exitTime = exitTime;
        }
    }

    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Truck> q = new ArrayDeque<>(); // 다리를 건너고 있는 트럭

        int time = 0; // 경과 시간
        int nowWeight = 0; // 현재 무게
        int index = 0;

        while(index < truck_weights.length || !q.isEmpty()) {
            time++; // 시간 증가

            // 다리 끝까지 간 트럭은 큐에서 제거
            if(!q.isEmpty() && q.peek().exitTime == time) {
                nowWeight -= q.poll().weight;
            }

            // index위치에 있는 트럭이 올라갈 수 있는지 체크 후 트럭을 큐에 넣기
            if(index < truck_weights.length &&
                    nowWeight + truck_weights[index] <= weight) {
                int truckWeight = truck_weights[index];

                q.offer(new Truck(truckWeight, time + bridge_length));

                nowWeight += truckWeight;
                index++;
            }
        }
        return time;
    }
}