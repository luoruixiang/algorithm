package algo.sort;

import java.util.Arrays;

public class Mergesort {

    static int count = 0;

    public static void main(String[] args){
        Mergesort mergesort = new Mergesort();
        int[] A = {1,2,0,3};
        mergesort.isIdealPermutation(A);
    }

    public boolean isIdealPermutation(int[] A) {
        int local = 0;
        for(int i = 0; i < A.length - 1; i++){
            if(A[i] > A[i + 1]){
                local++;
            }
        }
        mergeSort(A, 0, A.length - 1, new int[A.length]);
        return local == count;
    }

    private void mergeSort(int[] array, int start, int end, int[] aux){
        if(start >= end){
            return;
        }
        int mid = (start + end) >> 1;
        mergeSort(array, start, mid, aux);
        mergeSort(array, mid + 1, end, aux);
        merge(array, start, mid, end, aux);
    }

    private void merge(int[] array, int start, int mid, int end, int[] aux) {
        for(int i = start; i <= end; i++){
            aux[i] = array[i];
        }
        int low = start;
        int high = mid + 1;
        int index = start;
        while(index <= end){
            if(low > mid){
                while(high <= end){
                    array[index++] = aux[high++];
                }
            }
            else if(high > end){
                while(low <= mid){
                    array[index++] = aux[low++];
                }
            }
            else if(aux[low] <= aux[high]){
                array[index++] = aux[low++];
            }
            else if(aux[low] > aux[high]){
                count += mid - low + 1;
                array[index++] = aux[high++];
            }
        }
    }


}
