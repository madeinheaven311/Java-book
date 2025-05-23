package com.d4c;

class Node {
    int val;
    Node next;
    Node random;

    public Node() {
        System.out.println("Node");
    }
    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}