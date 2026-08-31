import java.util.Stack;

class Solution {
    public int solution(int[] order) {
        Stack<Integer> stack = new Stack<>();
        
        int box = 1; //다음 택배 번호
        int answer = 0; //지금까지 실은 택배 개수
        int orderIndex = 0; //다음에 실어야 하는 상자의 위치

        while(box <= order.length) {
            if(order[orderIndex] == box) { //현재 컨베이어 택배가 지금 필요한 택배라면
                //트럭에 싣기
                answer++;
                orderIndex++;
                box++;
            } else { //아니라면 스택에 넣기
                stack.push(box);
                box++;
            }

            //Stack의 맨 위에 지금 필요한 택배가 있다면
            while(!stack.isEmpty() && stack.peek() == order[orderIndex]) {
                stack.pop();
                answer++;
                orderIndex++;
            }
        }

        return answer;
    }
}