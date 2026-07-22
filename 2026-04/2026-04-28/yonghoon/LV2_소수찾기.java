import java.util.*;

class LV2_소수찾기 {
    static Set<Integer> set = new HashSet<>(); // 가능한 경우의 수 조합(중복이 되면 안되므로 조합 사용)
    static boolean[] visited = new boolean[7]; // 문자열 1~7

    public int solution(String numbers) {
        int answer = 0;
        numSet(numbers, "", 0);
        // 소수 개수 세기
        for(Integer num : set) {
            if(isPrime(num)) {
                answer++;
            }
        }
        return answer;
    }

    // 가능한 숫자 저장
    static void numSet(String numbers, String s, int length) {
        // 끝까지 갔으면 끝
        if(length > numbers.length()) {
            return;
        }

        for(int i = 0; i < numbers.length(); i++) {
            if(!visited[i]) {
                visited[i] = true;
                set.add(Integer.parseInt(s + numbers.charAt(i)));
                numSet(numbers, s + numbers.charAt(i), length + 1);
                visited[i] = false;
            }
        }
    }

    // 소수인지 체크
    static boolean isPrime(int num) {
        if(num < 2) {
            return false;
        }

        for(int i = 2; i < num; i++) {
            if(num % i == 0) {
                return false;
            }
        }

        return true;
    }
}