import java.util.concurrent.CountDownLatch;

public class QuickSort implements Runnable {

    int[] arr;
    long time1, time2;
    int begin, end;
    CountDownLatch mergeSignal;

    QuickSort(int data[], int left, int right, CountDownLatch mergeSignal) {
        this.arr = data;
        this.begin = left;
        this.end = right;
        this.mergeSignal = mergeSignal;
    }

    public void quicksort(int[] data, int left, int right) {

        if (right >= left) {
            arr = data;
            int r = partition(arr, left, right);
            quicksort(arr, left, r - 1); // 重复调用，对左半部分数组进行排序
            quicksort(arr, r + 1, right); // 对右半部分数组进行排序
        }
    }

    public static int partition(int[] arr, int left, int right) {
        int basic = arr[left];
        // 定义左右指针
        int i = left;
        int j = right;
        while (i < j) { // 左指针小于右指针
            while (i < j && arr[j] > basic) {// 操作右指针找到小于基数的下标
                j--;

            }
            if (i < j) {
                arr[i] = arr[j]; // 将右指针对应小于基数的值放到左指针所指的位置
                i++; // 左指针自加
            }
            while (i < j && arr[i] < basic) {// 相反，找到大于基数的下标
                i++;
            }
            if (i < j) {
                arr[j] = arr[i]; // 大于基数的值赋给右指针所指的位置
                j--; // 右指针自减
            }
        }
        arr[i] = basic; // 将基数放入到指针重合处
        return i;
    }

    public int[] OrderData() {
        return arr;
    }

    public double runtime() {
        return (time2 - time1);
    }

    public void sort() {
        time1 = System.currentTimeMillis();
        quicksort(arr, begin, end);
        time2 = System.currentTimeMillis();
    }

    @Override
    public void run() {
        quicksort(arr, begin, end);
        mergeSignal.countDown();
    }
}
