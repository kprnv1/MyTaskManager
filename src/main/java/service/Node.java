package service;

import model.Task;

public class Node {

    private boolean head = false;
    private  boolean tail = false;

    private Task item;
    private Node next;  //следующий
    private Node prev;  //придыдущий


    public Node(Node prev,Task item, Node next) {
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
        return "Node{" +
  //              "prev=" + prev + // Node
                ", item=" + item +
 //               ", next=" + next + // Node
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