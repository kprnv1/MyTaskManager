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


    public void linkLast(Task task) {
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

    private Node getLastNode() {
        for (Integer i : history.keySet()) {
            if (history.get(i).isTail()) {
                return history.get(i);
            }
        }
        return null;
    }

    private void removeTaskInHistoryByTask(Task task) {
        System.out.println(task.getId());
        Node node = history.get(task.getId());
        if (node == null) {
            return;
        }
        if (node.isTail() && !node.isHead()) {   //                               РАБОТАЕТ безупречно
            Node firstNode = node.getPrev();
            firstNode.setNext(null);
            firstNode.setTail(true);
            node.setPrev(null);
            history.remove(task.getId());
        } else if (node.isTail() && node.isHead()) {  //(один элемент)            РАБОТАЕТ безупречно
            history.remove(task.getId());
        } else if (!node.isHead() && !node.isTail()) {  // (самая середина)       РАБОТАЕТ безупречно
            removeTaskMiddle(task);
        } else if (!node.isTail() && node.isHead()) {  // не хвост и ГОЛОВА       РАБОТАЕТ безупречно
            removeTaskFirst(task);
        }
        id--;
    }


    public void removeTaskMiddle(Task task) {
        int number = 0;
        int count = 0;
        for (int i = task.getId(); i < history.size(); i++) {
            Node node = history.get(i);
            Node firstNode = node.getPrev();
            Node lastNode = node.getNext();
            int num = i;
            number = num;
            count++;
            if (lastNode == null) {
                history.remove(task.getId() + count - 1);
                break;
            }
            firstNode.setNext(lastNode);
            lastNode.getItem().setId(num);
            history.put(num, lastNode);
        }


    }


    public void removeTaskFirst(Task task) {                     // Дополнительный метод РАБОТАЕТ
        for (int i = 0; i < history.size() - 1; i++) {
            Node node = history.get(task.getId() + i);
            Node lastNode = node.getNext();
            lastNode.setPrev(null);
            lastNode.getItem().setId(task.getId() + i);
            history.remove(task.getId() + i);
            history.put(task.getId() + i, lastNode);
            if (i == history.size() - 2) {
                lastNode.setNext(null);
                history.remove(task.getId() + i + 1);
            }
        }

    }
}

