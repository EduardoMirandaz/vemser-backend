
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Cidade {
    String nome;
    double idh;
    int populacao;
    String principalFesta;

    public Cidade(String nome, double idh, int populacao, String principalFesta) {
        this.nome = nome;
        this.idh = idh;
        this.populacao = populacao;
        this.principalFesta = principalFesta;
    }
}

class Estado {
    String nome;
    List<Cidade> cidades;

    public Estado(String nome, List<Cidade> cidades) {
        this.nome = nome;
        this.cidades = cidades;
    }
}

public class Exercício4 {

    public static void main(String[] args) {
        // Estado 1
        Cidade cachoeiroDeItapemirim = new Cidade("Cachoeiro De Itapemirim", 0.746, 210589, "Exposul");
        Cidade vitoria = new Cidade("Vitoria", 	0.845, 365855, "Festival Frutos do Mar");
        Estado espiritoSanto = new Estado("Espírito Santo", List.of(cachoeiroDeItapemirim, vitoria));
        // Estado 2
        Cidade londrina = new Cidade("Londrina", 0.778, 575733, "Expo Londrina");
        Cidade curitiba = new Cidade("Curitiba", 	0.823, 1776761, "Nossa Senhora do Rocio");
        Estado parana = new Estado("Paraná", List.of(londrina, curitiba));
        // Estado 3
        Cidade saoCarlos = new Cidade("São Carlos", 0.805, 280513, "Tusca");
        Cidade guarulhos = new Cidade("Guarulhos", 	0.763, 1379568, "Nossa Senhora do Rocio");
        Estado saoPaulo = new Estado("São Paulo", List.of(saoCarlos, guarulhos));
        List<Estado> estados = new ArrayList<>();
        estados.add(espiritoSanto);
        estados.add(parana);
        estados.add(saoPaulo);
        System.out.println("Selecione um Estado entre:");
        for (Estado estado : estados) {
            System.out.println(estado.nome);
        }
        Scanner scanner = new Scanner(System.in);
        String escolhaEstado = scanner.nextLine();
        Estado estadoEscolhido = null;
        for (Estado estado : estados) {
            if(estado.nome.equals(escolhaEstado)){
                estadoEscolhido = estado;
                break;
            }
        }
        if(estadoEscolhido == null){
            System.out.println("Escolha inválida!");
            return;
        }
        System.out.println("Escolha uma cidade no estado de "+ escolhaEstado + " dentre essas:");
        for (Cidade cidade : estadoEscolhido.cidades) {
            System.out.println(cidade.nome);
        }

        String escolhaCidade = scanner.nextLine();
        Cidade cidadeEscolhida = null;
        for (Cidade cidade : estadoEscolhido.cidades) {
            if(cidade.nome.equals(escolhaCidade)){
                cidadeEscolhida = cidade;
                break;
            }
        }
        if(cidadeEscolhida == null){
            System.out.println("Escolha inválida!");
            return;
        }
        System.out.println("Características da cidade de " + escolhaCidade);
        System.out.println("IDH :: " + cidadeEscolhida.idh);
        System.out.println("População :: " + cidadeEscolhida.populacao);
        System.out.println("Principal festa :: "+ cidadeEscolhida.principalFesta);
    }
}
