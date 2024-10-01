package service;

import model.Task;

import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {
    private final HashMap<Integer, Node> history = new HashMap<>();
    public int id = 0;


    @Override
    public void add(Task task) {
        if (task != null) {
            task.setId(id++);
//            remove(task.getId()); // должно быть не более 10
            linkLast(task);
        }
    }


    @Override
    public void remove(int id) {
        System.out.println("Вот этот элемент удаляется: " + history.get(id).getItem());
        removeTaskInHistoryByTask(history.get(id).getItem());
    }

    @Override
    public List<Task> getHistory() {
        return getTasks();
    }

    private List<Task> getTasks() {
        List<Task> list = new ArrayList<>();
        for (int i = 0; i < history.size(); i++) {
            list.add(history.get(i).getItem());
        }
        return list;
    }


    public void linkLast(Task task) {   // Новый метод
        Node node = getLastNode();
        if (node != null) {
            Node nodeLast = new Node(node, task, null);
            history.put(task.getId(), nodeLast);
            node.setNext(nodeLast);
            node.setTail(false);
            nodeLast.setTail(true);
        } else {
            Node nodeLast = new Node(null, task, null);
            history.put(task.getId(), nodeLast);
            nodeLast.setTail(true);
            nodeLast.setHead(true);

        }

    }

    private Node getLastNode() {    // Последний node                // Новый метод
        for (Integer i : history.keySet()) {
            if (history.get(i).isTail()) {
                return history.get(i);
            }
        }
        return null;
    }

    private void removeTaskInHistoryByTask(Task task) {  // Новый метод
        Node node = history.get(task.getId());
        if (node == null) {
            return;
        }
        if (node.isTail() && !node.isHead()) {   // ХВОСТ и не голова              РАБОТАЕТ
            Node firstNode = node.getPrev();  // первый элемент
            firstNode.setNext(null);
            firstNode.setTail(true);
            node.setPrev(null);
            history.remove(task.getId());
        } else if (node.isTail() && node.isHead()) {  // ХВОСТ и ГОЛОВА (один элемент)            РАБОТАЕТ
            history.remove(task.getId());
        } else if (!node.isHead() && !node.isTail()) {  // не голова и не хвост
//            Node lastNode = node.getPrev();
//            Node nextNode = node.getNext();
//            lastNode.setNext(nextNode);
//            nextNode.setPrev(lastNode);
//            node.setPrev(null);
//            node.setNext(null);
            history.remove(task.getId());
        } else if (!node.isTail() && node.isHead()) {  // не хвост и ГОЛОВА
            Node lastNode = node.getNext();
            lastNode.getItem().setId(task.getId());
            lastNode.setPrev(null);
            lastNode.setHead(true);
            node.setItem(null);
            history.remove(task.getId());
            history.put(0, lastNode);
            history.remove(task.getId() + 1);
        }
        id--;
    }

}
