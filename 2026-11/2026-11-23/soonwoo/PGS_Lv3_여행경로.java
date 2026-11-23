import java.util.*;

class Solution {
    static boolean[] visited;
    static String answer;  // 경로
    
    public String[] solution(String[][] tickets) {
        visited = new boolean[tickets.length];
        
        // (도착지 기준) 알파벳 순으로 정렬
        Arrays.sort(tickets, (o1, o2) -> o1[1].compareTo(o2[1]));
        
        answer = "";
        dfs("ICN", "ICN", 0, tickets);
        
        return answer.split(" ");
    }
    
    public void dfs(String currLoc, String route, int cnt, String[][] tickets) {
        if (!answer.isEmpty()) return;
        
        if (cnt == tickets.length) {
            answer = route;
            return;
        }
        
        for (int i = 0; i < tickets.length; i++) {
            if (!visited[i] && tickets[i][0].equals(currLoc)) {
                visited[i] = true;
                
                dfs(tickets[i][1], route + " " + tickets[i][1], cnt + 1, tickets);
                
                visited[i] = false;
            }
        }
    }
}