
class Solution {
    public static void main(String[] args){
        Solution my = new Solution();
        // TCs
		int r1 = 2;		//TC #1
		int r2 = 3;		//TC #1


        // Solution output
		int output = my.solution(r1, r2);
		System.out.println(output);
    }
    public int solution(int r1, int r2) {
        long answer = 0;

        for (int i=1; i<=r2; i++) {
            long minJ = (int) Math.ceil(Math.sqrt(1.0*r1*r1 - 1.0*i*i));
            long maxJ = (int) Math.floor(Math.sqrt(1.0*r2*r2 - 1.0*i*i));

            answer += (maxJ - minJ + 1);

        }

        return answer * 4;
    }

    static int pointCount(int r){
        return r*r*4 - 4*(r-1) - 3;
    }
}