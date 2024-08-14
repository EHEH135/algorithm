import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {

     // 좌표를 나타내는 클래스
    class Point{
        int x,y;
        
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    
    // 상하좌우
    int dx[] = {0,0,-1,1};
    int dy[] = {-1,1,0,0};
    
    int[][] board; // board 전역변수화
    int m,n; // n : 세로 길이, m: 가로 길이

    public static void main(String[] args){
        Solution my = new Solution();
        // TCs
		// int[][] board = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};		//TC #1
		// int[] aloc = {1, 0};		//TC #1
		// int[] bloc = {1, 2};		//TC #1
		int[][] board = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};		//TC #2
		int[] aloc = {1, 0};		//TC #2
		int[] bloc = {1, 2};		//TC #2
		// int[][] board = {{1, 1, 1, 1, 1}};		//TC #3
		// int[] aloc = {0, 0};		//TC #3
		// int[] bloc = {0, 4};		//TC #3
		// int[][] board = {{1}};		//TC #4
		// int[] aloc = {0, 0};		//TC #4
		// int[] bloc = {0, 0};		//TC #4


        // Solution output
		int output = my.solution(board, aloc, bloc);
		System.out.println(output);
    }

    public int solution(int[][] board, int[] aloc, int[] bloc) {
        int answer = -1;
        this.board = board;
        n = board.length;
        m = board[0].length;
        
        
        Point cntA = new Point(aloc[0],aloc[1]);
        Point cntB = new Point(bloc[0],bloc[1]);
        
        answer = dfs(cntA,cntB);
        return answer;
    }
    
    
	// me : 현재 턴인 사람 // you : 상대방
    public int dfs(Point me, Point you){
        if(board[me.x][me.y] == 0) return 0;  // 발판이 사라졌다면 0 반환
        
        int x = me.x;
        int y = me.y;
        int result = 0; // 최종적인 턴 수
        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if(nx < 0 || ny < 0 || nx >= n|| ny >= m || board[nx][ny] == 0) continue;
            board[x][y] = 0; // 이전에 서있던 곳으로 이동불가능하게 만듦
            
             // 여기서 상대방 턴이기 때문에 매개변수로 dfs(you,me) 순서로 들어간다.
            int val = dfs(you, new Point(nx,ny)) + 1; // 턴수 + 1
            board[x][y] = 1; // 사용한 것을 원상 복구
            
            // 지금까지 모두 진 경우고, 이번에 이겼을 때 -> 바로 이긴걸로 바꿔줌
            if(val % 2 == 1 && result % 2 == 0) result = val;
            // 지금까지도 졌고, 이 경우도 진 경우 -> 최대한 많이 움직인다.
            else if(val % 2 == 0 && result % 2 == 0 ) result = Math.max(result,val);
            // 지금까지도 이겼고, 이 경우도 이긴 경우 -> 최대한 적게 움직인다.
            else if(val % 2 == 1 && result % 2 == 1 ) result = Math.min(result,val);
        }
        return result;
    }
}
