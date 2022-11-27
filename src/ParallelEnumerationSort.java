
import java.util.concurrent.CountDownLatch;

public class ParallelEnumerationSort {
    int[] arr;
    int[] orderarr;
    long time1, time2;
    int threadnum = Main.threadnum;
    CountDownLatch mergeSignal = new CountDownLatch(threadnum);

    public void PEsort(int[] data) {
        time1 = System.currentTimeMillis();
        arr = data;
        orderarr = new int[arr.length];

        int part = arr.length / threadnum;
        int begin = 0;
        int end = part;
        for (int i = 0; i < threadnum; i++) {
            PEpos th = new PEpos(begin, end, this);
            th.run();
            begin = end;
            end += part;
            if (i == threadnum - 2)
                end = arr.length - 1;
            // System.out.println(begin + "----" + end + "\n");
        }
        try {
            mergeSignal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        time2 = System.currentTimeMillis();
    }

    public double runtime() {
        return (time2 - time1);
    }

    public int[] OrderData() {
        return orderarr;
    }
}
