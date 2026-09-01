import java.util.*;

class Solution {
    public String convert(String s, int numRows) {

        // 한개일때는 그냥 리턴!
        if (numRows == 1) return s;

        StringBuilder[] rows = new StringBuilder[numRows];

        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }

        // 현재 행 위치
        int cur = 0;
        // 이게 내려가는 것인지 올라가는 것인지
        boolean down = false;

          for (char c : s.toCharArray()) {

            rows[cur].append(c);

            // 맨 위 또는 맨 아래에 닿으면 방향을 뒤집어야 함
            if (cur == 0 || cur == numRows - 1) {
                down = !down;
            }

            // 아래로 내려가는 중이면 1 위로 올라가는 중이면 -1
            cur += down ? 1 : -1;
        }

        StringBuilder result = new StringBuilder();

        for (StringBuilder row : rows) {
            result.append(row);
        }
        return result.toString();

    }
}