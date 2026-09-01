import java.util. *;

class LV2_택배상자 {
    public int solution(int[] order) {
        int answer = 0; // 총 실을 수 있는 상자 수
        Stack<Integer> stack = new Stack<>(); // 보조 컨테이너 벨트
        int index = 0;

        // 일단 보조컨테이너로 옮기고 바로 확인
        for(int box = 1; box <= order.length; box++) {
            stack.push(box);

            while(!stack.isEmpty()) {
                // 순서가 일치함
                if(order[index] == stack.peek()) {
                    stack.pop();
                    index++;
                    answer++;
                } else
                    // 일치하지 않는다면 while문 종료
                    break;
            }
        }

        return answer;
    }
}