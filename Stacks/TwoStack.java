import java.util.ArrayDeque;
import java.util.EmptyStackException;

public class TwoStack {
        int[] arr;
        int capacity;
        int left;
        int right;
        TwoStack(int capacity){
            this.arr = new int[capacity];
            this.capacity = capacity;
            this.left = -1;
            this.right = capacity;
        }
        boolean pushLeft(int val){
            if(left < right-1){
               arr[++this.left] = val;
               return true;
            } else {
                return false;
            }
        }

        boolean pushRight(int val){
            if(left < right-1){
                arr[--this.right] = val;
                return true;
            } else {
                return false;
            }
        }
        Integer popLeft() throws EmptyStackException{
//            if(left == -1)
//                throw new EmptyStackException();
            if(left >= 0){
                return this.arr[left--];
            } else {
                return null;
            }
        }
        Integer popRight() throws EmptyStackException{
//            if(left == -1)
//                throw new EmptyStackException();
            if(left >= 0){
                return this.arr[right++];
            } else {
                return null;
            }
        }
        int leftSize(){
            return left;
        }
        int rightSize(){
            return left;
        }
        boolean isLeftEmpty(){
            return left == -1;
        }
        boolean isRightEmpty(){
            return right == capacity;
        }
        boolean isEmpty(){
            return (left == -1) && (right == capacity);
        }
        boolean clearLeft(){
            for(int i=0 ; i>=left && i<right ; ++i){
                this.popLeft();
            }
            return isLeftEmpty();
        }
        boolean clearRight(){
            for(int i=capacity-1 ; i>left && i<=right ; --i){
                this.popRight();
            }
            return isRightEmpty();
        }
        boolean clear(){
            for(int i=0 ; i<left ; ++i)
                this.popLeft();
            for(int i=capacity-1 ; i>right ; --i)
                this.popRight();
            return this.isEmpty();
        }

    public static void main(String[] args) {

    }
}
