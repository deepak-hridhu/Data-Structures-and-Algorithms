import java.util.EmptyStackException;

public class MyStack {
    int[] arr;
    int size;
    int capacity;

    MyStack(int cap) {
        this.arr = new int[cap];
        this.capacity = cap;
    }
    int size(){
        return this.size;
    }
    int capacity(){
        return this.capacity;
    }
    int pop() throws EmptyStackException{
        if(this.size == 0)
            throw new EmptyStackException();
        int result = this.arr[this.size - 1];
        this.arr[this.size - 1] = Integer.MIN_VALUE;
        this.size--;
        return result;
    }
    MyStack push(int val) throws StackOverflowError{
        if(this.capacity == this.size) {
            throw new StackOverflowError();
        } else {
            this.arr[size++] = val;
            return this;
        }
    }
    int peek() throws EmptyStackException{
        if(this.size == 0)
            throw new EmptyStackException();
        return this.arr[ this.size - 1 ];
    }

    boolean isEmpty(){
        return this.size == 0;
    }

    boolean clear(){
        for(int i=0 ; i < this.size ; ++i)
            this.pop();
        return this.isEmpty();
    }
    void resize(int newCap){
        capacity = newCap;
        arr = new int[capacity];
    }
    public static void main(String[] args) {
        MyStack s = new MyStack(3);
        System.out.println(s.isEmpty());
        s.push(1);
        int x = s.peek();
        System.out.println(x);
        s.push(2).push(3);
        System.out.println(s.peek());
    }

}
