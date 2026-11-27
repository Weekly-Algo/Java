class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;

        char[] skillArr = skill.toCharArray();

        for(String skillSet : skill_trees) {
            int index = 0;
            boolean isValid = true;

            for(int i = 0; i < skillSet.length(); i++) {
                char current = skillSet.charAt(i);
                if(index < skillArr.length && current == skillArr[index]) {
                    index++;
                }

                else if(skill.indexOf(current) != -1) { //현재 글자가 필수 스킬에 존재한다면
                    isValid = false; //순서가 맞지 않으므로 빠져나간다!
                    break;
                }
            }

            if(isValid) {
                answer++;
            }

        }

        return answer;
    }
}