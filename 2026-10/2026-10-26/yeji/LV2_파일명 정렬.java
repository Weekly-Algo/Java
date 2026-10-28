import java.util.Arrays;

class Solution {
    public String[] solution(String[] files) {
        String[][] arr = new String[files.length][3];

        // 파일별 HEAD, NUMBER 추출
        for (int i = 0; i < files.length; i++) {
            String file = files[i];

            // NUMBER 시작 위치 찾기
            int start = 0;

            for (int j = 0; j < file.length(); j++) {
                if (Character.isDigit(file.charAt(j))) {
                    start = j;
                    break;
                }
            }

            // NUMBER 끝 위치 찾기
            int end = start;

            for (int j = start; j < file.length(); j++) {
                if (!Character.isDigit(file.charAt(j)) || j - start >= 5) {
                    break;
                }
                end = j + 1;
            }

            // 파일 정보 저장
            arr[i][0] = file;
            arr[i][1] = file.substring(0, start);
            arr[i][2] = file.substring(start, end);
        }

        // HEAD, NUMBER 순서로 정렬
        Arrays.sort(arr, (a, b) -> {
            int result = a[1].compareToIgnoreCase(b[1]);

            if (result != 0) {
                return result;
            }

            return Integer.compare(
                Integer.parseInt(a[2]),
                Integer.parseInt(b[2])
            );
        });

        String[] answer = new String[files.length];

        // 정렬된 파일명 저장
        for (int i = 0; i < files.length; i++) {
            answer[i] = arr[i][0];
        }

        return answer;
    }
}
