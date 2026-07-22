class Solution {
    static int answer;
    static String word;
    static int cnt;
    static String[] alphabet = new String[]{"A", "E", "I", "O", "U"};

    public int solution(String word) { // DFS로 풀이 -> 트리모양과 유사해서…
        answer = 0;
        Solution.word = word;
        cnt = 0;
        dfs("");

        return answer;
    }

    static void dfs(String str) {

        if(str.length() > 5) return;
        if(!str.equals("")) cnt++; // 빈문자열은 세지 않는다..
        if(str.equals(word)){
            answer = cnt;
            return;
        }

        for(String tmp : alphabet) {
            dfs(str+tmp); // 반복문 돌면서 문자 하나씩 추가해준다!!
        }
    }
}