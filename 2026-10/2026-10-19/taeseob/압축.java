import java.util.*;

class Solution {
    public int[] solution(String msg) {
        Map<String,Integer> map = new HashMap<>();
        Deque<Integer> q = new ArrayDeque<>();
        for(int i = 1 ; i < 27; i++) map.put(String.valueOf((char)('A'-1+i)),i);
        int i = 0, j = 1, flag = 27;
        
        while(j <= msg.length()){//j는 포함이 아니기 때문에 0123 이면 4까지
            String check = msg.substring(i,j);
            if(map.containsKey(check)) {
                j++;
            }// 사전에 있다면 다음 칸으로
            else{//없다면,,,?
                q.offer(map.get(msg.substring(i,j-1)));//큐에 한 칸 이전 숫자 넣기
                map.put(check,flag++); // 값 넣기
                i = j-1; //i는 현재까지 길이만큼 이동시키기 j는 포함이 아니기 떄문에 -1
            }
        }
        q.offer(map.get(msg.substring(i,j-1)));
        int[] answer = new int[q.size()];
        for(int r = 0; !q.isEmpty(); r++) answer[r] = q.poll();
        
        return answer;
    }
}