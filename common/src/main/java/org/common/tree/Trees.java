package org.common.tree;


import java.util.Objects;

public class Trees {
    /**
     * 将二叉树转循环链表，返回头节点
     */
    public static <T> Node<T> toCircleLink(Node<T> root){
        Node<T> linkInfo = new Node<>();  //借用数据结构保存头尾节点
        if (root == null) {
            return null;
        }

        dfs(linkInfo,root);

        linkInfo.right.right = linkInfo.left;
        linkInfo.left.left =linkInfo.right;

        return linkInfo.left;
    }

    public static void dfs(Node linkInfo, Node current) {
        if (current == null){
            return;
        }
        dfs(linkInfo,current.left);

        if (linkInfo.right == null){
            linkInfo.left = current;
        } else {
            linkInfo.right.right =current;
        }

        current.left = linkInfo.right;
        linkInfo.right = current;

        dfs(linkInfo,current.right);
    }

    public static void main(String[] args) {
        Node<String> a = new Node<>("A");
        Node<String> b = new Node<>("B");
        Node<String> c = new Node<>("C");
        Node<String> d = new Node<>("D");
        Node<String> e = new Node<>("E");
        a.left =b;
        a.right=c;
        b.left=d;
        c.left=e;

        Node<String> head = toCircleLink(a);
        Node<String> current = head;
        while (Objects.nonNull(current)){
            System.out.print(head.data+" -- ");
            head =head.right;

            if(Objects.equals(head.data,current.data)){
                break;
            }
        }
    }
}
