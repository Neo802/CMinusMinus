package classes;

import java.util.ArrayList;
import java.util.List;

// TO BE REFACTORED

public class MultiWayTree {

    // Node class representing each node in the tree
    public static class Node {
        String label; // The label of the node (e.g., grammar rule)
        List<Node> children; // List of child nodes

        // Constructor
        public Node(String label) {
            this.label = label;
            this.children = new ArrayList<>();
        }

        // Method to add a child to the current node
        public void addChild(Node child) {
            children.add(child);
        }

        // Recursive method to print the tree structure
        public void printTree(String prefix) {
            System.out.println(prefix + label);
            for (Node child : children) {
                child.printTree(prefix + "  ");
            }
        }
    }

    private Node root; // Root of the tree

    // Constructor
    public MultiWayTree(String rootLabel) {
        this.root = new Node(rootLabel);
    }

    // Getter for the root node
    public Node getRoot() {
        return root;
    }

    // Example usage
    /*
    public static void main(String[] args) {
        // Create the tree based on the grammar
        MultiWayTree tree = new MultiWayTree("program");
        Node root = tree.getRoot();

        // Example structure for demonstration
        Node declaration = new Node("declaration");
        root.addChild(declaration);

        Node funDeclaration = new Node("fun_declaration");
        declaration.addChild(funDeclaration);

        Node type = new Node("type");
        funDeclaration.addChild(type);

        Node name = new Node("NAME");
        funDeclaration.addChild(name);

        Node block = new Node("block");
        funDeclaration.addChild(block);

        // Print the tree structure
        root.printTree("");
    }
    */
}
