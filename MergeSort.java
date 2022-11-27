
public class MergeSort {

    int[] arr;
    long beign, end;

    public void mergesort(int[] data) {
        beign = System.currentTimeMillis();
        arr = data;
        sort(0, arr.length - 1);
        end = System.currentTimeMillis();
    }

    public void sort(int left, int right) {
        if (left == right) {
            return;
        }
        // 分成两半
        int mid = left + (right - left) / 2;
        // 左边排序
        sort(left, mid);
        // 右边排序
        sort(mid + 1, right);

        merge(left, mid + 1, right);
    }

    public double runtime() {
        return (end - beign);
    }

    void merge(int leftPtr, int rightPtr, int rightBound) {
        int mid = rightPtr - 1;
        int[] temp = new int[rightBound - leftPtr + 1];

        int i = leftPtr;
        int j = rightPtr;
        int k = 0;

        while (i <= mid && j <= rightBound) {
            if (arr[i] <= arr[j]) {
                temp[k] = arr[i];
                i++;
                k++;
            } else {
                temp[k] = arr[j];
                j++;
                k++;
            }
        }

        // 将右边剩余的归并
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        // 将左边剩余的归并
        while (j <= rightBound) {
            temp[k++] = arr[j++];

        }

        for (int m = 0; m < temp.length; m++)
            arr[leftPtr + m] = temp[m];
    }

    // 排序
    void swap(int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public int[] OrderData() {
        return arr;
    }

}
