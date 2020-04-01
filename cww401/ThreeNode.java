package cww401;

import cww331.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ThreeNode {
    static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node(int val) {
            this.val = val;
        }
    }

    public List <Integer> preOrderTraversal(Node root) {     //前序遍历
        List <Integer> result = new ArrayList <>();
        if (root == null) {    //空树返回一个空的List,
            return result;
        }
        //访问根节点，把元素添加到List中
        result.add(root.val);
        result.addAll(preOrderTraversal(root.left));
        result.addAll(preOrderTraversal(root.right));
        return result;
    }

    public List <Integer> inOrderTraversal(Node root) {    //中序遍历
        List <Integer> res = new ArrayList <>();
        if (root == null) {
            return res;
        }
        res.addAll(inOrderTraversal(root.left));
        res.add(root.val);
        res.addAll(inOrderTraversal(root.right));
        return res;
    }

    public List <Integer> posOrderTraversal(Node root) {      //后序遍历
        List <Integer> result1 = new ArrayList <>();
        if (root == null) {
            return result1;
        }
        result1.addAll(posOrderTraversal(root.left));
        result1.addAll(posOrderTraversal(root.right));
        result1.add(root.val);
        return result1;
    }

    public static Node Build(Node root) {  //后序遍历
        Node a = new Node(1);
        Node b = new Node(2);
        Node c = new Node(3);
        Node d = new Node(4);
        Node e = new Node(5);
        Node f = new Node(6);
        Node g = new Node(7);
        Node h = new Node(8);
        a.left = b;
        a.right = c;
        c.right = d;
        return a;
    }

    public boolean isSameTree(Node a, Node b) {    //判断两棵树是否相同
        if (a == null && b == null) {
            return true;
        }
        if (a == null || b == null) {  //这里只能由一个非空
            return false;
        }
        if (a.val != b.val) {  //判断根结点的值
            return false;
        }
        return isSameTree(a.left, b.left) && isSameTree(b.left, b.right);  //递归调用
    }

    public boolean isSubTree(Node a, Node b) {  //判断子树
        if (a == null && b == null) {
            return true;
        }
        if (a == null || b == null) {  //这里只能由一个非空
            return false;
        }
        boolean ret = false;
        if (a.val == b.val) {  //判断根结点的值
            ret = isSameTree(a, b);
        }
        return ret || isSubTree(a.left, b) || isSubTree(a.right, b);    //递归调用，短路求值
    }

    public int maxDepth(Node a) {    // 求最大深度
        if (a == null) {
            return 0;
        }
        if (a.right == null && a.left == null) {
            return 1;
        }
//                 return 1+ (maxDepth(a.left)>maxDepth(a.right)
//                         ?maxDepth(a.left): maxDepth(a.right)); //递归调用，三目运算符,执行三次
        int leftDepth = maxDepth(a.left);
        int rightDepth = maxDepth(a.right);
        return 1 + (leftDepth > rightDepth ? leftDepth : rightDepth);   //执行两次
    }

    public boolean isBalanced(Node root) {
        if (root == null) {
            return true; //空树也是平衡的
        }
        if (root.left == null && root.right == null) {
            return true;     //判断是否有子树
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        if (leftDepth - rightDepth > 1 || leftDepth - rightDepth < -1) {
            return false; //不是平衡树
        }
        return isBalanced(root.left) && isBalanced(root.right);
    }

    public boolean isSymmetric(Node root) {
        if (root == null) {
            return true;
        }
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(Node a, Node b) {
        if (a == null && b == null) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        if (a.val != b.val) {
            return false;
        }
        return isMirror(a.left, b.right) && isMirror(a.right, b.left);
    }

    public void levelOrder(Node root) {  //层排序
        Queue <Node> queue = new LinkedList <>();  //使用队列
        queue.offer(root);  //入队列
        while (!queue.isEmpty()) {
            Node cur = queue.poll();//出队列
            System.out.println(cur.val + " ");//打印元素
            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
        }
    }
    public static void main(String[] args) {
        Node root = Build();
        System.out.print("层遍历：");
        levelOrder(root);
    }
}
