// 💡 store pairs (value, min_so_far).

//using ArrayList (faster)
class MinStack {
    private List<int[]> st;

    public MinStack() {
        st = new ArrayList<>();
    }
    
    public void push(int val) {
        int[] top = st.isEmpty() ? new int[]{val, val} : st.get(st.size() - 1);
        int min_val = top[1];
        if (min_val > val) {
            min_val = val;
        }
        st.add(new int[]{val, min_val});        
    }
    
    public void pop() {
        st.remove(st.size() - 1);
    }
    
    public int top() {
        return st.isEmpty() ? -1 : st.get(st.size() - 1)[0];
    }
    
    public int getMin() {
        return st.isEmpty() ? -1 : st.get(st.size() - 1)[1];
    }
}

//-------- Using Stack ----------------

class MinStack {
    //store pairs (value, min_so_far).
    Stack<int[]> ms;

    public MinStack() {
        ms= new Stack<int[]>();
    }
    
    public void push(int val) {
        int currentMin = ms.isEmpty() ? val : ms.peek()[1];
        ms.push(new int[]{val,Math.min(val, currentMin)});
    }
    
    public void pop() {
        ms.pop();
    }
    
    public int top() {
        return ms.peek()[0];
    }
    
    public int getMin() {
        return ms.peek()[1];
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
