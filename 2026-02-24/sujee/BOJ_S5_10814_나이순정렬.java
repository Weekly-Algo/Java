import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_S5_10814_나이순정렬 {
    static class Person{
        int age;
        String name;
        int order; //입력순서...

        Person(int age, String name, int order){
            this.age = age;
            this.name = name;
            this.order = order;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Person> pq = new PriorityQueue<>(
                new Comparator<Person>() {
                    @Override
                    public int compare(Person o1, Person o2) {
                        if(o1.age == o2.age){
                            return o1.order - o2.order;
                        }
                        return o1.age - o2.age;
                    }
                }
        );

        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            pq.add(new Person(age, name, i));
        }

        for(int i = 0; i < N; i++){
            Person p = pq.poll();
            System.out.println(p.age + " " + p.name);
        }
    }
}
