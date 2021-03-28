import java.util.Scanner;

public class App {
        public static Integer[] randomVet( int tamanho) {
                Integer[] vetor = new Integer[tamanho];
                for (int i = 0; i < vetor.length; i++) {
                        vetor[i] = (int)Math.round(Math.random() * tamanho);
                }
                return vetor;
        }
        public static void menu(){
                System.out.println("\tTipos de testes");
                System.out.println("0. Fim");
                System.out.println("1. Inserções e comparações das colisões");
                System.out.println("2. Hash com Delete modificado");
                System.out.println("3. Testes Red-Black Tree");
                System.out.println("4. Testes AVL Tree");
                System.out.println("5. Testes Transform RBTree to AVL");
                System.out.println("Opcao:");
        }
    public static void main(String[] args) throws Exception {
        //Instanciando os Hashs
        HashTentativaLinear<Integer,Integer> hashing = new HashTentativaLinear<>();
        HashTentativaLinear<Integer,Integer> hashDuplo = new HashTentativaLinear<>();
        HashWithLinkedList<Integer, Integer> hll = new HashWithLinkedList<>();
        HashWithDeleteMod<Integer, Integer> hdm = new HashWithDeleteMod<>(10);
        HashTentativaLinear<Integer,Integer> hdmComp = new HashTentativaLinear<>(10);

        //Instanciando as árvores
        RBTree<Integer,Integer> rb = new RBTree<>();
        AVLTree<Integer,Integer> avl = new AVLTree<>();
        Transform<Integer,Integer> trans = new Transform<>();
        RBTree<Integer,Integer> rb2 = new RBTree<>();

        long inicio, fim;
        int opcao;
        Scanner in = new Scanner(System.in);
        
        do{
            menu();
            opcao = in.nextInt();
            switch(opcao){
            case 1:
        ////////  //////////////////////
                //Array que recebe os dados aleatórios
                System.out.print("Escolha um tamanho do vetor: ");
                int n = in.nextInt();
                Integer[] testA = (Integer[]) randomVet(n);

                //Inserindo com Hashing linear
                inicio = System.currentTimeMillis();
                for (Integer key : testA) {
                        hashing.put(key,key);
                }
                fim = System.currentTimeMillis();
                String timeHash = String.format("%.3f", ((fim-inicio) /1000d));

                //Inserindo com Hashing duplo
                inicio = System.currentTimeMillis();
                for (Integer key : testA) {
                        hashDuplo.putDuplo(key,key);
                }
                fim = System.currentTimeMillis();
                String timeHashDuplo = String.format("%.3f", ((fim-inicio) /1000d));

                //Inserindo com Hashing encadeado
                inicio = System.currentTimeMillis();
                for (Integer key : testA) {
                        hll.put(key, key);
                }
                fim = System.currentTimeMillis();
                String timeHashEncadeado = String.format("%.3f", ((fim-inicio) /1000d));

                //Comparando o resultado das inserções
                System.out.println("Insert 255000 elementos:");
                System.out.println("Linear \t Hash duplo \t Encadeado");
                System.out.println(timeHash +"ms  "+ timeHashDuplo+"ms  \t "+ timeHashEncadeado+ "ms");
                break;
                
            case 2:
        //////// testes Hash com Delete modificado ///////////////////////////////
                Integer[] testB = {52, 34, 11, 23, 49};
                
                for (Integer key : testB) {
                        hdm.put(key, key);
                        hdmComp.put(key, key);
                }
                hdmComp.print();
                System.out.println("Delete() modificado:");
                hdm.showBooWithKeys();
                
                hdm.delete(23);
                hdmComp.delete(23);
                hdm.delete(34);
                hdmComp.delete(34);
                
                System.out.println("\nApós usar o delete()");
                hdmComp.print();
                System.out.println("Delete() modificado:");
                hdm.showBooWithKeys();
                break;
                
            case 3:
        //////// testes Red-Black Tree ////////////////////////////////////
                // System.out.print("Escolha um tamanho do vetor: ");
                // int n1 = in.nextInt();
                // Integer[] testC = (Integer[]) randomVet(n1);
                // for (Integer key : testC) {
                //         rb.insere(key,key);
                // }
                rb.insere(1, 1);
                rb.insere(2, 2);
                rb.insere(3, 3);
        
                rb.print();
                rb.count();
                break;
                
            case 4:
        //////// testes AVL Tree //////////////////////////////////////
                // System.out.print("Escolha um tamanho do vetor: ");
                // int n2 = in.nextInt();
                // Integer[] testD = (Integer[]) randomVet(n2);
                // for (Integer key : testD) {
                //         avl.put(key, key);
                // }
                avl.put(2, 2);
                avl.put(1,1);
                avl.put(4,4);
                avl.put(5,5);
                avl.put(9,9);
                avl.put(3,3);
                avl.put(6,6);
                avl.put(7,7);
        
                avl.print();
                break;
            case 5:
        //////// testes Transform RBTree to AVL /////////////////////
                System.out.print("Escolha um tamanho do vetor: ");
                int n3 = in.nextInt();
                Integer[] testE = (Integer[]) randomVet(n3);
                for (Integer key : testE) {
                        rb2.insere(key, key);
                }
                rb2.print();
                trans.receiveRB(rb2);
                
                System.out.println("Transformado em Avl");
                trans.printavl();
                break;
            default:
                System.out.println("Opção inválida.");
           }
           in.close();
           System.out.println("Programa finalizado");
        } while(opcao != 0);
    }
}
