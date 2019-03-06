import java.util.Arrays;

public class Sort {
	public static void main(String[] args) {
		
		int[] array = new int[] {1,3,7,5,2,6,8,4};
		//bubbleSort(array);
		//quickSort(array, 0, 7);
		//insertSort(array);
			
		//selectSort(array);
			//merge(array, 0,2,6);  int[] array = new int[] {1,3,5,2,4,6,8};
		//mergeSort(array, 0, array.length-1);
		//radixSort(array);
		radixQueueSort(array);
		System.out.println(Arrays.toString(array));
	}
	
	
	/*
	 * 冒泡排序
	 */
	public static void bubbleSort(int[] array) {
		//排序，排arr.length-1次，从0开始
		for (int i = 0; i < array.length-1; i++) {
			//排序过的，再最后面，就不用再排了
			for (int j = 0; j < array.length-1-i; j++) {
				if(array[j] > array[j+1]) {
					int temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
				}
				
			}
		}
	}
	
	/*
	 * 快速排序
	 */
	
	public static void quickSort(int[] array, int start, int end) {
		
		if(start < end) {
			//定义基准数
			int standard = array[start];
			
			int low = start;
			int high = end;
			
			while(low < high) {
				//右边的比标准数大
				while(low < high && standard <= array[high]) {
					high--;
				}
				array[low] = array[high];
				//
				while(low < high && array[low] <= standard) {
					low++;
				}
				array[high] = array[low];
			}
			array[low] = standard;
			
			//处理左边的小的
			quickSort(array, start, low);
			//处理右边的大的
			quickSort(array, low+1, end);
		}
	}
	
	/*
	 * 直接插入排序
	 * 从第二个数开始，往后面比较
	 */
	public static void insertSort(int[] array) {
		for (int i = 1; i < array.length; i++) {
			//如果后一个数比前一个数小  取出
			if(array[i] < array[i-1]) {
				int temp = array[i];
				//往前比较然后插入，让array[j+1] = array[j]
				int j;
				for(j=i-1; j>=0&&array[j]>temp; j--) {
					array[j+1] = array[j];
				}
				array[j+1] = temp;
			}
		}
	}
	/*
	 * 插入排序之希尔排序
	 */
	public static void shellSort(int[] array) {
		
	}
	
	/*
	 * 选择排序
	 */
	public static void selectSort(int[] array) {
		for (int i = 0; i < array.length; i++) {
			int minIndex = i;
			for (int j = i+1; j < array.length; j++) {
				//如果后面的小于array[minIndex]，则minIndex = j
				if(array[j] < array[minIndex]) {
					minIndex = j;
				}
			}
			//i!=minIndex 即后面有小于array[i]的，把后面的换到array[i]
			if(i!=minIndex) {
				int temp = array[i];
				array[i] = array[minIndex];
				array[minIndex] = temp;
			}
		}
	}
	
	
	/*
	 * 归并(数组里array[i]到array[middle]是升序的，array[middle+1]到array[high]是升序的)
	 * [1,3,5,7,2,4,6,8]
	 */
	public static void merge(int[] array, int low, int middle, int high) {
		int[] temp = new int[high - low + 1];
		int i = low;
		int j = middle + 1;
		int index= 0 ;
		while(i <= middle && j <=high) {
			if(array[i] < array[j]) {
				temp[index] = array[i];
				i++;
			} else {
				temp[index] = array[j];
				j++;
			}
			index++;
		}
		//如果i少j多，
		while(j <= high) {
			temp[index] = array[j];
			j++;
			index++;
		}
		//如果j少i多
		while(i <= middle) {
			temp[index] = array[i];
			i++;
			index++;
		}
		for (int k = 0; k < temp.length; k++) {
			//array[k+low]这个地方极容易出错！！！！！
			//low是数组开始的地方的下标，当然归并后还是从这个地方放回去
			array[k+low] = temp[k];
		}
	}
	
	/*
	 * 归并排序(乱序，需要先把数组划分成左右两边都有序的数组，再进行归并，即先进行多次递归)
	 */
	public static void mergeSort(int[] array, int low, int high) {
		int middle = (low + high)/2;
		//递归条件，每个递归都有
		if(low < high) {
			//左边进行升序
			mergeSort(array, low, middle);
			//右边进行升序
			mergeSort(array, middle+1, high);
			//再进行归并
			merge(array, low, middle, high);
		}
	}
	
	/*
	 * 基数排序
	 */
	public static void radixSort(int[] array) {
		//先找出数组的最大值
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < array.length; i++) {
			if(max <= array[i]) {
				max = array[i];
			}
		}
		//计算最大值的位数，这个位数决定后来将要循环放桶里几次
		int length = (max + "").length();
		//用二维数组存array[i]
		int[][] temp = new int[10][array.length];
		//counts计数每个桶子里有几个数
		int[] counts = new int[10];
		
		for (int i=0,n=1; i < length; i++,n*=10) {
			
			for (int j = 0; j < array.length; j++) {
				//计算余数
				int yushu = array[j]/n%10;
				//开始往桶里放
				temp[yushu][counts[yushu]] = array[j];
				counts[yushu]++;
			}
			
			//从桶里取出来
			int index = 0;
			for (int j = 0; j < counts.length; j++) {
				if(counts[j] != 0) {
					for (int k = 0; k < counts[j]; k++) {
						  array[index] = temp[j][k];
						  index++;
					}
				}
				counts[j] = 0;
			}
		}
	}
	
	/*
	 * 基数排序 用队列实现
	 */
	
	public static void radixQueueSort(int[] array) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < array.length; i++) {
			if(max <= array[i]) {
				max = array[i];
			}
		}
		//获得最大数的位数，确定遍历几遍
		int length = (max +"").length();
		
		//创建队列数组
		MyQueue[] temp = new MyQueue[10];
		
		//为队列赋初始值
		for (int i = 0; i < temp.length; i++) {
			temp[i] = new MyQueue();
		}
		for(int i=0,n=1;i<length;i++,n*=10) {
			//获得余数
			int ys = array[i]/n%10;
			
			//把array[i]放到桶子里，桶子是队列
			temp[ys].add(array[i]);
			
			//把所有的队列里面的元素取出来
			int index = 0;
			for (int j = 0; j < temp.length; j++) {
				while(!temp[j].isEmpty()) {
					//取出
					array[index] = temp[j].poll();
					index++;
				}
			}
		}
	}
}
