import java.util.Map;

class Solution {
    public boolean isValid(String s) {
        //💡 Use a stack(because of LIFO need) to keep track of opening brackets
        // we are pushing closing version of opening bracket to avoid map
        //💡 When you see a closing bracket, check if it matches the top of the stack.
        Stack<Character> stack= new Stack<>();
        for(char c : s.toCharArray()){
            if(c=='(')stack.push(')');
            else if(c=='{')stack.push('}');
            else if(c=='[')stack.push(']');
            else if(stack.isEmpty() || stack.pop() != c){
                return false;
            }
        }
        return stack.isEmpty();
    }
}
