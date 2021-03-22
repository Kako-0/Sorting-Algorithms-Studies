public class App {
    public static void main(String[] args) throws Exception {
        // HashTentativaLinear hashing = new HashTentativaLinear<>(8);

        // hashing.putDuplo(1, 1);
        // hashing.putDuplo(8, 2);
        // hashing.putDuplo(3, 3);
        // hashing.putDuplo(64, 4);
        // hashing.putDuplo(2, 5);
        

        // System.out.println(hashing.get(1));
        // for (int i = 0; i < hashing.keys.length; i++) {
        //     System.out.println(i+": "+hashing.keys[i]);
        // }
        // System.out.println("\n");
        // for (int i = 0; i < hashing.vals.length; i++) {
        //     System.out.println(i+": "+hashing.vals[i]);
        // }
        // int i = 2;
        // int h1 = hashing.hash(i);
        // int h2 = hashing.hash2(i) + 1;
        // int h = (h1 + 1 * h2) % 8;
        // //Incrementa + 1 caso o segundo hashing for par
        // int k = (h2 % 2 == 0) ? h2 + 1 : h2;
        
        // System.out.println(h1);
        // System.out.println(k);
        // System.out.println(h);

        RBTree rb = new RBTree<>();
        rb.insere(2, 2);
        rb.insere(1,1);
        rb.insere(4,4);
        rb.insere(5,5);
        rb.insere(9,9);
        rb.insere(3,3);
        rb.insere(6,6);
        rb.insere(7,7);

        //rb.print();
        //rb.count();
        
        // AVLTree avl = new AVLTree<>();
        // avl.put(2, 2);
        // avl.put(1,1);
        // avl.put(4,4);
        // avl.put(5,5);
        // avl.put(9,9);
        // avl.put(3,3);
        // avl.put(6,6);
        // avl.put(7,7);

        // avl.print();

        Transform trans = new Transform<>();
        trans.receiveRB(rb);
        trans.giveForArrays();
        trans.putOnAVL();

        //trans.printavl();

    }
}
