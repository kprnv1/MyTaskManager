package service;

import model.Task;

import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {
    private final HashMap<Integer, Node> history = new HashMap<>();

    @Override
    public void add(Task task) {
        Node node = getLastNode();
        if (node != null) {
            Node nodeLast = new Node(task, null, node);
            history.put(task.getId(), nodeLast);
            node.setTail(false);
            nodeLast.setTail(true);
        } else {
            Node nodeLast = new Node(task, null, null);
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
        Node node = history.get(task.getId());
        if (node == null) {
            return;
        }
        if (node.isTail() && !node.isHead()) {   // ХВОСТ и не голова
            Node lastNode = node.getLast();
            lastNode.setNext(null);
            lastNode.setTail(true);
            node.setLast(null);
            history.remove(task.getId());
        } else if (node.isTail() && node.isHead()) {  // ХВОСТ и ГОЛОВА
            history.remove(task.getId());
        } else if (!node.isHead() && !node.isTail()) {  // не голова и не хвост
            Node lastNode = node.getLast();
            Node nextNode = node.getNext();
            lastNode.setNext(nextNode);
            nextNode.setLast(lastNode);
            node.setLast(null);
            node.setNext(null);
            history.remove(task.getId());
        } else if (!node.isTail() && node.isHead()) {  // не хвост и ГОЛОВА
            Node nextNode = node.getLast();
            nextNode.setNext(null);
            node.setHead(false);
            node.setLast(null);
        }
    }

}
