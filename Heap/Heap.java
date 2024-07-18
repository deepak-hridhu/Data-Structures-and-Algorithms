import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Heap<T extends Comparable<T>> {
    private ArrayList<T> list;

    public Heap() {
        list = new ArrayList<>();
    }
    void swap(int first, int second) {
        T temp = list.get(first);
        list.set(first, list.get(second));
        list.set(second, temp);
    }

    private int parent(int index) {
        return (index/2);
    }
    public int left(int index) {
        return (2 * index)+1;
    }

    public int right(int index) {
        return (2 * index) + 2;
    }

    public void add(T value) {
        list.add(value);
        upHeap(list.size() - 1);
    }

    private void upHeap(int index) {
        if(index == 0)
            return;
        var p = parent(index);
        if(list.get(index).compareTo(list.get(p)) < 0) {
            swap(index, p);
            upHeap(p);
        }
    }

    public T poll() throws NoSuchElementException {
        if(list.isEmpty())
            throw new NoSuchElementException();
        T top = list.get(0);
        T last = list.remove(list.size()-1);
        if(!list.isEmpty()) {
            list.set(0, last);
            downHeap(0);
        }
        return top;
    }

    private void downHeap(int index) {
        int min= index;
        int left = left(index);
        int right = right(index);

        if(left < list.size() && list.get(min).compareTo(list.get(left)) > 0)
            min = left;

        if(right < list.size() && list.get(min).compareTo(list.get(right)) > 0)
            min = right;

        if(min != index) {
            swap(index, min);
            downHeap(min);
        }

    }

    public ArrayList<T> heapSort() throws NoSuchElementException {
        ArrayList<T> data = new ArrayList<>();

        while(!list.isEmpty()) {
            data.add(this.poll());
        }
        return data;
    }

   
}
