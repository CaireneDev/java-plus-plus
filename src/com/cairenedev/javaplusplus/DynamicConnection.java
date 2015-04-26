package com.cairenedev.javaplusplus;

/**
 *  The <tt>DynamicConnection</tt> class represents a union-find data structure.
 *  <p>
 *  This implementation uses weighted quick union by size (without path compression).
 *  
 *  <em>Initialization (size n)</em>
 *  ** Linear (n)
 *  
 *  <em>reset()</em>
 *  ** Linear (n)
 *  
 *  <em>resize(int n)</em>
 *  ** Worst case Linear (n)
 *  
 *  <em>resize(int n, boolean keep)</em>
 *  ** Worst case Linear (n)
 *  
 *  <em>union(int first_element, int second_element)</em>
 *  **  Worst case log(n)
 *  
 *  <em>find(int element)</em>
 *  **  Worst case log(n)
 *  
 *  <em>connected(int first_element, second_element)</em>
 *  **  Worst case log(n)
 *  
 *  <em>connected(int[] elements)</em>
 *  **  Worst case n.log(n)
 *  
 *  <em>size()</em>
 *  ** constant time (returns original components count)
 *  
 *  <em>count()</em>
 *  ** constant time (returns current components count)
 *     
 *  @author Mohamed Osama <mohamed@cairenedev.com>
 */

public class DynamicConnection {
	
	private int[] parents_array;
	private int[] sizes_array;
	private int size;
	private int componentsCount;
	
	public DynamicConnection(int n) {
		resize(n);
	}
	
	public int size() {
		return size;
	}
	
	public int count() {
		return componentsCount;
	}
	
	public int find(int element) {
		if (element < 0 || element >= size) return -1;
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
	
    public void union(int first_element, int second_element) {
        int first_root = find(first_element);
        int second_root = find(second_element);
        if (first_root == second_root) return;
        if (sizes_array[first_root] < sizes_array[second_root]) {
            parents_array[first_root] = second_root;
            sizes_array[second_root] += sizes_array[first_root];
        }
        else {
            parents_array[second_root] = first_root;
            sizes_array[first_root] += sizes_array[second_root];
        }
        componentsCount--;
    }
	
	public void reset() {
		for(int i = 0; i < size; i++) {
			parents_array[i] = i;
			sizes_array[i] = 1;
			this.componentsCount = size;
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
					componentsCount++;
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
