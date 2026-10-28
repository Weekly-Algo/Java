import java.util.*;

class LV2_파일명_정렬 {
    static class Word {
        String word;
        String head;
        int num;
        int idx;

        Word(String word, String head, int num, int idx) {
            this.word = word;
            this.head = head;
            this.num = num;
            this.idx = idx;
        }
    }
    public String[] solution(String[] files) {
        String[] answer = new String[files.length];

        PriorityQueue<Word> pq = new PriorityQueue<>((a, b) -> {
            int wordCompare = a.head.toLowerCase().compareTo(b.head.toLowerCase());
            int numCompare = Integer.compare(a.num, b.num);

            // head부터 비교
            if(wordCompare != 0)
                return wordCompare;

            // 다음 숫자 비교
            if(numCompare != 0)
                return numCompare;

            // 마지막으로 들어온 순서 비교
            return Integer.compare(a.idx, b.idx);
        });

        for(int i = 0; i < files.length; i++) {
            String file = files[i];

            int numStart = -1; // 숫자 시작
            int numEnd = -1; // 숫자 끝

            // 숫자 시작하는 부분 찾기
            for(int j = 0; j < file.length(); j++) {
                char c = file.charAt(j);

                // 숫자 시작부분
                if(c - '0' >= 0 && c - '0' <= 9) {
                    numStart = j;
                    break;
                }
            }

            // 숫자 끝나는 부분 찾기
            for(int j = numStart; j < file.length(); j++) {
                char c = file.charAt(j);

                // 숫자 끝나는 부분
                if(!(c - '0' >= 0 && c - '0' <= 9)) {
                    numEnd = j;
                    break;
                }
            }

            // 숫자 끝나는부분을 못찾았으면 문자열 길이 넣기
            if(numEnd == -1)
                numEnd = file.length();

            // head와 숫자부분 계산
            String head = file.substring(0, numStart);
            int num = Integer.parseInt(file.substring(numStart, numEnd));

            // pq에 넣기(자동 정렬)
            pq.offer(new Word(file, head, num, i));
        }

        for(int i = 0; i < answer.length; i++) {
            answer[i] = pq.poll().word;
        }
        return answer;
    }
}