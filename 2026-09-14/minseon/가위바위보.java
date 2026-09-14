import java.util.*;

public class Main {

    public static int[] solution(int[] point, int[] arr, int time) {

        // time초 동안 반복
        for (int i = 0; i < time; i++) {

            // 현재 상태를 복사해서 다음 상태 배열 생성
            int[] next = arr.clone();

            // 모든 정점 확인
            for (int j = 0; j < arr.length; j++) {

                // j번 정점이 가리키는 정점 번호
                // point는 1번부터 시작하므로 배열 인덱스로 바꾸기 위해 -1
                int target = point[j] - 1;

                // 현재 j번 정점의 상태
                int currState = arr[j];

                // j번 정점이 가리키는 상대 정점의 상태
                int targetState = arr[target];

                // 현재 정점이 상대 정점을 이겼다면
                if (isWin(currState, targetState)) {

                    // 상대 정점의 다음 상태를 현재 정점의 상태로 변경
                    next[target] = currState;
                }
            }

            // 모든 정점 판정이 끝난 후 상태를 한 번에 적용
            arr = next;
        }

        // time초 후 최종 상태 반환
        return arr;
    }

    // 현재 상태가 상대 상태를 이기는지 확인
    public static boolean isWin(int current, int target) {

        // 가위(1) > 보(3)
        if (current == 1 && target == 3) {
            return true;
        }

        // 바위(2) > 가위(1)
        if (current == 2 && target == 1) {
            return true;
        }

        // 보(3) > 바위(2)
        if (current == 3 && target == 2) {
            return true;
        }

        // 패배 또는 무승부
        return false;
    }
}