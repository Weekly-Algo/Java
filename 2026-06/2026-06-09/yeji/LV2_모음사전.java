class Solution {

    int count = 0;   // 사전 순서
    int answer = 0;  // 찾은 단어의 위치

    String[] arr = {"A", "E", "I", "O", "U"};

    public int solution(String word) {

        dfs("", word);

        return answer;
    }

    public void dfs(String temp, String word) {

        // 단어 길이는 최대 5글자
        if (temp.length() > 5) {
            return;
        }

        // 빈 문자열을 제외한 모든 단어는 사전 순서에 포함
        if (!temp.equals("")) {
            count++;
        }

        // 현재 단어가 찾는 단어라면 위치 저장
        if (temp.equals(word)) {
            answer = count;
            return;
        }

        // 현재 단어 뒤에 모음을 하나씩 붙여 다음 단어 생성
        for (int i = 0; i < arr.length; i++) {
            dfs(temp + arr[i], word);
        }
    }
}