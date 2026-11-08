
class Solution {
    public int[] solution(int brown, int yellow) {

        for (int i = 1; i * i <= yellow; i++) {
            if (yellow % i == 0) {
                int x = yellow / i;
                int y = i;

                if ((x + y + 2) * 2 == brown) {
                    return new int[]{x + 2, y + 2};
                }
            }
        }

        return new int[]{};
    }
}
