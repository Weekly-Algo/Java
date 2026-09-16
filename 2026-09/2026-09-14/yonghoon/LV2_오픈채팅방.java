import java.util.*;

class LV2_오픈채팅방 {
    public String[] solution(String[] record) {

        HashMap<String, String> nicknames = new HashMap<>(); // 닉네임 이력
        List<String[]> log = new ArrayList<>(); // 로그

        // 로그 순회
        for(String s : record) {
            String[] info = s.split(" ");
            String state = info[0];
            String uid = info[1];

            // 입장함
            if(state.equals("Enter")) {
                nicknames.put(uid, info[2]); // 이름등록
                log.add(new String[] {uid, "Enter"});
            } else if(state.equals("Leave")) { // 나감
                log.add(new String[] {uid, "Leave"});
            } else { // 이름 변경
                nicknames.put(uid, info[2]);
            }
        }

        // 정답
        String[] answer = new String[log.size()];

        for(int i = 0; i < answer.length; i++) {
            String uid = log.get(i)[0]; // 해당 로그의 uid가져오기
            String command = log.get(i)[1]; // 해당 로그의 상태 가져오기

            if (command.equals("Enter")) {
                answer[i] = nicknames.get(uid) + "님이 들어왔습니다.";
            } else {
                answer[i] = nicknames.get(uid) + "님이 나갔습니다.";
            }
        }
        return answer;
    }
}