
public class PEpos implements Runnable {
    private int begin, end;

    ParallelEnumerationSort f;

    PEpos(int begin, int end, ParallelEnumerationSort father) {
        this.begin = begin;
        this.end = end;
        f = father;
    }

    @Override
    public void run() {
        for (int i = begin; i < end; i++) {
            int rank = 0;
            for (int j = 0; j <= f.arr.length - 1; j++) {
                if ((f.arr[i] > f.arr[j]) || ((f.arr[i] == f.arr[j] && i > j)))
                    rank++;
            }
            synchronized (this) {
                f.orderarr[rank] = f.arr[i];
            }
        }
        f.mergeSignal.countDown();
    }
}
