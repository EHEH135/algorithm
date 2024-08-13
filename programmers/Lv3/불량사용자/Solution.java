import java.util.*;

class solution {

    private String[] userId;
    private String[] bannedId;
    private HashSet<HashSet<String>> result = new HashSet<>();
    public static void main(String[] args){
        solution my = new solution();
        // TCs
		// String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};		//TC #1
		// String[] banned_id = {"fr*d*", "abc1**"};		//TC #1
		// String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};		//TC #2
		// String[] banned_id = {"*rodo", "*rodo", "******"};		//TC #2
		String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};		//TC #3
		String[] banned_id = {"fr*d*", "*rodo", "******", "******"};		//TC #3 


        // Solution output
		int output = my.solution(user_id, banned_id);
		System.out.println(output);
    }
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