import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S4_10828_스택 {
    static int[] arr;
    static int top;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new int[N];
        top = -1;

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String order = st.nextToken();
            switch (order) {
                case "push" :
                    int num = Integer.parseInt(st.nextToken());
                    push(num);
                    break;
                case "pop" :
                    System.out.println(pop());
                    break;
                case "size" :
                    System.out.println(size());
                    break;
                case "empty" :
                    System.out.println(empty());
                    break;
                case "top" :
                    System.out.println(top());
                    break;
            }
        }
    }

    public static void push(int num) {
        arr[++top] = num;
    }

    public static int pop() {
        if(top == -1) return -1;
        return arr[top--];
    }

    public static int size() {
        return top + 1;
    }

    public static int empty() {
        if(top == -1) {
            return 1;
        } else {
            return 0;
        }
    }

    public static int top() {
        if(top == -1) return -1;
        return arr[top];
    }
}
