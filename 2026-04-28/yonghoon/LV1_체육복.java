import java.util.*;

class LV1_체육복 {
    public int solution(int n, int[] lost, int[] reserve) {
        Set<Integer> lostSet = new TreeSet<>();
        Set<Integer> reserveSet = new TreeSet<>();

        // reserveSet에 저장
        for(int num : reserve) {
            reserveSet.add(num);
        }

        // reserve가 있지만 도난 당했으면 reserveSet에서 제거
        // 아니라면 lostSet으로
        for(int num : lost) {
            if(reserveSet.contains(num))
                reserveSet.remove(num);
            else
                lostSet.add(num);
        }

        // lostSet에서 -1, +1한 수가 reserveSet에 들어있는지 확인
        for(int num : reserveSet) {
            if(lostSet.contains(num - 1))
                lostSet.remove(num - 1);
            else if(lostSet.contains(num + 1))
                lostSet.remove(num + 1);
        }

        int answer = n - lostSet.size();
        return answer;
    }
}