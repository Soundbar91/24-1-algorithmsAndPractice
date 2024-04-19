package algorithm.sort;

import java.util.Collections;
import java.util.LinkedList;

public class BucketSort {
    public static void main(String[] args) {

        double[] arr = new double[30];
        for(int i=0; i < arr.length; i++){
            arr[i] = Math.round(Math.random() * 100) / 100.0;
        }

        bucketSort(arr);

        for(int i=0; i < arr.length; i++){
            System.out.print(arr[i] + ", ");
        }
    }

    public static void bucketSort(double[] arr){
        int n = arr.length;
        if(n == 0) return;

        LinkedList<Double>[] bucket = new LinkedList[n];

        for(int i=0; i < n; i++){
            bucket[i] = new LinkedList<>();
        }

        for(int i=0; i < n; i++){
            int idx = (int)(arr[i] * n);
            bucket[idx].add(arr[i]);
        }

        for(int i=0; i < n; i++){
            Collections.sort(bucket[i]);
        }

        int index = 0;
        for(int i=0; i < n; i++){
            for(int j=0; j < bucket[i].size(); j++){
                arr[index++] = bucket[i].get(j);
            }
        }
    }
}