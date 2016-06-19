package sorting;
// 数组是空间共享的
public class MergeSorting {
	public static void mergeSort(int[] list){
		if(list.length > 1){
			//Merge sort the first half
			int[] firstHalf = new int[list.length / 2];
			System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
			mergeSort(firstHalf);
			
			//Merge sort the second half
			int[] secondHalf = new int[list.length - list.length / 2];
			System.arraycopy(list, list.length /2 , secondHalf, 0, secondHalf.length);
			mergeSort(secondHalf);
			
			//Merge firstHalf with secondHalf
			int[] temp = merge(firstHalf,secondHalf);
			System.arraycopy(temp, 0, list, 0, temp.length);//传入的list和执行完该步的list已经发生改变
			
		}
	}
	private static int[] merge(int[] list1,int[] list2){
		int[] temp = new int[list1.length + list2.length];
		
		int current1 = 0;//Current index in list1
		int current2 = 0;//Current index in list2
		int current3 = 0;//Current index in temp
		
		while(current1 < list1.length && current2 < list2.length){
			if(list1[current1] < list2[current2])
				temp[current3++] = list1[current1++];
			else
				temp[current3++] = list2[current2++];
		}
		
		while(current1 < list1.length)
			temp[current3++] = list1[current1++];
		
		while(current2 < list2.length)
			temp[current3++] = list2[current2++];
		
		return temp;
	}
	
	public static void main(String[] args) {
//		int[] list = {8,5,1,4,5,6,7,8,2,0};
		int[] list = {4,3,2,1};
		mergeSort(list);
		for(int i = 0; i < list.length; i++)
			System.out.print(list[i]+" ");
	}
	
	
}
