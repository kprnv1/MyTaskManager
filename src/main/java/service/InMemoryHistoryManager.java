package service;

import model.Task;

import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {
    private final HashMap<Integer, Node> history = new HashMap<>();
    private int id = 0;

    @Override
    public void add(Task task) {
        if (task != null) {
            task.setId(id++);
            linkLast(task);
        }
        final int SIZE_LIST = 10;
        if (history.size() > SIZE_LIST) {
            Task task0 = history.get(0).getItem();
            removeTaskInHistoryByTask(task0);
        }
    }

    @Override
    public void remove(int id) {
        if (id < 10 && id < history.size()) {
            removeTaskInHistoryByTask(history.get(id).getItem());
        } else System.out.println("Значения " + id + " не существует!");
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

    private void linkLast(Task task) {
        Node node = getLastNode();
        if (node != null) {
            Node nodeLast = new Node(node, task, null);
            history.put(task.getId(), nodeLast);
            node.setNext(nodeLast);
            node.setTail(false);
            nodeLast.setTail(true);
            compareTask(task);
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

    private void compareTask(Task task) {
        for (int i = 0; i < history.size() - 1; i++) {
            if (history.get(i).getItem().getName().equals(task.getName())) {
                task = history.get(i).getItem();
                Node node = history.get(task.getId());
                Node firstNode = node.getPrev();
                Node lastNode = node.getNext();
                firstNode.setNext(lastNode);
                lastNode.setPrev(firstNode);
//                node = null;
                removeTaskInHistoryByTask(task);
            }
        }
    }

    private void removeTaskInHistoryByTask(Task task) {
        Node node = history.get(task.getId());
        if (node == null) {
            return;
        }
        if (node.isTail() && !node.isHead()) {
            Node firstNode = node.getPrev();
            firstNode.setNext(null);
            firstNode.setTail(true);
            node.setPrev(null);
            history.remove(task.getId());
        } else if (node.isTail() && node.isHead()) {  //(один элемент)
            history.remove(task.getId());
        } else if (!node.isHead() && !node.isTail()) {  // (самая середина)
            removeTaskMiddle(task);
        } else if (!node.isTail() && node.isHead()) {
            removeTaskFirst(task);
        }
        id--;
    }

    private void removeTaskMiddle(Task task) {
        int count = 0;
        for (int i = task.getId(); i < history.size(); i++) {
            Node node = history.get(i);
            Node lastNode = node.getNext();
            count++;
            if (lastNode == null) {
                history.remove(task.getId() + count - 1);
                break;
            }
            lastNode.getItem().setId(i);
            history.put(i, lastNode);
        }
    }

    private void removeTaskFirst(Task task) {
        for (int i = 0; i < history.size() - 1; i++) {
            Node node = history.get(task.getId() + i);
            Node lastNode = node.getNext();
            if (node.getItem().getId() == 0) {
                node.setHead(true);
            }
            lastNode.getItem().setId(task.getId() + i);
            history.remove(task.getId() + i);
            history.put(task.getId() + i, lastNode);
            if (i == history.size() - 2) {
                history.remove(task.getId() + i + 1);
            }
        }
    }

}

