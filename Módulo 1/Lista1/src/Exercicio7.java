public class Exercicio7 {
    public static void main(String[] args) {
        int A = 10;
        int B = 20;
        System.out.println("A->" + A);
        System.out.println("B->" + B);
        int aux = A;
        A = B;
        B = aux;
        System.out.println("A->" + A);
        System.out.println("B->" + B);
    }
}
