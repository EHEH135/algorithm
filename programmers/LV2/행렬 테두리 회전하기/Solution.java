import java.util.*;
class Solution {
    public static void main(String[] args){
        Solution my = new Solution();
        // TCs
		// int rows = 6;		//TC #1
		// int columns = 6;		//TC #1
		// int[][] queries = {{2,2,5,4},{3,3,6,6},{5,1,6,3}};		//TC #1
		// int rows = 3;		//TC #2
		// int columns = 3;		//TC #2
		// int[][] queries = {{1,1,2,2},{1,2,2,3},{2,1,3,2},{2,2,3,3}};		//TC #2
		int rows = 100;		//TC #3
		int columns = 97;		//TC #3
		int[][] queries = {{1,1,100,97}};		//TC #3


        // Solution output
		int[] output = my.solution(rows, columns, queries);
		System.out.print("[");
        for( int i = 0; i < output.length; i++){
            System.out.print(output[i]);
            if ( i == (output.length-1)) break;
            else System.out.print(", ");
        }
        System.out.println("]");
    }
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 시계방향
        int[][] table = new int[rows][columns];

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                table[i][j] = (j+1)+(i*columns);
            }
        }

        for(int i = 0; i < queries.length; i++){
            int[] p1 = {queries[i][0]-1, queries[i][1]-1};
            int[] p2 = {queries[i][2]-1, queries[i][3]-1};
            int[] current = {queries[i][0]-1, queries[i][1]-1};
            int flag = 0;
            int minValue = table[current[0]][current[1]];
            int tmp = table[current[0]][current[1]];

            while(flag < 4){
                if(flag == 0 && current[1] < p2[1]){
                    current[0] += direction[flag][0];
                    current[1] += direction[flag][1];
                    int nextTmp = table[current[0]][current[1]];
                    if(minValue > nextTmp){
                        minValue = nextTmp;
                    }
                    table[current[0]][current[1]] = tmp;
                    tmp = nextTmp;
                    if(current[1] == p2[1]){
                        flag++;
                    }
                }
                else if(flag == 1 && current[1] == p2[1] && current[0] < p2[0]){
                    current[0] += direction[flag][0];
                    current[1] += direction[flag][1];
                    int nextTmp = table[current[0]][current[1]];
                    table[current[0]][current[1]] = tmp;
                    tmp = nextTmp;
                    if(current[0] == p2[0]){
                        flag++;
                    }
                }
                else if(flag == 2 && current[1] > p1[1]){
                    current[0] += direction[flag][0];
                    current[1] += direction[flag][1];
                    int nextTmp = table[current[0]][current[1]];
                    table[current[0]][current[1]] = tmp;
                    tmp = nextTmp;
                    if(current[1] == p1[1]){
                        flag++;
                    }
                }
                else if(flag == 3 && current[1] == p1[1] && current[0] > p1[0]){
                    current[0] += direction[flag][0];
                    current[1] += direction[flag][1];
                    int nextTmp = table[current[0]][current[1]];
                    table[current[0]][current[1]] = tmp;
                    tmp = nextTmp;
                    if(current[0] == p1[0]){
                        flag++;
                    }
                }
            }

            answer[i] = minValue;
        }

        return answer;
    }
}