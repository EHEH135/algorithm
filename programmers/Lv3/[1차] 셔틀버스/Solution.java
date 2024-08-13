import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Collections;
import java.util.List;

class Solution {
    public static void main(String[] args){
        Solution my = new Solution();
        // TCs
		// int n = 1;		//TC #1
		// int t = 1;		//TC #1
		// int m = 5;		//TC #1
		// String[] timetable = {"08:00", "08:01", "08:02", "08:03"};		//TC #1
		// int n = 2;		//TC #2
		// int t = 10;		//TC #2
		// int m = 2;		//TC #2
		// String[] timetable = {"09:10", "09:09", "08:00"};		//TC #2
		// int n = 2;		//TC #3
		// int t = 1;		//TC #3
		// int m = 2;		//TC #3
		// String[] timetable = {"09:00", "09:00", "09:00", "09:00"};		//TC #3
		// int n = 1;		//TC #4
		// int t = 1;		//TC #4
		// int m = 5;		//TC #4
		// String[] timetable = {"00:01", "00:01", "00:01", "00:01", "00:01"};		//TC #4
		// int n = 1;		//TC #5
		// int t = 1;		//TC #5
		// int m = 1;		//TC #5
		// String[] timetable = {"23:59"};		//TC #5
		int n = 10;		//TC #6
		int t = 60;		//TC #6
		int m = 45;		//TC #6
		String[] timetable = {"23:59","23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59"};		//TC #6


        // Solution output
		String output = my.solution(n, t, m, timetable);
		System.out.println(output);
    }
    public String solution(int n, int t, int m, String[] timetable) {
        String answer;
        Queue<Integer> crewTable = new LinkedList<>();
        Queue<Integer> busTable = new LinkedList<>();

        for (int i = 0; i < timetable.length; i++){
            crewTable.add(timeParser(timetable[i]));
        }
        busTable = createBusTable(n, t);

        List<Integer> sortedCrewList = new LinkedList<>(crewTable);
        Collections.sort(sortedCrewList);
        crewTable.clear();
        crewTable.addAll(sortedCrewList);

        List<Integer> sortedBusList = new LinkedList<>(busTable);
        Collections.sort(sortedBusList);
        busTable.clear();
        busTable.addAll(sortedBusList);

        System.out.println(crewTable);
        System.out.println(busTable);

        answer = latestTimeCheck(busTable, crewTable, m);
        
        return answer;
    }
    public String latestTimeCheck(Queue<Integer> busTable, Queue<Integer> crewTable, int m) {
        int latestTime = 0;

        while (!busTable.isEmpty()) {
            int currentBus = busTable.poll();
            int seatsAvailable = m;

            while (seatsAvailable > 0 && !crewTable.isEmpty() && crewTable.peek() <= currentBus) {
                latestTime = crewTable.poll();
                seatsAvailable--;
            }

            if (busTable.isEmpty() && seatsAvailable > 0) {
                latestTime = currentBus;
            }
            else if (busTable.isEmpty() && seatsAvailable == 0) {
                latestTime--;
            }
        }

        return timeParser2(latestTime);
    }

    public Queue<Integer> createBusTable(int n, int t){
        Queue<Integer> busTable = new LinkedList<>();
        int startTime = timeParser("9:00");
        busTable.add(startTime);

        for (int i = 1; i < n; i++){
            int time = startTime + (i * t);
            busTable.add(time);
        }

        return busTable;
    }
    public int timeParser(String time){
        String[] timeS = time.split(":");
        int timeM = (Integer.parseInt(timeS[0]) * 60) + Integer.parseInt(timeS[1]);
        return timeM;
    }
    public String timeParser2(int time){
        int hour = (int)(time / 60);
        int minute = (int)(time % 60);
        String hourS = "";
        String minuteS = "";
        
        if (hour < 10){
            hourS = "0" + Integer.toString(hour);
        }
        else{
            hourS = Integer.toString(hour);
        }
        
        if (minute < 10){
            minuteS = "0" + Integer.toString(minute);
        }
        else{
            minuteS = Integer.toString(minute);
        }

        String timeS = hourS + ":" + minuteS;
        
        return timeS;
    }
}