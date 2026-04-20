import java.util.*;

public class LV2_주차_요금_계산 {

    public int[] solution(int[] fees, String[] records) {
        int basicTime = fees[0]; // 기본 시간
        int basicFee = fees[1]; // 기본 요금
        int unitTime = fees[2]; // 단위 시간
        int unitFee = fees[3]; // 단위 요금

        Map<Integer, String> map = new HashMap<>(); // 차량번호 - 입차, 출차 시간
        Map<Integer, Integer> timeMap = new TreeMap<>(); // 차량번호 - 누적시간
        Map<Integer, Integer> priceMap = new TreeMap<>(); // 차량번호 - 주차요금
        //for(int i = 0; i < records.length(); i++) {
        for (String s : records) {
            String[] temp = s.split(" ");
            String time = temp[0];
            int carNum = Integer.parseInt(temp[1]);
            String log = temp[2];

            // 입차
            if (log.equals("IN")) {
                map.put(carNum, time);
            }

            // 출차
            else {
                // 입차, 출차 시간 가져오기
                String[] inString = map.get(carNum).split(":");
                String[] outString = time.split(":");
                int[] inTime = new int[2];
                int[] outTime = new int[2];
                for (int i = 0; i < 2; i++) {
                    inTime[i] = Integer.parseInt(inString[i]);
                    outTime[i] = Integer.parseInt(outString[i]);
                }

                // 주차 시간 계산 후 저장
                int hour = outTime[0] - inTime[0];
                int totalMinute = outTime[1] + (60 * hour) - inTime[1];

                int prevTime = timeMap.getOrDefault(carNum, 0);
                timeMap.put(carNum, prevTime + totalMinute);

                // 출차가 끝난 것은 맵에서 제거
                map.remove(carNum);
            }
        }

        // 아직 맵에 들어있다는 것은 23:59 출차 처리가 필요
        // 모든 키를 가져온 후 해당 키로 시간 저장
        Set<Integer> keys = map.keySet();
        for (Integer key : keys) {
            String[] inString = map.get(key).split(":");
            int[] inTime = new int[2];
            for (int i = 0; i < 2; i++) {
                inTime[i] = Integer.parseInt(inString[i]);
            }

            int hour = 23 - inTime[0];
            int totalMinute = 59 + (60 * hour) - inTime[1];
            int prevTime = timeMap.getOrDefault(key, 0);
            timeMap.put(key, prevTime + totalMinute);
        }

        // 주차요금 계산
        keys = timeMap.keySet();
        for (Integer key : keys) {
            int totalTime = timeMap.get(key);

            // 총 시간이 기본시간 보다 작다면 기본요금만
            if (totalTime <= basicTime) {
                priceMap.put(key, basicFee);
            }

            // 아니라면 추가요금 더해줘야함
            // 올림하는법 a/b -> 내림 (a+b-1)/b -> 올림
            else {
                int extraTime = (totalTime - basicTime + unitTime - 1) / unitTime;
                priceMap.put(key, basicFee + (extraTime * unitFee));
            }
        }

        // 배열에 담기
        int[] answer = new int[priceMap.size()];
        keys = priceMap.keySet();
        int i = 0;
        for (int key : keys) {
            answer[i++] = priceMap.get(key);
        }

        return answer;
    }
}

