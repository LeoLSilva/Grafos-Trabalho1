package grafos;

public class DFS {

	private int branco = 0;
	private int cinza = 1;
	private int preto = 2;

	private int tempo;
	private int[] cor;
	private int[] d;
	private int[] f;
	private StringBuilder ordemDfs;

	public String buscaEmProfundidade(int[][] matriz) {
		int n = matriz.length;
		cor = new int[n];
		d = new int[n];
		f = new int[n];
		ordemDfs = new StringBuilder();

		for (int u = 0; u < n; u++) {
			cor[u] = branco;
		}
		tempo = 0;

		for (int u = 0; u < n; u++) {
			if (cor[u] == branco) {
				dfsVisit(u, matriz);
			}
		}
		return ordemDfs.toString().trim();
	}

	private void dfsVisit(int u, int[][] matriz) {
		cor[u] = cinza;
		tempo++;
		d[u] = tempo;
		ordemDfs.append(u).append(" ");
		for (int v = 0; v < matriz.length; v++) {
			if (matriz[u][v] == 1 && cor[v] == branco) {
				dfsVisit(v, matriz);
			}
		}

		cor[u] = preto;
		tempo++;
		f[u] = tempo;
		
	}

}
