import java.util.*;
// 생각 과정
// 갈색 + 노랑 = 전체 타일
// 노랑 = 가로 -2 * 세로 -2
// 약수들 중에 답이넹

class Solution {
    public int[] solution(int brown, int yellow) {

        int total = brown + yellow;

        // 높이는 3부터 ! 음수는 불가능하니까
        // 높이는 가로와 같거나 가로보다 작음
        for(int h = 3; h <= Math.sqrt(total); h++){

            // 약수만 확인
            if(total % h != 0) continue;

            int w = total / h;

            if((w-2)*(h-2) == yellow)
                return new int[]{w, h};


        }

        return new int[]{};
    }
}