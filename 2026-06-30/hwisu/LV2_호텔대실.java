import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        // 시작 시간 기준 정렬
        Arrays.sort(book_time, (a, b) -> a[0].compareTo(b[0]));

        // 각 방이 사용 가능해지는 시각(분 단위)을 저장하는 최소 힙
        PriorityQueue<Integer> rooms = new PriorityQueue<>();

        for (String[] booking : book_time) {
            int start = toMinutes(booking[0]);
            int end = toMinutes(booking[1]);

            // 가장 빨리 비는 방이 지금 예약 시작 전에 비는지 확인
            if (!rooms.isEmpty() && rooms.peek() <= start) {
                rooms.poll(); // 그 방 재사용 (꺼내고 새 시간으로 다시 넣기)
            }

            // 퇴실 + 청소 10분 후 사용 가능
            rooms.offer(end + 10);
        }

        return rooms.size();
    }

    private int toMinutes(String time) {
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }
}