

import java.util.*;

class Solution {

	
	public static int solution(String s) {
		Stack<Character> st = new Stack<>();
		char[] arr = s.toCharArray();
		int ans = 0; // 검사해서 문제없을 시 카운트할것
		int N = arr.length;
		char[] arr2 = new char[N]; //회전한 값을 넣을 새 배열
		boolean isOk = true;

		for (int i = 0; i < N; i++) { // char배열길이만큼 반복

			for (int k = 0; k < N; k++) {
				arr2[k] = arr[(i + k) % N];
			} // 괄호 회전하기

			outer: for (int j = 0; j < N; j++) {
				switch (arr2[j]) {
				case '(', '{', '[':
					st.push(arr2[j]);
					break; // 스택에 넣어주기
				case ')':
					if (st.isEmpty() || st.pop() != '(') {
						isOk = false; // 해당 닫힌 괄호 아닐경우 false만들고
						break outer;// for문 나오기
					}
					break;

				case '}':
					if (st.isEmpty() || st.pop() != '{') {
						isOk = false;
						break outer;
					}
					break;

				case ']':
					if (st.isEmpty() || st.pop() != '[') {
						isOk = false;
						break outer;
					}
					break;
				}
			}

			if (isOk && st.isEmpty()) { //true 일때만 카운트
				ans++;
			}

			st.clear();
			isOk = true;

		}
		return ans;

	}

}
