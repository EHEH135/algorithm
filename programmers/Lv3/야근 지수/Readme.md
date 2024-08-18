
# 처음 제출 코드

실패이유 : 효율성

```java

class Solution {
    public static void main(String[] args){
        Solution my = new Solution();
        // TCs
		// int[] works = {4, 3, 3};		//TC #1
		// int n = 4;		//TC #1
		// int[] works = {2, 1, 2};		//TC #2
		// int n = 1;		//TC #2
		int[] works = {1,1};		//TC #3
		int n = 3;		//TC #3


        // Solution output
		int output = my.solution(works, n);
		System.out.println(output);
    }
    public int solution(int[] works, int n) {
        int answer = 0;

        if(isZero(works, n)){
            answer = 0;
        }
        else{
            while(n > 0){
                int maxIndex = findMaxIndex(works);

                works[maxIndex] -= 1;
                n -= 1;
            }
            for(int work: works){
                answer += work*work;
            }
        }

        return answer;
    }
    static int findMaxIndex(int[] works){
        int maxIndex = 0;
        for(int i = 1; i < works.length; i++){
            if(works[maxIndex] < works[i]){
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    static boolean isZero(int[] works, int n){
        int sum = 0;
        for(int work: works){
            sum += work;
        }
        if(sum <= n){
            return true;
        }
        return false;
    }
}
```

# 최종 코드

정렬을 통해 항상 마지막 인덱스에는 가장 큰 값이 온다는 점을 이용하여 실행 회수 줄임.

```java
import java.util.*;

class Solution {
    public static void main(String[] args){
        Solution my = new Solution();
        // TCs
		int[] works = {4, 3, 3};		//TC #1
		int n = 4;		//TC #1
		// int[] works = {2, 1, 2};		//TC #2
		// int n = 1;		//TC #2
		// int[] works = {1,1};		//TC #3
		// int n = 3;		//TC #3


        // Solution output
		int output = my.solution(works, n);
		System.out.println(output);
    }
    public int solution(int[] works, int n) {
        int answer = 0;
        Arrays.sort(works);

        if(isZero(works, n)){
            answer = 0;
        }
        else{
            while(n > 0){
                int maxValue = works[works.length-1];

                for(int i = works.length-1; i >= 0; i--){
                    if(works[i] >= maxValue){
                        works[i]--;
                        n--;
                    }

                    if(n == 0) break;
                }
            }
            for(int work: works){
                answer += work*work;
            }
        }

        return answer;
    }
    static boolean isZero(int[] works, int n){
        int sum = 0;
        for(int work: works){
            sum += work;
        }
        if(sum <= n){
            return true;
        }
        return false;
    }
}
```