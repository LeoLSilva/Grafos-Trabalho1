package grafos;

import java.util.Collection;
import java.util.HashMap;

public class grafosMetodos {
	textoDebug t = new textoDebug();
	private static int[][] matrizAdj;
	private boolean dirigido;

	public String tipoDoGrafo(int[][] matriz) {
		String texto = "\nGrafo: ";
		matrizAdj = matriz;
		t.mostrarMatriz(matrizAdj);
		texto += (verificarDirigido()) ? "Dirigido, " : "Não dirigido, ";
		texto += (verificarMultigrafo() ? "multigrafo, " : "simples, ");
		texto += (verificarRegular() ? "regular, " : "não regular, ");
		texto += (VerificarCompleto() ? "completo" : "não completo");
		texto += (verificarNulo() ? ", nulo." : "");
		return texto;
	}

	// Veirifica se [i][j] != [j][i], se for é dirigido
	private boolean verificarDirigido() {
		int n = matrizAdj.length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (matrizAdj[i][j] != matrizAdj[j][i]) {
					dirigido = true;
					return true;
				}
			}
		}
		dirigido = false;
		return false;
	}

	// Simples diagonal tem que ser 0 e sem múltiplas arestas, se não Multigrafo
	private boolean verificarMultigrafo() {
		int n = matrizAdj.length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if ((i == j && matrizAdj[i][j] > 0) || matrizAdj[i][j] > 1) {
					return true;
				}
			}
		}
		return false;
	}

	// Só ver se todas são 0, sem ligação ou arestas.
	private boolean verificarNulo() {
		int n = matrizAdj.length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (matrizAdj[i][j] != 0) {
					return false;
				}
			}
		}
		// Se for true é regular
		return true;
	}

	private boolean VerificarCompleto() {
		if (verificarNulo())
			return false;
		int n = matrizAdj.length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == j && matrizAdj[i][j] != 0) {
					return false;
				} else if (i != j && matrizAdj[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}

	// Ver se todas tem o mesmo grau de vertices
	private boolean verificarRegular() {
		int n = matrizAdj.length;
		if (!dirigido) {
			int[] cont = new int[n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					cont[i] += matrizAdj[i][j];
				}
			}
		} else {
			int[] in = new int[n];
			int[] out = new int[n];

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					out[i] += matrizAdj[i][j];
					in[i] += matrizAdj[j][i];
				}
				if (i != 0) {
					if (out[i] != out[0] || in[i] != in[0]) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public String arestasDoGrafo(int[][] matriz) {
		matrizAdj = matriz;
		int n = matriz.length;
		verificarDirigido();
		int cont = 0;
		if (dirigido) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					cont += matrizAdj[i][j];
				}
			}
		} else {
			for (int i = 0; i < n; i++) {
				for (int j = i; j < n; j++) {
					cont += matrizAdj[i][j];
				}
			}
		}
		return "Grafo possui " + cont + " arestas.";
	}

	public String grausDoVertice(int[][] matriz) {
		matrizAdj = matriz;
		verificarDirigido();
		int n = matrizAdj.length;
		int cont = 0;
		String texto = "\nGrau do Grafo: ";
		HashMap<Character, Integer[]> vertices = new HashMap<Character, Integer[]>();
		Integer[] val = null;
		if (!dirigido) {
			for(int i =0; i < n; i++) {
				cont = 0;
				for(int j =0; j < n; j++) {
					if(i == j && matriz[i][j] != 0) cont += matriz[i][j] * 2;
					else if(i != j)
					cont += matriz[i][j];
				}
				val = new Integer[]{0,0,cont};
				vertices.put((char) ('A'+ i), val);
			}
		} else {
			int in = 0;
			int out = 0;
			
			for (int i = 0; i < n; i++) {
				in = 0;
				out = 0;
				for (int j = 0; j < n; j++) {
					out += matrizAdj[i][j];
					in += matrizAdj[j][i];
				}
				val = new Integer[] {out, in, out+in};
				vertices.put((char) ('A'+ i), val);
			}
		}
		int[] seq = vertices.values().stream()
                .mapToInt(v -> v[2])
                .toArray();

		texto += getMaiorGrau(vertices)+"\n";
		texto += "\nVertices e Graus: \n"+t.lerHash(vertices, dirigido);
		texto += "\nSequencia de graus: " + formatarSequencia(seq);
		return texto;
	}
	
	private String formatarSequencia(int[] seq) {
		String t = "";
		bubbleSortSeq(seq);
		for(int i =0; i < seq.length -1;i++) {
			t += seq[i] + ", ";
		}
		t+=seq[seq.length-1]+".";
		return t;
	}
	private int getMaiorGrau(HashMap<Character, Integer[]> v) {
		int maior = -1;
        for (Integer[] valor : v.values()) {
            if (valor[2] > maior) {
                maior = valor[2];
            }
        }
		return maior;
	}
	
	public void bubbleSortSeq(int[] vetor) {
	    int n = vetor.length;
	    boolean trocou;
	    do {
	        trocou = false;
	        for (int i = 0; i < n - 1; i++) {
	            if (vetor[i] > vetor[i + 1]) {
	                int temp = vetor[i];
	                vetor[i] = vetor[i + 1];
	                vetor[i + 1] = temp;
	                trocou = true;
	            }
	        }
	    } while (trocou);
	}
	
}
