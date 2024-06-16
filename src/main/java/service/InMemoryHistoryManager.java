package service;

import model.Task;

import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {

    private final int HISTORY_SIZE = 9;
    private final Map<Integer, Node<Task>> history;
    Node<Task> first;
    Node<Task> last;

    public InMemoryHistoryManager() {
        this.history = new HashMap<>();
    }

    @Override
    public void add(Task task) {
        if (history.size() > HISTORY_SIZE) {
            history.remove(0);
        }
            Node<Task> node = history.get(task.getId());
            removeNode(node);
            linkLast(task);

    }

    @Override
    public List<Task> getHistory() {
        return getTasks();
    }

    public List<Task> getTasks() {   //TODO собирает в список все задачи
        List<Task> list = new ArrayList<>();
        Node<Task> current = first;
        while (current != null) {
            list.add(current.item);
            current = current.next;
        }
        return list;
    }

    public void remove(int id) {
        Node<Task> node = history.get(id);
        removeNode(node);
    }

    public void linkLast(Task task) {  //TODO добавление задачи в конец списка
        final Node<Task> l = last;
        final Node<Task> newNode = new Node<>(l, task, null);
        last = newNode;
        history.put(task.getId(), newNode);
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
    }

    public void removeNode(Node<Task> node) {
        if (!(node == null)) {
            final Node<Task> next = node.next;
            final Node<Task> prev = node.prev;
            node.item = null;
            if (first == node && last == node) {
                first = null;
                last = null;
            } else if (first == node) {
                first = next;
                first.prev = null;
            } else if (last == node) {
                last = prev;
                last.next = null;
            } else {
                prev.next = next;
                next.prev = prev;
//                history.remove(node.item.getId());
            }
        }
    }


    public static class Node<Task> {

        Task item;  // данные внутри элемента
        Node<Task> next; //ссылка на следующий элемент
        Node<Task> prev; // ссылка на предыдущий элемент

        public Node(Node<Task> prev, Task item, Node<Task> next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

}