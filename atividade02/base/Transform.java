import java.util.ArrayList;

public class Transform<Key extends Comparable<Key>, Value>{
    private AVLTree<Key,Value> avl = new AVLTree<>();
    private RBTree<Key,Value> rb = new RBTree<>();
    private ArrayList<Key> aKeys = new ArrayList<Key>();
    private ArrayList<Value> aValues = new ArrayList<Value>();
    protected class Node {
        public Key chave;
        public Value valor;
        public Node esq, dir;

        int size;

        Node(Key key, Value value, int size, boolean color) {
            this.chave = key;
            this.valor = value;

            this.size = size;
        }
    }

    public RBTree<Key,Value> receiveRB(RBTree<Key,Value> tree){
        rb = tree;
        return rb;
    }

    private void giveForArrays(RBTree<Key,Value>.Node tree){
        if (tree == null)
            return; 

        aKeys.add((Key) tree.chave);
        aValues.add((Value) tree.valor);
        
        giveForArrays(tree.esq);
        giveForArrays(tree.dir);
    }
    public void giveForArrays(){
        giveForArrays(rb.raiz);
    }

    public void putOnAVL(){
        for (int i = 0; i < rb.raiz.size-1; i++) {
            avl.put(aKeys.get(i), aValues.get(i));
        }
    }
    
    
    //DEBUGS
    // public void showKeysAndValues(){
    //     for(int i = 0; i < aKeys.size(); i++){
    //         System.out.print(aKeys.get(i)+", ");
    //     }
    //     System.out.println("\nValues:");
    //     for(int i = 0; i < aKeys.size(); i++){
    //         System.out.print(aValues.get(i)+", "); 
    //     }            
    // }
    
    // public void print() {
    //     print(rb.raiz, "", 0);
    // }

    // private void print(RBTree.Node no, String prefix, int depth) {
    //     if (no == null) {
    //         return;
    //     }
    //     for (int i = 0; i < no.size; i++) {
    //         System.out.print("  ");
    //     }
    //     if (!prefix.equals("")) {
    //         System.out.print(prefix);
    //         System.out.print(":");
    //     }
    //     System.out.print(no.chave);
    //     System.out.print(" (");
    //     System.out.print("H:");
    //     System.out.print(no.size+1);
    //     System.out.println(")");
    //     print(no.esq, "L", depth + 1);
    //     print(no.dir, "R", depth + 1);
    // }

    // public void printavl() {
    //     this.printavl(avl.raiz, "", 0);
    // }

    // private void printavl(AVLTree.Node node, String prefix, int depth) {
    //     if (node == null) {
    //         return;
    //     }
    //     for (int i = 0; i < depth; i++) {
    //         System.out.print("  ");
    //     }
    //     if (!prefix.equals("")) {
    //         System.out.print(prefix);
    //         System.out.print(":");
    //     }
    //     System.out.print(node.chave);
    //     System.out.print(" (");
    //     System.out.print("H:");
    //     System.out.print(node.altura+1);
    //     System.out.println(")");
    //     printavl(node.esq, "L", depth + 1);
    //     printavl(node.dir, "R", depth + 1);
    // }
}