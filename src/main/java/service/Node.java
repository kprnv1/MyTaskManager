package service;

import java.util.Objects;

import static service.InMemoryHistoryManager.head;
import static service.InMemoryHistoryManager.tail;

public class Node <Task> {

        private Node<Task> nextElement;
        private Node<Task> previousElement;
        private Task currentElement;

        public Node(Node<Task> previousElement, Task currentElement, Node<Task> nextElement) {
            this.previousElement = previousElement;
            this.currentElement = currentElement;
            this.nextElement = nextElement;
        }

    public Node<Task> getNextElement() {
        return nextElement;
    }

    public void setNextElement(Node<Task> nextElement) {
        this.nextElement = nextElement;
    }

    public Node<Task> getPreviousElement() {
        return previousElement;
    }

    public void setPreviousElement(Node<Task> previousElement) {
        this.previousElement = previousElement;
    }

    public Task getCurrentElement() {
        return currentElement;
    }

    public void setCurrentElement(Task currentElement) {
        this.currentElement = currentElement;
    }

    public static Node removeNode(Node<model.Task> node) {
        if (head.equals(node) && tail.equals(node)) {
            head = null;
            tail = null;
            return node;
        }
        if (!head.equals(node) && !tail.equals(node)) {
            node.getPreviousElement().setNextElement(node.getNextElement());
            node.getNextElement().setPreviousElement(node.getPreviousElement());
            node.setNextElement(null);
            node.setPreviousElement(null);
            return node;
        }
        if (head.equals(node)) {
            node.getNextElement().setPreviousElement(null);
            head = node.getNextElement();
            node.setNextElement(null);
            return node;
        }
        if (tail.equals(node)) {
            node.getPreviousElement().setNextElement(null);
            tail = node.getPreviousElement();
            node.setPreviousElement(null);
            return node;
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node<?> node = (Node<?>) o;
        return Objects.equals(nextElement, node.nextElement) && Objects.equals(previousElement, node.previousElement)
                && Objects.equals(currentElement, node.currentElement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nextElement, previousElement, currentElement);
    }

    @Override
    public String toString() {
        return "Node{" +
                "nextElement=" + nextElement +
                ", previousElement=" + previousElement +
                ", currentElement=" + currentElement +
                '}';
    }
}