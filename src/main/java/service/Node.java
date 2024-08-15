package service;

import model.Task;

public class Node {

    private boolean head = false;
    private  boolean tail = false;

    private Task item;
    private Node next;
    private Node last;

    public Node(Task item, Node next, Node last) {
        this.item = item;
        this.next = next;
        this.last = last;
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

    public Node getLast() {
        return last;
    }

    public void setLast(Node last) {
        this.last = last;
    }

    @Override
    public String toString() {
        return "Node{" +
                "next=" + next +
                ", last=" + last +
                ", item=" + item +
                '}';
    }

    //Head=true
//Tail=false
//Значит node это первый элемент
//
//Head = true
//Tail =true
//Значит в списке 1 элемент node
//
//Head =false
//Tail = false
//Значит node не первый и не послелний элемент в списке = серидина
//
//Head  = false
//Tail =true
// значит node послелний в списке

}