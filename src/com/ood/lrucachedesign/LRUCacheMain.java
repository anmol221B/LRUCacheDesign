package com.ood.lrucachedesign;


import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

class Node{
    int key;
    int val;
    Node next;
    Node prev;

    Node(int key,int val){
        this.key = key;
        this.val = val;
        this.next = null;
        this.prev = null;
    }

    public void pushNode(Node prevNode,Node node){
        prevNode.next = node;
        node.prev = prev;
    }

}


public class LRUCacheMain {
    int capacity;
    HashMap<Integer,Node> mp = new HashMap<>();
    Node head = null;
    Node end = null;

    public LRUCacheMain(int capacity){
        this.capacity = capacity;
    }

    public int get(int key){
        if(mp.containsKey(key)){
            Node node = mp.get(key);
            delete(node);
            setHead(node);
            return node.val;
        }
        return -1;
    }

    public void set(int key,int val){
        if(mp.containsKey(key)){
            Node node = mp.get(key);
            node.val = val;
            delete(node);
            setHead(node);
        }
        else{
            Node node = new Node(key,val);
            if(mp.size()<capacity){
                setHead(node);
            }
            else{
                mp.remove(end.key);
                delete(end);
                setHead(node);
            }
            mp.put(key,node);

        }
    }



    public void delete(@NotNull Node node){
        if(node.prev!=null){
            node.prev.next = node.next;
        }
        else{
            head = node.next;
        }

        if(node.next!=null){
            node.next.prev = node.prev;
        }else{
            end = node.prev;
        }
    }


    public void setHead(Node node){
        node.next = head;
        if(head!=null)
            head.prev = node;

        head = node;

        if(end==null)
            end = head;
    }
    public static void main(String[] args) {

        LRUCacheMain lrucache = new LRUCacheMain(4);
        lrucache.set(1, 100);
        lrucache.set(10, 99);
        lrucache.set(15, 98);
        lrucache.set(10, 97);
        lrucache.set(12, 96);
        lrucache.set(18, 95);
        lrucache.set(1, 94);

        System.out.println(lrucache.get(1));
        System.out.println(lrucache.get(10));
        System.out.println(lrucache.get(15));




    }
}
