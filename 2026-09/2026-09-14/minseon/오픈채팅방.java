import java.util.*;

class Solution {

    public String[] solution(String[] record) {

        // userId, 닉네임 저장할 map
        Map<String, String> nameMap = new HashMap<>();

        // 최종 Enter, Leave 기록 저장할 리스트 배열
        List<String[]> result = new ArrayList<>();

        // 모든 record를 하나씩 확인
        for (String r : record) {

            // 공백을 기준으로 문자열 분리
            // ex) "Enter uid1234 Muzi"
            // -> ["Enter", "uid1234", "Muzi"]
            String[] parts = r.split(" ");

            // 첫 번째 -> Enter 같은 명령어
            String command = parts[0];

            // 두 번째 -> uid1234 같은 id
            String userId = parts[1];

            // Enter 명령인 경우
            if (command.equals("Enter")) {

                // 해당 namd을 해시맵에 저장
                nameMap.put(userId, parts[2]);

                // 출력에 필요하니까 result 배열에 저장
                result.add(new String[]{command, userId});
            }

            // Leave 명령인 경우
            else if (command.equals("Leave")) {

                // Leave는 닉네임 변경이 없으므로
                // 출력 기록만 저장
                result.add(new String[]{command, userId});
            }

            // Change 명령인 경우
            else if (command.equals("Change")) {

                // 해당 userId의 닉네임만 변경
                nameMap.put(userId, parts[2]);

                // Change 명령은 출력되지 않으므로 따로 저장 x
            }
        }

        // 실제 출력 결과를 저장할 배열
        String[] ans = new String[result.size()];

        // Enter / Leave 순서대로 확인
        for (int i = 0; i < result.size(); i++) {

            // 명령어
            String command = result.get(i)[0];

            // userId
            String userId = result.get(i)[1];

            // userId를 이용해서 최종 닉네임 조회
            String name = nameMap.get(userId);

            // Enter인 경우
            if (command.equals("Enter")) {

                // 최종 닉네임으로 입장 메시지 생성
                ans[i] = name + "님이 들어왔습니다.";
            }

            // Leave인 경우
            else {

                // 최종 닉네임으로 퇴장 메시지 생성
                ans[i] = name + "님이 나갔습니다.";
            }
        }

        // 완성된 결과 반환
        return ans;
    }
}