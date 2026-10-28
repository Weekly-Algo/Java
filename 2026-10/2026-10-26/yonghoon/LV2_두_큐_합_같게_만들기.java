import java.util.*;

class LV2_두_수_합_같게_만들기 {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;

        Queue<Integer> q1 = new ArrayDeque<>();
        Queue<Integer> q2 = new ArrayDeque<>();

        // 각각의 합 구하기
        long total1 = 0;
        long total2 = 0;

        for(int i = 0; i < queue1.length; i++) {
            total1 += queue1[i];
            total2 += queue2[i];

            // 각각의 큐에 숫자 넣기
            q1.offer(queue1[i]);
            q2.offer(queue2[i]);
        }

        int maxCnt = (queue1.length + queue2.length) * 2;

        while(true) {
            // 같아지면 종료
            if(total1 == total2)
                break;

            // 최대 횟수 도달하면 -1로 바꾸고 종료
            if(answer >= maxCnt) {
                answer = -1;
                break;
            }

            // 각 큐의 합 비교해서 더 큰쪽에서 작은 쪽으로 숫자 하나 옮기기
            if(total1 > total2) {
                int num = q1.poll();
                q2.offer(num);

                total1 -= num;
                total2 += num;
            } else {
                int num = q2.poll();
                q1.offer(num);

                total1 += num;
                total2 -= num;
            }

            answer++;
        }
        return answer;
    }
}