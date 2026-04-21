import java.util.ArrayDeque;
import java.util.Queue;

class TargetSolution {
    static int n;

    public int solution(int[] numbers, int target) {
        n = numbers.length;

        //bfs
        int answer = bfs(numbers, target);

        return answer;
    }

    public static int bfs(int[] numbers, int target) {
        int count = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0}); //현재까지의 값, 인덱스 큐에 넣기

        while(!queue.isEmpty()) { //큐가 빌때까지
            int[] curr = queue.poll(); //하나 꺼내기
            int sum = curr[0]; //현재의 합
            int idx = curr[1]; //현재의 인덱스

            if(idx == n) { //끝까지 다 썼다면
                if (sum == target) count++; //개수 증가
                continue; //계속하기
            }

            queue.offer(new int[]{sum + numbers[idx], idx + 1}); //더한 경우를 큐에 넣기
            queue.offer(new int[]{sum - numbers[idx], idx + 1}); //뺀 경우를 큐에 넣기
        }

        return count;
    }
}

public class PRG_L2_타겟_넘버 {
    public static void main(String[] args) {
        TargetSolution sol = new TargetSolution();

        int[] numbers = {1, 1, 1, 1, 1};

        int result = sol.solution(numbers, 3);
        System.out.println(result);
    }
}
