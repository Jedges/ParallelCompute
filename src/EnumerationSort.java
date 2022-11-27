public class EnumerationSort {
    int[] arr;
    int[] orderarr;
    long beign, end;

    public void enumerationSort(int[] data) {
        beign = System.currentTimeMillis();
        arr = data;
        orderarr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            sort(i);
        }
        end = System.currentTimeMillis();
    }

    public void sort(int temp) {
        int pos = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < arr[temp])
                pos++;
            else if (arr[i] == arr[temp] && i < temp)
                pos++;
        }
        orderarr[pos] = arr[temp];
    }

    public int[] OrderData() {
        return orderarr;
    }

    public double runtime() {
        return (end - beign);
    }
}