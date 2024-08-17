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