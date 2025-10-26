class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}


class BinaryTree {
    private Node root;

    public BinaryTree() {
        this.root = null;
    }

    public void add(int value) {
        root = addRecursive(root, value);
        System.out.println("Added element: " + value);
    }

    private Node addRecursive(Node current, int value) {
        if (current == null) {
            return new Node(value);
        }

        if (value < current.value) {
            current.left = addRecursive(current.left, value);
        } 

        else if (value > current.value) {
            current.right = addRecursive(current.right, value);
        }

        return current;
    }

    public boolean search(int value) {
        return searchRecursive(root, value);
    }

    private boolean searchRecursive(Node current, int value) {
 
        if (current == null) {
            return false;
        }

        if (value == current.value) {
            return true;
        }

        return value < current.value
                ? searchRecursive(current.left, value)
                : searchRecursive(current.right, value);
    }

    public void delete(int value) {
        root = deleteRecursive(root, value);
        System.out.println("Deleted element: " + value);
    }

    private Node deleteRecursive(Node current, int value) {
      
        if (current == null) {
            return null;
        }

        if (value == current.value) {
           
            if (current.left == null && current.right == null) {
                return null;
            }

            if (current.left == null) {
                return current.right;
            }

            if (current.right == null) {
                return current.left;
            }

            int smallestValue = findSmallestValue(current.right);
            current.value = smallestValue;
            current.right = deleteRecursive(current.right, smallestValue);
            return current;
        }

        if (value < current.value) {
            current.left = deleteRecursive(current.left, value);
            return current;
        }

        current.right = deleteRecursive(current.right, value);
        return current;
    }

    private int findSmallestValue(Node root) {
        return root.left == null ? root.value : findSmallestValue(root.left);
    }

    //S root l r
    public void traversePreOrder() {
        System.out.print("Pre-order traversal: ");
        traversePreOrderRecursive(root);
        System.out.println();
    }

    private void traversePreOrderRecursive(Node node) {
        if (node != null) {
            System.out.print(node.value + " ");
            traversePreOrderRecursive(node.left);
            traversePreOrderRecursive(node.right);
        }
    }

    // C l root r
    public void traverseInOrder() {
        System.out.print("In-order traversal: ");
        traverseInOrderRecursive(root);
        System.out.println();
    }

    private void traverseInOrderRecursive(Node node) {
        if (node != null) {
            traverseInOrderRecursive(node.left);
            System.out.print(node.value + " ");
            traverseInOrderRecursive(node.right);
        }
    }

    public void traversePostOrder() {
        System.out.print("Post-order traversal: ");
        traversePostOrderRecursive(root);
        System.out.println();
    }

    private void traversePostOrderRecursive(Node node) {
        if (node != null) {
            traversePostOrderRecursive(node.left);
            traversePostOrderRecursive(node.right);
            System.out.print(node.value + " ");
        }
    }


    public void printTree() {
        System.out.println("  Tree Structure  ");
        printTreeRecursive(root, "", true);
        System.out.println("        ");
    }

    private void printTreeRecursive(Node node, String prefix, boolean isTail) {
        if (node == null) {
            return;
        }

        System.out.println(prefix + (isTail ? "└── " : "├── ") + node.value);

        if (node.left != null || node.right != null) {
            if (node.left != null) {
                printTreeRecursive(node.left, prefix + (isTail ? "    " : "│   "), node.right == null);
            }
            if (node.right != null) {
                printTreeRecursive(node.right, prefix + (isTail ? "    " : "│   "), true);
            }
        }
    }

    public boolean isEmpty() {
        return root == null;
    }
}

public class TestBinaryTree {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        System.out.println(" Adding Elements  ");
        tree.add(50);
        tree.add(30);
        tree.add(70);
        tree.add(20);
        tree.add(40);
        tree.add(60);
        tree.add(80);
        tree.add(10);
        tree.add(25);

        tree.printTree();

        System.out.println("\n Tree Traversals  ");
        tree.traverseInOrder();
        tree.traversePreOrder();
        tree.traversePostOrder();

        System.out.println("\n  Searching Elements ");
        int[] searchValues = {40, 100, 10, 75};
        for (int value : searchValues) {
            boolean found = tree.search(value);
            System.out.println("Елемент " + value + (found ? "found" : " NOT found"));
        }

        System.out.println("\n  Deleting Elements  ");
        tree.delete(20);
        tree.delete(30);
        tree.delete(50);

        tree.printTree();
        tree.traverseInOrder();
    }
}