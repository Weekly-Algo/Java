import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> list = new ArrayList<>();
        Queue<Integer> queue = new ArrayDeque<>();

        for(int i = 0; i < progresses.length; i++) {
            int need = 100 - progresses[i];
            int day = need / speeds[i];

            if(need % speeds[i] != 0) day++; //나누어 떨어지지 않으면
            queue.offer(day);
        }

        int count = 1;
        int standard = queue.poll();

        while(!queue.isEmpty()) {
            int day = queue.poll();
            if(day <= standard) { //앞이 더 오래걸린다면
                count++;
            } else { //새로운 배포 그룹
                list.add(count);
                standard = day;
                count = 1;
            }
        }

        //마지막 배포 그룹
        list.add(count);

        int[] answer = new int[list.size()];
        for(int i = 0; i < list.size(); i++) {{
            answer[i] = list.get(i);
        }}

        return answer;
    }
}