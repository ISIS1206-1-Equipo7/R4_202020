package model.data_structures;

import java.util.Random;
import model.logic.Station;

public class Quicksort {
	
	/**
	 * Ordena el arreglo descendentemente por numero de inDegree
	 * @param pArray el arreglo a ordenar
	 * @param start el indice del comienzo
	 * @param end el indice del final
	 */
	public void byInDegree(Vertex<String, Station>[] pArray, int start, int end){
		if (start >= end) return;

		Random rand = new Random();

		int pivotIdx = rand.nextInt(end - start) + start;
		int pivotElem = pArray[pivotIdx].indegree();

		swap(pArray, pivotIdx, end);

		int pointer = start;

		for (int i = start; i < end; i++){
			if (pArray[i].indegree() > pivotElem){
				swap(pArray, i, pointer);
				pointer ++;
			}
		}

		swap(pArray, pointer, end);

		byInDegree(pArray, start, pointer-1);
		byInDegree(pArray, pointer+1, end);
	}
	
	/**
	 * Ordena el arreglo descendentemente por numero de outDegree
	 * @param pArray el arreglo a ordenar
	 * @param start el indice del comienzo
	 * @param end el indice del final
	 */
	public void byOutDegree(Vertex<String, Station>[] pArray, int start, int end){
		if (start >= end) return;

		Random rand = new Random();

		int pivotIdx = rand.nextInt(end - start) + start;
		int pivotElem = pArray[pivotIdx].outdegree();

		swap(pArray, pivotIdx, end);

		int pointer = start;

		for (int i = start; i < end; i++){
			if (pArray[i].outdegree() > pivotElem){
				swap(pArray, i, pointer);
				pointer ++;
			}
		}

		swap(pArray, pointer, end);

		byOutDegree(pArray, start, pointer-1);
		byOutDegree(pArray, pointer+1, end);
	}
	
	/**
	 * Ordena el arreglo ascendentemente por suma de inDegree + outDegree
	 * @param pArray el arreglo a ordenar
	 * @param start el indice del comienzo
	 * @param end el indice del final
	 */
	public void bySumDegree(Vertex<String, Station>[] pArray, int start, int end){
		if (start >= end) return;

		Random rand = new Random();

		int pivotIdx = rand.nextInt(end - start) + start;
		int pivotElem = pArray[pivotIdx].indegree() + pArray[pivotIdx].outdegree();

		swap(pArray, pivotIdx, end);

		int pointer = start;
		
		int elem;
		for (int i = start; i < end; i++){
			elem = pArray[i].indegree() + pArray[i].outdegree();
			if (elem < pivotElem){
				swap(pArray, i, pointer);
				pointer ++;
			}
		}

		swap(pArray, pointer, end);

		bySumDegree(pArray, start, pointer-1);
		bySumDegree(pArray, pointer+1, end);
	}
	
	/**
	* Funcion auxiliar
	*/
	public void swap(Vertex<String, Station>[] pArray, int idx1, int idx2){
		Vertex<String, Station> aux = pArray[idx1];
		pArray[idx1] = pArray[idx2];
		pArray[idx2] = aux;
	}
}


















