import java.util.NoSuchElementException;

public class LL<T> {
    private static class Node<T> {
        T value;
        Node<T> next;

        public Node() {
        }

        public Node(T val) {
            this.value = val;
        }

        public Node(T val, Node node) {
            this.value = val;
            this.next = node;
        }
    }

    private Node<T> head, tail;
    private int size;
    public void addFirst(T val) {
        if( head == null) {
        head = new Node(val, null);
        tail = head;
        } else {
             Node<T> first = new Node(val, head);
             head = first;
        }
        size++;
    }
    public void addLast(T val) {
        if ( head == null) {
            head = new Node(val, null);
            tail = head;
        } else {
            tail.next = new Node(val, null);
            tail = tail.next;
        }
        size++;
    }
    public void add(T val) {
        this.addLast(val);
    }
    public void add(int index, T val) {
        if (index == 0) {
            this.addFirst(val);
        } else if (index == size) {
            this.addLast(val);
        } else {
            Node<T> cur = head;
            for(int i=0 ; i<index ; ++i)
                cur = cur.next;
            cur.next = new Node(val, cur.next);
        }
    }
    public T remove() throws NoSuchElementException {
        return this.removeLast();
    }

    public T removeFirst() throws NoSuchElementException {
        if(head == null) {
            throw new NoSuchElementException();
        }
        T first = head.value;
        head = head.next;
        size--;
        if(size == 0) {
            tail = null;
        }
        return first;
    }
    private Node getNode(int index) {
        Node<T> node = head;
        for(int i=0 ; i<index ; ++i)
            node = node.next;
        return node;
    }
    public T remove(int index) throws NoSuchElementException {
        if(head == null) {
            throw new NoSuchElementException();
        }
        Node<T> node = getNode(index-1);
        T val = node.next.value;
        node.next = node.next.next;
        return val;
    }
    public T removeLast() throws NoSuchElementException {
        if(head == null) {
            throw new NoSuchElementException();
        }
        Node<T> node = getNode(size-2);
        T val = node.next.value;
        node.next = node.next.next;
        return val;
    }
    public void prettyPrint() {
        Node<T> cur = head;
        while (cur != null) {
            System.out.print(cur.value + " -> ");
            cur = cur.next;
        }
        System.out.println("END");
    }
    
}
