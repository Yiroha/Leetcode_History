package com.per.iroha.imsystem;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class A {

    //快排
    public static void quickSort(int[] array, int low, int high){
        if(low >= high){
            return;
        }
        int l = low;
        int h = high;
        int flag = array[low];
        while(l < h){
            while(array[h] >= flag && h > l){
                h--;
            }
            while(array[l] <= flag && h > l){
                l++;
            }
            int t = array[h];
            array[h] = array[l];
            array[l] = t;
        }
        array[low] = array[l];
        array[l] = flag;

        quickSort(array,l + 1, high);
        quickSort(array,low,high - 1);
    }

    //二分
    public static int binarySearch(int[] array, int tar){
        int l = 0;
        int h = array.length - 1;
        int mid;
        while(l < h){
            mid = (h - l) / 2 + l;
            if(tar == array[mid]){
                return mid;
            }else if(tar > array[mid]){
                l = mid;
            }else{
                h = mid;
            }
        }
        return -1;
    }

    //快速搜索
    public static int quickSelect(int[] array,int k){
        if(array == null || k < 0 || k > array.length){
            return 0;
        }
        int left = 0;
        int right = array.length - 1;
        while(true){
            int pos = postion(array,left,right);
            if(pos == k - 1){
                return array[pos];
            }else if(pos > k - 1){
                right = pos -1;
            }else{
                left = pos + 1;
            }
        }
    }

    private static int postion(int[] array, int low, int high){
        int l = low;
        int h = high;
        int flag = array[low];
        while(l < h){
            while(array[h] >= flag && h > l){
                h--;
            }
            while(array[l] <= flag && h > l){
                l++;
            }
            int temp = array[l];
            array[l] = array[h];
            array[h] = temp;
        }
        array[low] = array[l];
        array[l] = flag;
        return l;
    }

    //冒泡
    public static void bubboSort(int[] array){
        for(int i = 0; i < array.length - 1; i++){
            for(int j = 0; j < array.length - i - 1; j++){
                if(array[j] > array[j + 1]){
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    //堆排序
    public static void heapSort(int[] array){
        for(int j = array.length / 2; j >= 0;j--){
            adjustHeap(array,j,array.length);
        }

        for(int i = array.length - 1; i > 0; i--){
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            adjustHeap(array,0,i);
        }
    }

    private static void adjustHeap(int[] array, int index , int length){
        int parent = index;
        int child = parent * 2 + 1;
        int temp = array[index];
        while(child < length){
            if(child + 1 < length && array[child] < array[child + 1]){
                child++;
            }
            if(temp >= array[child]){
                break;
            }
            array[parent] = array[child];
            parent = child;
            child = parent * 2 + 1;
        }
        array[parent] = temp;
    }


    public static void main(String[] args){
        int[] a = { 49, 38, 65, 97, 76, 13, 27, 50 };
//        quickSort(a,0,a.length - 1);
//        bubboSort(a);
        heapSort(a);
        for(int i:a){
            System.out.print(i+" ");
        }
//        System.out.println(binarySearch(a,27));
//        System.out.println(quickSelect(a,1));
    }
}
