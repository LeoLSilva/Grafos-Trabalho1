package grafos;

import java.util.HashMap;
import java.util.Map;

public class textoDebug {

	private boolean ativo = true;
	
	public <T> void printLn(T t) {
		System.out.println(t);
	}
	public void LerVetor(int[] vetor) {
		for (int i =0; i < vetor.length - 1; i++) {
			System.out.print(vetor[i]+", ");
		}
		System.out.println(vetor[vetor.length-1]);
	}
	
	public void mostrarMatriz(int[][] matrizAdj) {
	    int n = matrizAdj.length;
	    char[] vertices = new char[n];
	    for (int i = 0; i < n; i++) {
	        vertices[i] = (char) ('A' + i);
	    }

	    // Cabeçalho
	    System.out.print("  ");
	    for (int i = 0; i < n; i++) {
	        System.out.print(vertices[i] + " ");
	    }
	    System.out.println();

	    // Linhas
	    for (int i = 0; i < n; i++) {
	        System.out.print(vertices[i] + " ");
	        for (int j = 0; j < n; j++) {
	            System.out.print(matrizAdj[i][j] + " ");
	        }
	        System.out.println();
	    }
	}
	
	 public static void desenharGrafo(int[][] matriz) {
	        int n = matriz.length;
	        char[] vertices = new char[n];
	        for (int i = 0; i < n; i++) {
	            vertices[i] = (char) ('A' + i);
	        }
	        System.out.println("Representação do Grafo:");
	        for (int i = 0; i < n; i++) {
	            System.out.print(vertices[i] + " -> ");
	            for (int j = 0; j < n; j++) {
	                if (matriz[i][j] == 1) {
	                    System.out.print(vertices[j] + " ");
	                }
	            }
	            System.out.println();
	        }
	    }
	 
	 public String lerHash(HashMap<Character, Integer[]> mapa, boolean dirigido) {
	        StringBuilder sb = new StringBuilder();
	        if(!dirigido) {
	        	
	        for (Map.Entry<Character, Integer[]> entry : mapa.entrySet()) {
	            sb.append("Chave: ").append(entry.getKey())
	              .append(" | Valor: ").append(entry.getValue()[2])
	              .append("\n");
	        }
		}else {
			for (Map.Entry<Character, Integer[]> entry : mapa.entrySet()) {
	            sb.append("Chave: ").append(entry.getKey())
	            	.append(" | Out: ").append(entry.getValue()[0])
	            	.append(" | In: ").append(entry.getValue()[1])
	            	.append(" | Total: ").append(entry.getValue()[2])
	            	.append("\n");
	        }
		}
	        return sb.toString();
	    }
}
