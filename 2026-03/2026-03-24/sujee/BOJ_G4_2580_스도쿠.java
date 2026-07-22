import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_G4_2580_스도쿠 {
    static int[][] sudoku = new int[9][9];
    static StringBuilder ans;
    static List<int[]> emptyPlace; // 빈 곳 (0인 곳) 좌표를 저장할 리스트
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        emptyPlace = new ArrayList<>();
        for(int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0;  j < 9; j++) {
                int x = Integer.parseInt(st.nextToken());
                sudoku[i][j] = x;
                if(x == 0) emptyPlace.add(new int[] {i, j}); // 답을 찾아야 할 좌표를 미리 체크해둔다!
            }
        } // 기본 스도쿠 판 입력 완료

        solve(sudoku, 0); // 스도쿠 풀이

        // 출력!
        ans = new StringBuilder();
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                ans.append(sudoku[i][j]);
                ans.append(" ");
            }
            ans.append("\n");
        }
         System.out.println(ans);
    }

    static boolean solve(int[][] sudoku1, int idx) {
        if(idx == emptyPlace.size()) {
            for (int i = 0; i < 9; i++) {
                sudoku[i] = sudoku1[i].clone();
            }
            return true;
        }

        int[] tmp = emptyPlace.get(idx);
        int c1 = tmp[0]; int c2 = tmp[1];

        for(int j = 1; j <= 9; j++) {
            if(check(sudoku1, c1, c2, j)) {
                sudoku1[c1][c2] = j;
                if(solve(sudoku1, idx+1)) return true;
                sudoku1[c1][c2] = 0;
            }
        }
        return false;

    }

    static boolean check(int[][] sudoku1, int x, int y, int k){
        for(int i = 0; i < 9; i++) {
            if(sudoku1[x][i] == k) return false;
            if(sudoku1[i][y] == k) return false;
        }

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(sudoku1[x/3*3+i][(y/3)*3+j] == k) return false;
            }
        }

        return true;
    }
}
