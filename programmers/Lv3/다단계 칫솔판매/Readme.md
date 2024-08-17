# 접근 방법
자식은 하나의 부모를 갖는다. 라는 점을 이용해서 HashMap을 통해 그래프를 만들어 주는 것이 핵심.

# 처음 실패 코드

```java
import java.util.HashMap;

class Solution {
    public static void main(String[] args){
        Solution my = new Solution();
        // TCs
		// String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};		//TC #1
		// String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};		//TC #1
		// String[] seller = {"young", "john", "tod", "emily", "mary"};		//TC #1
		// int[] amount = {12, 4, 2, 5, 10};		//TC #1
		String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};		//TC #2
		String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};		//TC #2
		String[] seller = {"sam", "emily", "jaimie", "edward"};		//TC #2
		int[] amount = {2, 3, 5, 4};		//TC #2


        // Solution output
		int[] output = my.solution(enroll, referral, seller, amount);
		System.out.print("[");
        for( int i = 0; i < output.length; i++){
            System.out.print(output[i]);
            if ( i == (output.length-1)) break;
            else System.out.print(", ");
        }
        System.out.println("]");
    }
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
        HashMap<String, Integer> earn = new HashMap<String, Integer>();
        HashMap<String, Integer> childCnt = new HashMap<String, Integer>();
        boolean[] visited = new boolean[enroll.length];
        earn.put("-", 0);

        //사원 전체 
        for(int i = 0; i < enroll.length; i++){
            earn.put(enroll[i], 0);
            visited[i] = false;
            if(!childCnt.containsKey(referral[i])){
                childCnt.put(referral[i], 1);
            }
            else{
                int tmp = childCnt.get(referral[i]);
                childCnt.put(referral[i], tmp + 1);
            }
        }
        //벌어온 금액
        for(int i = 0; i < seller.length; i++){
            if(earn.containsKey(seller[i])){
                earn.put(seller[i], amount[i]*100);
            }
        }

        int index = 0;
        while(!childCnt.isEmpty()){
            int i = index % enroll.length;
            if(!childCnt.containsKey(enroll[i]) && !visited[i]){
                visited[i] = true;
                int parent = earn.get(referral[i]);
                int child = earn.get(enroll[i]);
                int giving = (int)(child / 10);

                parent += giving;
                child -= giving;

                earn.put(enroll[i], child);
                earn.put(referral[i], parent);

                if(childCnt.containsKey(referral[i])){
                    int childCntTmp = childCnt.get(referral[i]);
                    if(childCntTmp == 1){
                        childCnt.remove(referral[i]);
                    }
                    else{
                        childCnt.put(referral[i], childCntTmp - 1);
                    }
                }
            }
            index++;
        }

        for(int i = 0; i < enroll.length; i++){
            answer[i] = earn.get(enroll[i]);
        }

        return answer;
    }
}
```

# 통과 코드
```java
import java.util.HashMap;

class Solution {
    public static void main(String[] args){
        Solution my = new Solution();
        // TCs
		// String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};		//TC #1
		// String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};		//TC #1
		// String[] seller = {"young", "john", "tod", "emily", "mary"};		//TC #1
		// int[] amount = {12, 4, 2, 5, 10};		//TC #1
		String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};		//TC #2
		String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};		//TC #2
		String[] seller = {"sam", "emily", "jaimie", "edward"};		//TC #2
		int[] amount = {2, 3, 5, 4};		//TC #2


        // Solution output
		int[] output = my.solution(enroll, referral, seller, amount);
		System.out.print("[");
        for( int i = 0; i < output.length; i++){
            System.out.print(output[i]);
            if ( i == (output.length-1)) break;
            else System.out.print(", ");
        }
        System.out.println("]");
    }
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];

        // 다시 풀어보기 순서
        HashMap<String, String> parentMap = new HashMap<>(); // <자신, 부모>
        HashMap<String, Integer> myIndexMap = new HashMap<>(); // <자신, 인덱스>

        for(int i = 0; i < enroll.length; i++){
            parentMap.put(enroll[i], referral[i]);
            myIndexMap.put(enroll[i], i);
            answer[i] = 0;
        }

        for(int i = 0; i < seller.length; i++){
            String current = seller[i];
            String parent = parentMap.get(current);
            int currentEarn = amount[i] * 100;

            while(true){
                int currentMoney = (int)(currentEarn / 10);

                int myIndex = myIndexMap.get(current);
                answer[myIndex] += currentEarn - currentMoney;

                if(parent.equals("-")){
                    break;
                }

                currentEarn = currentMoney;
                current = parent;
                parent = parentMap.get(current);
                if(currentMoney < 1){
                    break;
                }
            }
            

        }

        return answer;
    }
}
```