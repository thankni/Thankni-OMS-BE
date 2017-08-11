package com.common;

public class Demo{
	public static void main(String[] args){
		int[] arr = {2,7,2,1,5,75,3,9};
		sort1(arr);
		print(arr);
		
	}

	/**
		升序的排列  冒泡排序:相邻两个元素依次比较交换
	*/
	public static void sort(int[] arr ){
	
		int temp = 0;
		for(int j=arr.length;j>0;j--){
			for(int i=1;i<j;i++){
				if(arr[i-1]>arr[i]){
					temp = arr[i-1];
					arr[i-1] = arr[i];
					arr[i] = temp;
				}
			}
		}
		
	}

	/**
			选择排序法：选定某个元素跟其后面的元素依次比较交换
		*/
	public static void sort1(int[] arr){
		int temp = 0;
		for(int j=0;j<arr.length;j++){
			for(int i=j+1;i<arr.length;i++){
				if(arr[j]>arr[i]){
					temp = arr[j];
					arr[j] = arr[i];
					arr[i] = temp;
				}
			}
		}
		
	}

	/**
		插入排序：
		假设被标记元素左边都是有序的，用被标记元素与其左边的元素从右向左开始依次比较，
		比较的元素如果比被标记位大就把这个元素右移，直到比较的元素小于标记元素，把标记元素放入与其比较的
		元素右边的空位上。然后被标记元素下标右移一位。
	*/	
	public static void sort2(int[] arr){
	/*	for(int i = 1;i <arr.length;i++){
			for(int j=i-1;j>0;j--){
				if(){}
			}
		}*/
		
	}

	public static void print(int... arr){
		for(int x:arr){
			System.out.print(x+",");
		}
		System.out.println();
	}

	public static int binarySearch(int[] arr,int fromIndex,int toIndex,int key){
		/**
			二分法查找：对有序数组，从最中间的元素开始查找key
		*/
		int low=fromIndex;
		int high= toIndex;
		while(low<=high){
			int mid=(low+high)>>>1;
			int midval = arr[mid];
			if(key>midval){
				low=mid+1;
			}else if(key<midval){
				high=mid-1;
			}else{
				return mid;
			}
		}
		return -1;
	}
	
}