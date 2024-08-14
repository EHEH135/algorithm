

# 처음 코드
```java
import java.util.Arrays;

class Solution {

    static int[][] move = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};//right, down, left, up
    static int[][] currentBoard;

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
        int answer = 0;
        currentBoard = board.clone();
        for (int i = 0; i < board.length; i++) {
            currentBoard[i] = board[i].clone();
        }

        answer = gameRun(aloc, bloc, board);

        return answer;
    }

    //이동할 곳이 이동 가능한지 확인
    public boolean checker(int[] loc, int[] direction){
        int x = loc[0] + direction[0];
        int y = loc[1] + direction[1];

        int boardX = currentBoard.length;
        int boardY = currentBoard[0].length;

        if((x < 0 || x >= boardX) || (y < 0 || y >= boardY)){
            return false;
        }
        else{
            if(currentBoard[x][y] != 1){
                return false;
            }
            return true;
        }
    }

    //이동할 지점의 점수를 카운팅 한다.
    public int neighborBlockCount(int[] loc){
        int cnt = 0;
        for(int i = 0; i < move.length; i++){
            if(checker(loc, move[i])){
                cnt++;
            }
        }
        return cnt;
    }

    public int gameRun(int[] aloc, int[] bloc, int[][] board){
        int turn = 0;
        int blockCnt = -1;
        int blockIndex = -1;
        while(true){
            //a 차례
            if(turn % 2 == 0){
                currentBoard[aloc[0]][aloc[1]] = 0;

                for(int i = 0; i < move.length; i++){
                    if(checker(aloc, move[i])){
                        int[] tmp = {aloc[0]+move[i][0], aloc[1]+move[i][1]};
                        int cnt = 0;

                        cnt = neighborBlockCount(tmp);
                        //이동할 곳에 상대가 있을 때
                        if((bloc[0] == tmp[0]) && (bloc[1] == tmp[1])){
                            cnt = -1;
                        }
                        if(blockCnt <= cnt){
                            blockCnt = cnt;
                            blockIndex = i;
                        }
                    }
                }
                if(blockCnt == -1 && blockIndex == -1){
                    break;
                }
                board[aloc[0]][aloc[1]] = 0;
                aloc[0] = aloc[0] + move[blockIndex][0];
                aloc[1] = aloc[1] + move[blockIndex][1];
                
                // System.out.printf("aloc : %d, %d\n", aloc[0], aloc[1]);
                
                if(board[bloc[0]][bloc[1]] == 0){
                    turn++;
                    break;
                }
                
                blockCnt = -1;
                blockIndex = -1;
            }
            else{
                currentBoard[bloc[0]][bloc[1]] = 0;

                for(int i = 0; i < move.length; i++){
                    if(checker(bloc, move[i])){
                        int[] tmp = {bloc[0]+move[i][0], bloc[1]+move[i][1]};
                        int cnt = 0;

                        cnt = neighborBlockCount(tmp);
                        //이동할 곳에 상대가 있을 때
                        if((aloc[0] == tmp[0]) && (aloc[1] == tmp[1])){
                            cnt = -1;
                        }
                        if(blockCnt <= cnt){
                            blockCnt = cnt;
                            blockIndex = i;
                        }
                    }
                }
                if(blockCnt == -1 && blockIndex == -1){
                    break;
                }
                board[bloc[0]][bloc[1]] = 0;
                bloc[0] = bloc[0] + move[blockIndex][0];
                bloc[1] = bloc[1] + move[blockIndex][1];
                
                // System.out.printf("bloc : %d, %d\n", bloc[0], bloc[1]);
                
                if(board[aloc[0]][aloc[1]] == 0){
                    turn++;
                    break;
                }
                
                blockCnt = -1;
                blockIndex = -1;
            }
            turn++;
        }

        return turn;
    }
}
```