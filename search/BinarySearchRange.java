import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//what if I want to find the smallest range which contains my target with binarySearch
//we just need to adjust binarySearch a little
//but the edge case is kind of crazy
//please let me know If I made a mistake
public class BinarySearchRange<T extends Comparable<T>> {
    public BinarySearchRange() {

    }

    public int[] searchRange(T[] arr, T target) {
        int l = 0;
        int r = arr.length - 1;
        while (r - l > 1) {
            int m = (l + r) / 2;
            int res = target.compareTo(arr[m]);
            if (res == 0) {
                return new int[] { m, m };
            } else if (res > 0) {
                l = m;
            } else {
                r = m;
            }
        }
        int compareArr0AndTarget = arr[0].compareTo(target);
        int compareArrLastAndTarget = arr[r].compareTo(target);
        if (l == 0 && compareArr0AndTarget > 0) {
            return new int[] { -1, 0 };
        } else if (r == arr.length - 1 && compareArrLastAndTarget < 0) {
            return new int[] { r, arr.length };
        } else if (compareArr0AndTarget == 0) {
            return new int[] { l, l };
        } else if (compareArrLastAndTarget == 0) {
            return new int[] { r, r };
        }
        return new int[] { l, r };
    }

    public int[] searchRange(List<T> list, T target) {
        int l = 0;
        int r = list.size() - 1;
        while (r - l > 1) {
            int m = (l + r) / 2;
            int res = target.compareTo(list.get(m));
            if (res == 0) {
                return new int[] { m, m };
            } else if (res > 0) {
                l = m;
            } else {
                r = m;
            }
        }
        int compareArr0AndTarget = list.get(0).compareTo(target);
        int compareArrLastAndTarget = list.get(list.size() - 1).compareTo(target);
        if (l == 0 && compareArr0AndTarget > 0) {
            return new int[] { -1, 0 };
        } else if (r == list.size() - 1 && compareArrLastAndTarget < 0) {
            return new int[] { r, list.size() };
        } else if (compareArr0AndTarget == 0) {
            return new int[] { l, l };
        } else if (compareArrLastAndTarget == 0) {
            return new int[] { r, r };
        }
        return new int[] { l, r };
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[] { 1, 2, 3, 4, 7, 8, 9, 11, 14, 19 };
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 7, 8, 9, 11, 14, 19));
        BinarySearchRange<Integer> bs = new BinarySearchRange<>();
        int[] range1 = bs.searchRange(arr, 0);
        int[] range2 = bs.searchRange(list, 0);
        for (int i : range1) {
            System.out.printf("%d ", i);
        }
        System.out.println();
        for (int i : range2) {
            System.out.printf("%d ", i);
        }
    }
}
