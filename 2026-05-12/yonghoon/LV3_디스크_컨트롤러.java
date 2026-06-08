import java.util.*;

class Proc {
    int workTime;
    int inTime;
    int workNum;

    public Proc(int workTime, int inTime, int workNum) {
        this.workTime = workTime;
        this.inTime = inTime;
        this.workNum = workNum;
    }
}

class LV3_디스크_컨트롤러 {
    public int solution(int[][] jobs) {
        // 우선 jobs를 시작 시간 기준 정렬
        Arrays.sort(jobs, (a, b) -> (a[0] - b[0]) );

        // 대기 큐
        Queue<Proc> pq = new PriorityQueue<>(new Comparator<Proc>() {
            @Override
            public int compare(Proc a, Proc b) {
                if(a.workTime == b.workTime && a.inTime == b.inTime)
                    return a.workNum - b.workNum;
                else if(a.workTime == b.workTime)
                    return a.inTime - b.inTime;
                else
                    return a.workTime - b.workTime;
            }
        });

        int elapsedTime = 0; // 총 경과한 시간
        int total = 0; // 평균을 구하기 위한 반환시간 합
        int idx = 0; // 작업 인덱스

        while(idx < jobs.length || !pq.isEmpty()) {

            // 현재 경과한 시간보다 먼저 들어온 작업 넣기
            while(idx < jobs.length && jobs[idx][0] <= elapsedTime) {
                pq.offer(new Proc(jobs[idx][1], jobs[idx][0], idx));
                idx++;
            }

            // 작업이 남아있는데 해당 작업이 들어온 시간이 경과한 시간보다 뒤여서 큐가 비어있는 경우
            // 다음 작업이 들어온 시간으로 경과시간 바꿔야함
            if(pq.isEmpty()) {
                elapsedTime = jobs[idx][0];
                continue;
            }

            Proc proc = pq.poll();

            // 경과시간 계산
            elapsedTime += proc.workTime;

            // 반환시간 계산
            total += (elapsedTime - proc.inTime);
        }

        int answer = total / jobs.length;
        return answer;
    }
}

