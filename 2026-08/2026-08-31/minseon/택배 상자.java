import java.util.*;

class Solution {

    public int solution(int[] order) {

        // 제일 마지막에 들어간 상자부터 나올 수 있음! 그래서 stack 쓰는
        // 이 stack이 보조 컨테이너 벨트 의미
        Stack<Integer> stack = new Stack<>();

        // 메인 컨테이너 벨트에서 다음에 나올 상자 번호
        int box = 1;

        // 트럭에 실은 상자 개수
        int answer = 0;


        // order에 적힌 순서대로 필요한 상자를 확인
        for (int target : order) {

            // 현재 필요한 상자까지
            // 메인 벨트의 상자들을 이동
            while (box <= target) {

                // target 상자라면 바로 트럭에 싣기
                if (box == target) {
                    box++;
                    answer++;
                    break;
                }

                // 아직 target이 아니라면
                // 보조 컨테이너 벨트(Stack)에 넣기
                stack.push(box);
                box++;
            }

            // 메인에서 이미 실었다면 -> 보조 컨테이너 확인 X
            if (answer > 0 && order[answer - 1] == target) {
                continue;
            }


            // 보조 벨트의 가장 위 상자가 target이라면 꺼냄
            if (!stack.isEmpty() && stack.peek() == target) {

                stack.pop(); // 보조에서 꺼내고
                answer++; // 증가시킴

            } else {

                // 필요한 상자를 꺼낼 방법이 없음
                break;
            }
        }

        return answer;
    }
}