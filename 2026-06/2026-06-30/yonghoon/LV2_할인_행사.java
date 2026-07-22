import java.util.*;

class LV2_할인_행사 {
    public int solution(String[] want, int[] number, String[] discount) {
        // 원하는 물품 map 생성
        HashMap<String, Integer> wantMap = new HashMap<>();
        for(int i = 0; i < want.length; i++) {
            String wantProc = want[i];
            int wantNum = number[i];
            wantMap.put(wantProc, wantNum);
        }

        // 회원가입 가능 날짜
        int answer = 0;
        for(int i = 0; i <= discount.length - 10; i++) {
            // 할인 map 생성
            HashMap<String, Integer> discountMap = new HashMap<>();
            for(int j = i; j < i + 10; j++) {
                String discountProc = discount[j];
                int num = discountMap.getOrDefault(discountProc, 0);
                discountMap.put(discountProc, num + 1);
            }

            // 할인 map과 원하는 물품 map이 일치하는지 검사
            boolean isOk = true;
            for(String proc : wantMap.keySet()) {
                if(wantMap.get(proc) != discountMap.getOrDefault(proc, 0)) // 할인 map의 경우 없을 수도 있으니 getOrDefault 사용
                {
                    isOk = false;
                    break;
                }
            }

            // isOk가 true이면 일치하므로 회원가입 가능
            if(isOk)
                answer++;
        }
        return answer;
    }
}