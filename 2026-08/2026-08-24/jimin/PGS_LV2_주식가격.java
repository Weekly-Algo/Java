import java.util.Stack;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];

        Stack<Integer> stack = new Stack<>(); //인덱스를 저장

        for(int i = 0; i < prices.length; i++) {

            //가격이 떨어졌다면
            while(!stack.isEmpty() && prices[stack.peek()] > prices[i]) {
                int index = stack.pop();
                answer[index] = i - index;
            }

            //가격이 떨어지지 않았다면 저장
            stack.push(i);
        }
            //끝까지 가격이 떨어지지 않았다면
            while(!stack.isEmpty()) {
                int index = stack.pop();
                answer[index] = prices.length - 1 - index;
            }

        return answer;
    }
}