import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B1_1259_팰린드롬수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = "";
        while(true){
            str = br.readLine();
            if(str.equals("0")) break;
            for(int i = 0; i < str.length(); i++){
                if(str.charAt(i) != str.charAt(str.length()-i-1)){
                    System.out.println("no");
                    break;
                }
                if(i == str.length()-1)
                    System.out.println("yes");
            }
        }
    }
}
