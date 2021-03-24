package org.common.tree;

public class Node<T> {

    public Node<T> left;
    public Node<T> right;

    public T data;

    public Node(){
    }

    public Node(T data){
        this.data = data;
    }

}
