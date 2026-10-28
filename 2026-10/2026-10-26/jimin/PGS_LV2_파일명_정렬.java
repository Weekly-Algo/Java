import java.util.ArrayList;
import java.util.List;

class Solution {
    public String[] solution(String[] files) {
        String[] answer = new String[files.length];
        List<String[]> list = new ArrayList<>();

        for(int i = 0; i < files.length; i++) {
            String file = files[i];
            
            // Number의 시작 위치 찾기
            int start = 0;
            for(int j = 0; j < file.length(); j++) {
                if(Character.isDigit(file.charAt(j))) {
                    start = j;
                    break;
                }
            }

            // Head 추출하기
            String head = file.substring(0, start);

            // Number의 끝 찾기
            int end = start;

            for(int j = start; j < file.length() && j < start + 5; j++) {
                if(!Character.isDigit(file.charAt(j))) break;
                end = j + 1;
            }

            String number = file.substring(start, end);
            
            //리스트에 저장
            list.add(new String[]{head, number, file});
        }

        list.sort((a, b) -> {
            int headCompare = a[0].compareToIgnoreCase(b[0]); // 대소문자 구분없이 비교
            
            // Head가 다르다면 Head를 기준으로 정렬
            if(headCompare != 0) { 
                return headCompare;
            }

            // Head가 같다면 Number로 비교
            int numA = Integer.parseInt(a[1]);
            int numB = Integer.parseInt(b[1]);
            return Integer.compare(numA, numB);
        });

        // 정렬된 리스트에서 원본 파일명만 가져오기
        for(int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i)[2];
        }

        return answer;
    }
}