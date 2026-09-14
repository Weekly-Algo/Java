import java.util.*;

// 후기?
// 첨엔 for문 써서 구현처럼 해보려고 했는데...
// 테케 일부가 시간초과가 나더라고욥...
// 그래서 약간의 도움을 받아...ㅎㅎ 해쉬맵으로 고쳤습니다...허허

class Solution {
    public String[] solution(String[] record) {

        Map<String, String> map = new HashMap<>();

        // 닉네임 저장
        for (String r : record) {
            String[] arr = r.split(" ");

            // 키 : 아이디 , 값 : 닉네임으로 저장 -> 갱신됨~
            if (arr[0].equals("Enter") || arr[0].equals("Change")) {
                map.put(arr[1], arr[2]);
            }
        }

        List<String> answer = new ArrayList<>();

        for (String r : record) {
            String[] arr = r.split(" ");

            if (arr[0].equals("Enter")) {
                answer.add(map.get(arr[1]) + "님이 들어왔습니다.");
            }
            else if (arr[0].equals("Leave")) {
                answer.add(map.get(arr[1]) + "님이 나갔습니다.");
            }
        }

        return answer.toArray(new String[0]);
    }
}