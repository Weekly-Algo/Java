class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        int m = key.length;
        int n = lock.length;

        //큰 board 만들기
        int size = n + (m - 1) * 2;

        int[][] board = new int[size][size];

        //lock 시작 위치
        int start = m - 1; 
        
        //lock 복사
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                board[start + i][start + j] = lock[i][j];
            }
        }

        //4번 회전하기
        for(int i = 0; i < 4; i++) {
            //key를 놓을수 있는 모든 위치 탐색
            for(int r = 0; r <= size - m; r++) {
                for(int c = 0; c <= size - m; c++) {
                    //key맞춰보기
                    putKey(board, key, r, c);

                    //맞으면 성공
                    if(check(board, start, n)) {
                        return true;
                    }

                    //다시 원래대로 복구
                    removeKey(board, key, r, c);
                }
            }

            //key를 90도 회전
            key = rotate(key);
        }

        return false;
    }

    //회전하기
    public int[][] rotate(int[][] key) {
        int n = key.length;

        //회전될 결과를 저장할 새로운 배열
        int[][] newKey = new int[n][n];

        //원래 key의 모든 칸 탐색
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                //(i, j)의 값을 회전된 위치에 저장
                newKey[j][n-1-i] = key[i][j];
            }
        }

        return newKey;
    }

    //열쇠 맞추기
    void putKey(int[][] board, int[][] key, int r, int c) {
        int m = key.length;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < m; j++) {
                board[r+i][c+j] += key[i][j];
            }
        }
    }

    //맞춘후 되돌리기
    void removeKey(int[][] board, int[][] key, int r, int c) {
        int m = key.length;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < m; j++) {
                board[r+i][c+j] -= key[i][j];
            }
        }
    }

    //확인하기
    boolean check(int[][] board, int start, int lockSize) {
        //lock영역만 검사
        for(int i = 0; i < lockSize; i++) {
            for(int j = 0; j < lockSize; j++) {
                //하나라도 1이 아니면 실패
                if(board[start+i][start+j] != 1) {
                    return false;
                }
            }
        }

        //전부 1이면 성공
        return true;
    }
}