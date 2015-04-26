package com.cairenedev.javaplusplus;

public class DynamicConnection {
	
	private int[] parents_array;
	private int[] sizes_array;
	private int size;
	
	public DynamicConnection(int n) {
		resize(n);
	}
	
	public int size() {
		return size;
	}
	
	public int count() {
		return size;
	}
	
	public int find(int element) {
		while(element != parents_array[element]) element = parents_array[element];
		return element;
	}
	
	public boolean connected(int first_element, int second_element) {
		return find(first_element) == find(second_element);
	}
	
	public boolean connected(int[] elements) {
		for(int i = 0; i < elements.length - 1; i++) {
			if (!(connected(elements[i], elements[i + 1]))) return false;
		}
		return true;
	}
	
	public void reset() {
		for(int i = 0; i < size; i++) {
			parents_array[i] = i;
			sizes_array[i] = 1;
		}
	}
	
	public void resize(int n) {
		this.size = n;
		initializeArrays();
		reset();
	}
	
	public void resize(int n, boolean keep) {
		/* 
		 * Handles only enlarging, if you tried to reduce the array it will be reset!
		 */
		if(keep && (n == size)) return;
		if (keep && (n > size)) {
			this.size = n;
			int[] parents = new int[size];
			int[] sizes = new int[size];
			for(int i = 0; i < size; i++) {
				if(i < parents_array.length) {
					parents[i] = parents_array[i];
					sizes[i] = sizes_array[i];
				}
				else {
					parents[i] = i;
					sizes[i] = 1;
				}
			}
			initializeArrays(parents, sizes);
		}
		else resize(n);
	}
	
	public void initializeArrays() {
		this.parents_array = new int[size];
		this.sizes_array = new int[size];
	}
	
	public void initializeArrays(int[] parents, int[] sizes) {
		this.parents_array = new int[size];
		this.sizes_array = new int[size];
		for(int i = 0; i < size; i++) {
			parents_array[i] = parents[i];
			sizes_array[i] = sizes[i];
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
