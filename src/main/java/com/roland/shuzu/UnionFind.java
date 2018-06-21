package com.roland.shuzu;

public class UnionFind {

    private int[] elements;
    private int[] size;
    private int count = 0;

    public UnionFind(int N){
        count = N;
        elements = new int[N];
        for(int i = 0; i < N; i++){
            elements[i] = i;
        }
        size = new int[N];
        for(int i = 0; i < N; i++){
            size[i] = 1;
        }
    }

    public void union(int p, int q){
        int pRoot = find(p);
        int qRoot = find(q);
        if(pRoot != qRoot){
            if(size[pRoot] > size[qRoot]){
                elements[qRoot] = pRoot;
                size[pRoot] += size[qRoot];
            }
            else{
                elements[pRoot] = qRoot;
                size[qRoot] += size[pRoot];
            }
        }
        count--;
    }

    public int find(int p){
        while(elements[p] != p){
            p = elements[p];
        }
        return p;
    }

    public boolean connected(int p, int q){
        return find(p) == find(q);
    }

    public int count(){
        return count;
    }
}
