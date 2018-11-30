package main;

import java.util.Comparator;

public class Cromossomo {
	private int[] chromosome;
	private int avalicao;
	
	public Cromossomo(int chromosomeLength) {
		this.chromosome = new int[chromosomeLength];
		for (int gene = 0; gene < chromosomeLength; gene++) {
			if (0.5 < Math.random()) {
				this.setGene(gene, 1);
			} else {
				this.setGene(gene, 0);
			}
		}
	}
	
	public int[] getChromosome() {
		return this.chromosome;
	}
	public void setGene(int gene, int i) {
		this.chromosome[gene] = i;
	}

	public int getGene(int genePos) {
		return chromosome[genePos];
	}
	
	public void setAvaliacao(){
		int soma = 0;
		for (int i=0;i<chromosome.length;i++) {
			if (chromosome[i] == 1) {
				soma++;
			}
		}
		this.avalicao = soma;
	}
	
	public int getAvaliacao() {
		return this.avalicao;
	}
}

