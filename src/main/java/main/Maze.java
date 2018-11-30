package main;

import java.util.ArrayList;
import java.util.Random;

public class Maze {
	static double taxaCrossover = 0.9;
	static double taxaMutacao = 0.9;
	static int cromossomoSize = 6;
	static int tamPopulacao = 4000;

	public static void main(String[] args) {
		/*
		 * Variaveis do sistema genetico -> Cromossomo (com genes) - Tamanho do
		 * cromossomo, 0 e 1 Tamanho da Populacao (4000x4000) Taxa de mutacao Taxa de
		 * crossover
		 */
		int cromossomoSize = 6;
		// Inicializa a população
		Cromossomo[][] populacao = new Cromossomo[tamPopulacao][tamPopulacao];
		for (int x = 0; x < tamPopulacao; x++) {
			for (int y = 0; y < tamPopulacao; y++) {
				populacao[x][y] = new Cromossomo(cromossomoSize);
			}
		}
		System.out.println(populacao);
		avaliaPopulacao(populacao);
		Cromossomo[][] novaPopulacao = crossoverPopulation(populacao);
		avaliaPopulacao(novaPopulacao);
		System.out.println(novaPopulacao);

	}
	
	public static void avaliaPopulacao(Cromossomo[][] populacao) {
		for (int x = 0; x < tamPopulacao; x++) {
			for (int y = 0; y < tamPopulacao; y++) {
				populacao[x][y].setAvaliacao();
			}
		}
	}

	public static Cromossomo[][] crossoverPopulation(Cromossomo[][] population) {
		Cromossomo[][] novaPopulacao = new Cromossomo[tamPopulacao][tamPopulacao];
		Random random = new Random();
		ArrayList<Cromossomo> parentes = new ArrayList<Cromossomo>();
		int n1; int n2;
		for (int x = 0; x < tamPopulacao; x++) {
			for (int y = 0; y < tamPopulacao; y++) {
				for (int i=0;i<4;i++) {
					n1 = random.nextInt(tamPopulacao);
					n2 = random.nextInt(tamPopulacao);
					parentes.add(population[n1][n2]);
				}
				
				if (parentes.get(0).getAvaliacao() >= parentes.get(1).getAvaliacao()) {
					parentes.remove(1);
				} else {
					parentes.remove(0);
				}
				if (parentes.get(1).getAvaliacao() >= parentes.get(2).getAvaliacao()) {
					parentes.remove(1);
				} else {
					parentes.remove(2);
				}
				// Utiliza Crossover
				if (taxaCrossover > Math.random()){//&& populationIndex >= this.elitismCount) {
					Cromossomo novoCromossomo = new Cromossomo(cromossomoSize);
					// Pega um ponto de corte aleatorio
					int swapPoint = cromossomoSize/2;
	
					for (int geneIndex = 0; geneIndex < cromossomoSize; geneIndex++) {
						if (geneIndex < swapPoint) {
							novoCromossomo.setGene(geneIndex, parentes.get(0).getGene((geneIndex)));
						} else {
							novoCromossomo.setGene(geneIndex, parentes.get(1).getGene((geneIndex)));
						}
					}
					
					for (int i=0;i<novoCromossomo.getChromosome().length; i++){
						if (taxaMutacao > Math.random()) {
							if (0.5 < Math.random()) {
								novoCromossomo.setGene(i, 1);
							} else {
								novoCromossomo.setGene(i, 0);
							}
						}
					}
					novaPopulacao[x][y] = novoCromossomo;
				} else {
					// Não utiliza Crossover
					Cromossomo cromossomo= population[x][y];
					for (int i=0;i<cromossomo.getChromosome().length; i++){
						if (taxaMutacao > Math.random()) {
							if (0.5 < Math.random()) {
								cromossomo.setGene(i, 1);
							} else {
								cromossomo.setGene(i, 0);
							}
						}
					}
					novaPopulacao[x][y] = cromossomo;
				}			
				parentes.clear();
				System.out.println("X: " + x + "Y: " + y);
			}
		}
		return novaPopulacao;
	}
}
