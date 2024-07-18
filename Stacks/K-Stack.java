public class KStack {
    char[] arr;
    int[] peek;
    int[] next;
    int capacity;
    int stNum;
    int free;
    int size;
    KStack(int n, int k) {
        this.arr = new char[n];
        this.peek = new int[k];
        this.next = new int[n];
        this.free = 0;
        this.capacity = n;
        this.size = 0;
        this.stNum = k;

        for(int i=0 ; i < n ; ++i)
            arr[i] = '\0';
        for(int i=0 ; i < k ; ++i)
            peek[i] = -1;
        for(int i=0 ; i < n-1 ; ++i)
            next[i] = i + 1;
        next[capacity-1] = -1;
    }
    boolean push(char val, int stNum) {
        if( size == capacity) {
            return false;
            // alternative : StackOverFlowException
        }
        arr[free] = val;
//        if( peek[stNum-1] == -1)
        int i = free;
        free = next[i];
        next[ i ] = peek[ stNum-1 ];
        peek[stNum - 1] = i;
        return true;
    }
    boolean isEmpty(int stNum) {
        return peek[stNum - 1] == -1;
    }
    boolean isFull(int stNum) {
        return free == -1;
    }
    char pop(int stNum){
        if( this.isEmpty(stNum) ){
            return '\0';
            //alternative : EmptyStackException
        }
        int top = peek[stNum - 1];
        peek[stNum-1] = next[top];
        next[top] = free;
        free = top;
        return arr[top];
    }

}
