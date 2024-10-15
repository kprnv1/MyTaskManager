package service;

import model.Task;

import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {
    private final HashMap<Integer, Node> history = new HashMap<>();
    final int SIZE_LIST = 10;


    @Override
    public void add(Task task) {
        if (task != null) {
            remove(task.getId());
            linkLast(task);
        }
    }

    @Override
    public void remove(int id) {
        if (history.size() == SIZE_LIST){
            history.remove(1);
            transfer();
        }
        Node node = history.get(id);
        removeNode(node);
    }

    public void removeNode(Node node) {
        if (node == null) {
            return;
        }
        if (node.isTail() && !node.isHead()) {
            Node firstNode = node.getPrev();
            firstNode.setNext(null);
            firstNode.setTail(true);
            node.setHead(false);
            node.setNext(null);
            history.remove(node.getItem().getId());
            transfer();
        } else if (node.isTail() && node.isHead()) {
            history.remove(node.getItem().getId());
            transfer();
        } else if (!node.isHead() && !node.isTail()) {
            Node lastNode = node.getNext();
            Node firstNode = node.getPrev();
            node.setNext(null);
            node.setPrev(null);
            lastNode.setPrev(firstNode);
            firstNode.setNext(lastNode);
            history.remove(node.getItem().getId());
            transfer();
        } else if (!node.isTail() && node.isHead()) {
            Node firstNode = node.getNext();
            node.setHead(false);
            firstNode.setHead(true);
            firstNode.setPrev(null);
            history.remove(node.getItem().getId());
            transfer();
        }
    }

    @Override
    public List<Task> getHistory() {
        return getTasks();
    }

    private List<Task> getTasks() {
        List<Task> list = new ArrayList<>();
        for (int i = 1; i <= history.size(); i++) {
            list.add(history.get(i).getItem());
        }
        return list;
    }

    private void linkLast(Task task) {
        Node node = getLastNode();
        if (node != null) {
            Node nodeLast = new Node(node, task, null);
            history.put(history.size() + 1, nodeLast);
            nodeLast.setPrev(node);
            node.setNext(nodeLast);
            node.setTail(false);
            nodeLast.setTail(true);
        } else {
            Node nodeFirst = new Node(null, task, null);
            history.put(task.getId(), nodeFirst);
            nodeFirst.setTail(true);
            nodeFirst.setHead(true);
        }
    }

    public void transfer() {
        for (int i = 1; i <= history.size(); i++) {
            if (history.get(i) == null) {
                history.put(i, history.get(i + 1));
                history.remove(i + 1);
            } else continue;
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

}

