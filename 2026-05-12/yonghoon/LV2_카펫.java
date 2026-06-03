import java.util.*;

class LV2_카펫 {
    public int[] solution(int brown, int yellow) {
        // 카펫의 가로와 세로 
        int totalX = 0, totalY = 0;

        for(int i = 1; i <= yellow; i++) {
            // 나누어 떨어지는 것만 체크
            if(yellow % i != 0) continue;

            // 노란색의 가로 세로 길이
            int yellowX = yellow / i; // 가로가 더 길어야함
            int yellowY = i;

            int brownX = yellowX + 2;
            int brownY = yellowY + 2;

            // 두 넓이가 같으면 저장 후 break
            if( (brownX * brownY)  - yellow == brown) {
                totalX = brownX;
                totalY = brownY;
                break;
            }
        }
        int[] answer = new int[] {totalX, totalY};
        return answer;
    }
}