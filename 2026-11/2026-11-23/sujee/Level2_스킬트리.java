class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;

        for(String tree : skill_trees) {
            String str = ""; // 선행 순서와 관련된 스킬만 모음
            for(int i = 0; i < tree.length(); i++) {
                char c = tree.charAt(i);
                if(skill.contains(c + "")) str += c;
            }

            int j;
            for(j = 0; j < str.length(); j++) {
                if(str.charAt(j) != skill.charAt(j)) break;
            }
            if(j == str.length()) answer++;
        }

        return answer;
    }
}