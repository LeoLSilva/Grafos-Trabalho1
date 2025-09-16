package grafos;

public class Main {
	/* Alunos: Leonardo Silva e Gabriel Utyama
	/*private static int[][] matrizAdj = { { 0, 1, 1, 0 }, // A
										 { 1, 0, 0, 1 }, // B
										 { 1, 0, 0, 1 }, // C
										 { 0, 1, 1, 0 } // D
	};*/

	private static int[][] matrizAdj = {
            {0, 1, 0, 0},
            {0, 0, 1, 1},
            {0, 0, 0, 0},
            {0, 0, 1, 0}
        };
	
	 public static void main(String[] args) {
		 textoDebug texto = new textoDebug();
		 grafosMetodos g = new grafosMetodos();
		 DFS dfs = new DFS();
		 //texto.printLn(g.tipoDoGrafo(matrizAdj));
		 //texto.printLn(g.arestasDoGrafo(matrizAdj));
		 //texto.printLn(g.grausDoVertice(matrizAdj));
		 texto.printLn(dfs.buscaEmProfundidade(matrizAdj));
	 }
}
