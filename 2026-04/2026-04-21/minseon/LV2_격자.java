import java.util.*;

class Solution {

    //전체 칸 수 w * h
    //가로, 세로 한줄씩 둘러싸고 있으니까
    //안쪽 격자의 수는 (w-2) * (h-2)
    //예시처럼 4*3 -> 안쪽 격자 (2*1)

    public int[] solution(int brown, int yellow) {
        int total = brown + yellow; //전체 칸수


        for(int h=1; h<=total; h++) {
            //약수면
            if (total % h == 0) {
                int w = total / h;

                if((w-2) * (h-2) == yellow) {
                    return new int[]{w, h}; //만족하는 정점 출력
                }
            }
        }

        return new int[]{0, 0}; //반복 끝나도 없으면 0, 0 출력
    }
}