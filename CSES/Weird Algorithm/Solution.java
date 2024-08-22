import java.util.*;

class Solution {
    public static void main(String[] args){        
        Scanner sc = new Scanner(System.in); // Scanner 객체 생성
        int n = sc.nextInt();
        sc.close();  // Scanner 객체 닫기 (자원 해제)

        // Solution output
        
        while(n != 1){
            System.out.print(n + " ");
            if(n % 2 == 0){
                n /= 2;
            } else {
                n = (n * 3) + 1;
            }
        }
        System.out.print(1);
    }
}
