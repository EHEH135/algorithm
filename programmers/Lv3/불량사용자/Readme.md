# 문제 설명

![image](https://github.com/user-attachments/assets/b3b37d2d-7049-4341-83c0-501071efdbb2)

![image](https://github.com/user-attachments/assets/ed471cce-daca-49c9-98f9-babec3bf59b5)


# 접근방법

‘응모자 아이디’보다 항상 ‘불량 사용자’ 수가 적기 때문에 불량 사용자 기반으로 응모자 아이디 전체 탐색을 하면 된다고 생각했습니다.

전체 탐색시 중복 경우가 나올 수 있으므로 순서는 지켜지지 않지만 중복제거를 해주는 HashSet을 사용했습니다.

먼저 ‘불량 사용자’와 ‘응모자 아이디’가 맞는지 확인하기위해 match라는 boolean타입의 함수를 만들어 주어 확인을 진행할 수 있도록 하였고

![image](https://github.com/user-attachments/assets/9c37ace9-83aa-467e-9ac5-10b817c71685)


전체 탐색을 위해 DFS와 BackTracking을 사용했습니다. set에 ID가 없으며 match를 통과 했으면 set에 추가를 하고 재귀를 시작합니다. 이후 재귀가 끝나면 set에 ID가 남지 않도록 remove를 해줍니다.
remove를 하는이유는 아래와 같습니다.

- 첫 번째 `banned_id`에 대해 `"frodo"`를 선택하고, `set`에 `"frodo"`를 추가합니다.
- `dfs(new HashSet<>(set), depth + 1)`가 호출되며, 새로운 `HashSet`에 `"frodo"`가 포함된 상태로 재귀 호출이 시작됩니다.
- 재귀 호출이 끝나고 돌아오면, `set`에는 여전히 `"frodo"`가 남아 있습니다.
- 이때 `remove("frodo")`를 하지 않으면, 다음 루프에서 `"frodo"`가 이미 `set`에 존재하므로, `"frodo"`는 다른 `banned_id`에 매칭될 수 없게 됩니다.

마지막으로 depth와 전체 사용자 수가 같다면 set에 있는 값을 result에 저장을 하고 재귀를 멈춥니다.

![image](https://github.com/user-attachments/assets/0aa9b793-d975-4548-93aa-2500a2b2638f)


# 프로그래머스 제출 코드

```java
import java.util.*;

class Solution {

    private String[] userId;
    private String[] bannedId;
    private HashSet<HashSet<String>> result = new HashSet<>();
    
    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        
        userId = user_id;
        bannedId = banned_id;

        dfs(new HashSet<>(), 0);

        answer = result.size();
        return answer;
    }

    public void dfs(HashSet<String> set, int depth){
        if(depth == bannedId.length){
            result.add(set);
            return;
        }
        
        for(int i = 0; i < userId.length; i++){
            if(set.contains(userId[i])){
                continue;
            }

            if(match(userId[i], bannedId[depth])){
                set.add(userId[i]);
                dfs(new HashSet<>(set), depth + 1);
                set.remove(userId[i]);
            }
        }
    }
    
    public boolean match(String user, String banned){
        if(user.length() != banned.length()){
            return false;
        }

        boolean flag = true;

        for(int i = 0; i < user.length(); i++){
            if(banned.charAt(i) == '*'){
                continue;
            }
            if(banned.charAt(i) != user.charAt(i)){
                flag = false;
                break;
            }
        }
        
        return flag;
    }
}
```
