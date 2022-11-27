
import java.util.concurrent.CountDownLatch;

public class ParallelQuickSort {
    int[] arr;
    long time1, time2;

    public void pqsort(int[] data, int left, int right) {
        time1 = System.currentTimeMillis();
        arr = data;
        CountDownLatch mergeSignal = new CountDownLatch(4);
        int r1 = QuickSort.partition(arr, left, right);
        int r0 = QuickSort.partition(arr, left, r1 - 1);
        int r2 = QuickSort.partition(arr, r0 + 1, right);

        Thread thread0 = new Thread(new QuickSort(arr, left, r0 - 1, mergeSignal));
        Thread thread1 = new Thread(new QuickSort(arr, r0 + 1, r1 - 1, mergeSignal));
        Thread thread2 = new Thread(new QuickSort(arr, r1 + 1, r2 - 1, mergeSignal));
        Thread thread3 = new Thread(new QuickSort(arr, r2 + 1, right, mergeSignal));
        thread0.start();
        thread1.start();
        thread2.start();
        thread3.start();
        try {
            mergeSignal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        time2 = System.currentTimeMillis();
    }

    public int[] OrderData() {
        return arr;
    }

    public double runtime() {
        return (time2 - time1);
    }
}