package prep;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AllOne432 {
}

class AllOne {
    Node head;
    Node tail;
    Map<String, Node> map;
    public AllOne() {
        head = new Node(0);
        tail = new Node(0);
        map = new HashMap<>();
        head.next = tail;
        tail.prev = head;
    }

    public void inc(String key) {
        if(map.containsKey(key)) {
            Node node = map.get(key);
            int freq = node.freq;
            node.keys.remove(key);

            Node nextNode = node.next;
            if(nextNode == tail || nextNode.freq != freq + 1) {
                Node newNode = new Node(freq + 1);
                newNode.keys.add(key);
                newNode.prev = node;
                newNode.next = nextNode;
                node.next = newNode;
                nextNode.prev = newNode;
                map.put(key, newNode);
            } else {
                nextNode.keys.add(key);
                map.put(key, nextNode);
            }

            if(node.keys.isEmpty())
                remove(node);

        } else {
            Node firstNode = head.next;
            if(firstNode == tail || firstNode.freq > 1) {
                Node newNode = new Node(1);
                newNode.next = firstNode;
                newNode.prev = head;
                head.next = newNode;
                firstNode.prev = newNode;
                newNode.keys.add(key);
                map.put(key, newNode);
            } else {
                firstNode.keys.add(key);
                map.put(key, firstNode);
            }
        }
    }

    public void dec(String key) {
        if(!map.containsKey(key)) return;
        Node node = map.get(key);
        node.keys.remove(key);
        int freq = node.freq;
        if(freq == 1) {
            map.remove(key);
        } else {
            Node prevNode = node.prev;
            if(prevNode == head || prevNode.freq != freq - 1) {
                Node newNode = new Node(freq - 1);
                newNode.keys.add(key);
                map.put(key, newNode);
                newNode.prev = prevNode;
                newNode.next = node;
                prevNode.next = newNode;
                node.prev = newNode;
            } else {
                prevNode.keys.add(key);
                map.put(key, prevNode);
            }
        }
        if(node.keys.isEmpty())
            remove(node);
    }

    public String getMaxKey() {
        if(tail.prev == head)
            return "";
        return tail.prev.keys.iterator().next();
    }

    public String getMinKey() {
        if(head.next == tail)
            return "";
        return head.next.keys.iterator().next();
    }

    public void remove(Node node) {
        Node prevNode = node.prev;
        Node nextNode = node.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }

    class Node {
        int freq;
        Node next;
        Node prev;
        Set<String> keys;
        Node(int freq) {
            this.freq = freq;
            keys = new HashSet<>();
        }
    }
}



/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */