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
	 * ð������
	 */
	public static void bubbleSort(int[] array) {
		//������arr.length-1�Σ���0��ʼ
		for (int i = 0; i < array.length-1; i++) {
			//������ģ�������棬�Ͳ���������
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
	 * ��������
	 */
	
	public static void quickSort(int[] array, int start, int end) {
		
		if(start < end) {
			//�����׼��
			int standard = array[start];
			
			int low = start;
			int high = end;
			
			while(low < high) {
				//�ұߵıȱ�׼����
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
			
			//������ߵ�С��
			quickSort(array, start, low);
			//�����ұߵĴ��
			quickSort(array, low+1, end);
		}
	}
	
	/*
	 * ֱ�Ӳ�������
	 * �ӵڶ�������ʼ��������Ƚ�
	 */
	public static void insertSort(int[] array) {
		for (int i = 1; i < array.length; i++) {
			//�����һ������ǰһ����С  ȡ��
			if(array[i] < array[i-1]) {
				int temp = array[i];
				//��ǰ�Ƚ�Ȼ����룬��array[j+1] = array[j]
				int j;
				for(j=i-1; j>=0&&array[j]>temp; j--) {
					array[j+1] = array[j];
				}
				array[j+1] = temp;
			}
		}
	}
	/*
	 * ��������֮ϣ������
	 */
	public static void shellSort(int[] array) {
		
	}
	
	/*
	 * ѡ������
	 */
	public static void selectSort(int[] array) {
		for (int i = 0; i < array.length; i++) {
			int minIndex = i;
			for (int j = i+1; j < array.length; j++) {
				//��������С��array[minIndex]����minIndex = j
				if(array[j] < array[minIndex]) {
					minIndex = j;
				}
			}
			//i!=minIndex ��������С��array[i]�ģ��Ѻ���Ļ���array[i]
			if(i!=minIndex) {
				int temp = array[i];
				array[i] = array[minIndex];
				array[minIndex] = temp;
			}
		}
	}
	
	
	/*
	 * �鲢(������array[i]��array[middle]������ģ�array[middle+1]��array[high]�������)
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
		//���i��j�࣬
		while(j <= high) {
			temp[index] = array[j];
			j++;
			index++;
		}
		//���j��i��
		while(i <= middle) {
			temp[index] = array[i];
			i++;
			index++;
		}
		for (int k = 0; k < temp.length; k++) {
			//array[k+low]����ط������׳�����������
			//low�����鿪ʼ�ĵط����±꣬��Ȼ�鲢���Ǵ�����ط��Ż�ȥ
			array[k+low] = temp[k];
		}
	}
	
	/*
	 * �鲢����(������Ҫ�Ȱ����黮�ֳ��������߶���������飬�ٽ��й鲢�����Ƚ��ж�εݹ�)
	 */
	public static void mergeSort(int[] array, int low, int high) {
		int middle = (low + high)/2;
		//�ݹ�������ÿ���ݹ鶼��
		if(low < high) {
			//��߽�������
			mergeSort(array, low, middle);
			//�ұ߽�������
			mergeSort(array, middle+1, high);
			//�ٽ��й鲢
			merge(array, low, middle, high);
		}
	}
	
	/*
	 * ��������
	 */
	public static void radixSort(int[] array) {
		//���ҳ���������ֵ
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < array.length; i++) {
			if(max <= array[i]) {
				max = array[i];
			}
		}
		//�������ֵ��λ�������λ������������Ҫѭ����Ͱ�Ｘ��
		int length = (max + "").length();
		//�ö�ά�����array[i]
		int[][] temp = new int[10][array.length];
		//counts����ÿ��Ͱ�����м�����
		int[] counts = new int[10];
		
		for (int i=0,n=1; i < length; i++,n*=10) {
			
			for (int j = 0; j < array.length; j++) {
				//��������
				int yushu = array[j]/n%10;
				//��ʼ��Ͱ���
				temp[yushu][counts[yushu]] = array[j];
				counts[yushu]++;
			}
			
			//��Ͱ��ȡ����
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
	 * �������� �ö���ʵ��
	 */
	
	public static void radixQueueSort(int[] array) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < array.length; i++) {
			if(max <= array[i]) {
				max = array[i];
			}
		}
		//����������λ����ȷ����������
		int length = (max +"").length();
		
		//������������
		MyQueue[] temp = new MyQueue[10];
		
		//Ϊ���и���ʼֵ
		for (int i = 0; i < temp.length; i++) {
			temp[i] = new MyQueue();
		}
		for(int i=0,n=1;i<length;i++,n*=10) {
			//�������
			int ys = array[i]/n%10;
			
			//��array[i]�ŵ�Ͱ���Ͱ���Ƕ���
			temp[ys].add(array[i]);
			
			//�����еĶ��������Ԫ��ȡ����
			int index = 0;
			for (int j = 0; j < temp.length; j++) {
				while(!temp[j].isEmpty()) {
					//ȡ��
					array[index] = temp[j].poll();
					index++;
				}
			}
		}
	}
}
