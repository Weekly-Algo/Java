import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public String[] solution(String[] record) {
        List<String> answer = new ArrayList<>();
        Map<String, String> user = new HashMap<>();

        for(int i = 0; i < record.length; i++) { //기록만큼 반복
            String[] str = record[i].split(" "); //문자열 자르기
            String msg = str[0];
            String uid = str[1];

            if(msg.equals("Enter") || msg.equals("Change")) {
                String name = str[2];
                user.put(uid, name); //맵에 넣기
            }
        }

        for(int i = 0; i < record.length; i++) {
            String[] str = record[i].split(" "); //문자열 자르기
            String msg = str[0];
            String uid = str[1];

            if(msg.equals("Enter")) {
                String name = user.get(uid);
                answer.add(name + "님이 들어왔습니다.");
            }

            if(msg.equals("Leave")) {
                String name = user.get(uid);
                answer.add(name + "님이 나갔습니다.");
            }
        }

        return answer.toArray(new String[0]);
    }
}