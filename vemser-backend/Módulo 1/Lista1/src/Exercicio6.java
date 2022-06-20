import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Palavra {
    boolean isPt;
    String en;
    String pt;

    public Palavra(String en, String pt) {
        this.isPt = true;
        this.en = en;
        this.pt = pt;
    }
}

public class Exercicio6 {

    public static void main(String[] args) {
        Palavra Cachorro = new Palavra("Dog", "Cachorro");
        Palavra Tempo = new Palavra("Time", "Tempo");
        Palavra Amor = new Palavra("Love", "Amor");
        Palavra Cidade = new Palavra("City", "Cidade");
        Palavra Feliz = new Palavra("Happy", "Feliz");
        Palavra Deveria = new Palavra("Should", "Deveria");
        Palavra Poderia = new Palavra("Could", "Poderia");

        List<Palavra> listaDePalavras = new ArrayList<>();
        listaDePalavras.add(Cachorro);
        listaDePalavras.add(Tempo);
        listaDePalavras.add(Amor);
        listaDePalavras.add(Cidade);
        listaDePalavras.add(Feliz);
        listaDePalavras.add(Deveria);
        listaDePalavras.add(Poderia);

        System.out.println("Insira a palavra a ser traduzida:");
        Scanner scanner = new Scanner(System.in);
        String palavraASerTraduzida = scanner.nextLine();
        boolean encontrei = false;
        for(Palavra palavra : listaDePalavras){
            if(palavra.pt.equals(palavraASerTraduzida) || palavra.en.equals(palavraASerTraduzida)){
                encontrei = true;
                if(palavra.pt.equals(palavraASerTraduzida)){
                    System.out.println(" A tradução de "+ palavraASerTraduzida + " para inglês é: "+ palavra.en);
                }
                else{
                    System.out.println(" A tradução de "+ palavraASerTraduzida + " para português é: "+ palavra.pt);
                }
            }
        }
        if(!encontrei){
            System.out.println("Essa palavra não é válida");
        }
    }
}
