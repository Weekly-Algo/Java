import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S2_2630_색종이만들기 {
	
	static int blue = 0;
	static int white = 0;
	static int[][] board;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 분할정복
		partition(0, 0, N);
		
		System.out.println(white);
		System.out.println(blue);
		
	} // main
	
	static void partition(int row, int col, int size) {
		if (check(row, col, size)) {
			if (board[row][col] == 0) {
				white++;
			}
			else {
				blue++;
			}
			// 같은 색상이면 종료
			return;
		}
		// 색상이 다 같이 않으면 절반으로 분할(가로세로 반반 -> 4개로 분할)
		int newSize = size / 2;
		
		// 분할된 4개 영역에 재귀
		partition(row, col, newSize);
		partition(row, col + newSize, newSize);
		partition(row + newSize, col, newSize);       
        partition(row + newSize, col + newSize, newSize);
        
	} // partition
	
	static boolean check(int row, int col, int size) {
        int color = board[row][col];
        
        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                // 하나라도 다르면 false 반환
                if (board[i][j] != color) {
                    return false;
                }
            }
        }
        // 모두 같은 색상이면 true 반환ㄱ
        return true;
        
    } // check
	
}