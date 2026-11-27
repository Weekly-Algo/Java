import java.util.*;

class 스킬트리 {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;

        for(String skillTree : skill_trees) {
            // 필요 스킬
            int require = 0;

            // 가능한 경우인지
            boolean isOk = true;

            for(int i = 0; i < skillTree.length(); i++) {
                char nowSkill = skillTree.charAt(i);

                // 현재 스킬이 익혀야할 스킬과 동일하다면 익히고 넘어감
                if(require < skill.length() && skill.charAt(require) == nowSkill)
                    require++;
                else {
                    // 이후 순서에 있는 스킬이 들어있다면 배울 수 없음
                    for(int j = require; j < skill.length(); j++) {
                        if(nowSkill == skill.charAt(j)) {
                            isOk = false;
                            break;
                        }
                    }

                    if(!isOk)
                        break;
                }
            }

            if(isOk)
                answer++;
        }
        return answer;
    }
}