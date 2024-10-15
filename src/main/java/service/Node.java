package service;

import model.Task;

public class Node {
    private boolean head = false;
    private boolean tail = false;
    private Task item;
    private Node next;
    private Node prev;

    public Node(Node prev, Task item, Node next) {
        this.prev = prev;
        this.item = item;
        this.next = next;
    }

    public boolean isHead() {
        return head;
    }

    public void setHead(boolean head) {
        this.head = head;
    }

    public boolean isTail() {
        return tail;
    }

    public void setTail(boolean tail) {
        this.tail = tail;
    }

    public Task getItem() {
        return item;
    }

    public void setItem(Task item) {
        this.item = item;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    @Override
    public String toString() {
        return "Node{item=" + item + '}';
    }

}