import java.lang.Math;
import java.util.*;

class Solution {
    
    //DP
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        int answer = 1000*100;
        int flag = -1;
        temperature += 10;
        t1 += 10;
        t2 += 10;
        
        int[][] dp = new int[onboard.length][51];
        for(int i = 0; i < onboard.length; i++){
            for(int j = 0; j < 51; j++){
                dp[i][j] = 1000*100;    
            }
        }
        
        dp[0][temperature] = 0;
        
        if(temperature < t1){
            flag = 1;
        }
        
        for(int i = 1; i < onboard.length; i++){
            for(int j = 0; j < 51; j++){
                int minV = 1000*100;
                if (( onboard[i] == 1 && t1 <= j && j <= t2 ) || onboard[i] == 0) {
        
                    if (0 <= j+flag && j+flag <= 50) {
                        minV = min(minV, dp[i-1][j+flag]);
                    }
                    
                    if (j == temperature) {
                        minV = min(minV, dp[i-1][j]);
                    }

                    if (0 <= j-flag && j-flag <= 50) {
                        minV = min(minV, dp[i-1][j-flag] + a);
                    }
                    
                    if (t1 <= j && j <= t2) {
                        minV = min(minV, dp[i-1][j] + b);
                    }
                    
                    dp[i][j] = minV;
                    // System.out.println(i + "," + j + ":" + minV);
                }
            }
        }
        
        for(int i = 0; i < 51; i++){
            answer = min(dp[onboard.length-1][i], answer);
        }
        
        return answer;
    }
    
    public int min(int a, int b) {
        if (a < b) {
            return a;
        } else {
            return b;
        }
    }
}