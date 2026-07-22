import java.util.*;

class Solution {
    public int solution(int[] order) {
        int loadedCount = 0; // 트럭에 실은 상자 수
        
        // 보조 컨베이어 벨트 역할 스택
        Deque<Integer> subBelt = new ArrayDeque<>();
        
        for (int box=1; box<=order.length; box++) {
            
            subBelt.push(box);
            
            while (!subBelt.isEmpty() && subBelt.peek() == order[loadedCount]) {
                subBelt.pop(); // 상자를 꺼내서 트럭에 싣음
                loadedCount++; // 트럭에 하나 실었으니, 다음에 실을 상자로 ㅌㅌ
            }
        }
        
        return loadedCount;
    }
}