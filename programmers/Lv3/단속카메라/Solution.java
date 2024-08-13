import java.util.Arrays;

class Solution {
    public static void main(String[] args){
        Solution my = new Solution();
        // TCs
		int[][] routes = {{-20,-15}, {-14,-5}, {-18,-13}, {-5,-3}};		//TC #1


        // Solution output
		int output = my.solution(routes);
		System.out.println(output);
    }
    public int solution(int[][] routes) {
        int answer = 0;
        int camera = -30001;

        Arrays.sort(routes, (o1, o2) -> {
            return o1[1]-o2[1];
        });

        for(int i = 0; i < routes.length; i++){
            if(camera < routes[i][0]){
                answer += 1;
                camera = routes[i][1];
            }
        }

        return answer;
    }
}