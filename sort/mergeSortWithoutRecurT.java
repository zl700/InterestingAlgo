import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class mergeSortWithoutRecurT {

    // left[ll,lr] inclusive, right[rl,rr] inclusive
    // this is better than left(l,m), right(m+1,r) when writting out sort without recursion
    public static <T extends Comparable<T>> void merge(List<T> list, List<T> sparelist, int ll, int lr, int rl, int rr) {
        int i = ll;
        int k = ll;
        int j = rl;
        // we need to adjust index which goes beyond the whole list.
        if(ll>list.size()-1){
            ll=list.size()-1;
            lr=list.size()-1;
            rl=list.size()-1;
            rr=list.size()-1;
        }else if(lr>list.size()-1){
            lr = list.size() - 1;
            rl = list.size() - 1;
            rr = list.size() - 1;
        }else if(rl>list.size()-1){
            rl = list.size() - 1;
            rr=list.size() - 1;
        }else if(rr>list.size()-1){
            rr=list.size() - 1;
        }
        while (i <= lr && j <= rr) {
            T val_i=list.get(i);
            T val_j=list.get(j);
            if (val_i.compareTo(val_j)<0) {
                sparelist.set(k,val_i);
                i++;
                k++;
            } else {
                sparelist.set(k,val_j);
                j++;
                k++;
            }
        }
        for (; i <= lr; i++) {
            sparelist.set(k,list.get(i));
            k++;
        }

        for (; j <= rr; j++) {
            sparelist.set(k,list.get(j));
            k++;
        }

    }

    public static List<Integer> generate(int total) {
        List<Integer> list = new ArrayList<>(total);
        Random random = new Random();
        for (int i = 0; i < total; i++) {
            list.add(random.nextInt(total));
        }
        return list;
    }
    public static <T extends Comparable<T>> List<T> sort(List<T> list){
        List<T> sparelist=new ArrayList<>(list.size());
        for (int i = 0; i < list.size(); i++) {
            sparelist.add(list.get(i));
        }
        int step=0;int count=0;
        while(true){
            //this is tricky, we need to find the law by ourselves:
            //loop0        loop1           loop2
            //ll lr rl rr  ll lr rl rr   ll lr rl rr
            //0  0  1  1   0  1  2  3    0  3  4  7
            //2  2  3  3==>4  5  6  7==> 8  11 12 15
            //4  4  5  5   8  9  10 11   16 19 20 23
            //6  6  7  7   12 13 14 15   24 27 28 32  
            step=(int)Math.pow(2, count)-1;
            
            for(int i=0; i<list.size(); i=i+step*2+2){
                if(count%2==0){
                    merge(list, sparelist, i, i+step,i+step+1,i+step*2+1);
                }else{
                    merge(sparelist, list, i, i+step,i+step+1,i+step*2+1);
                }
            }
            count++;
            // when we sort the listay with one merge, we can break
            // the last element is step*2+1,check for lookloop for deduction of last element 
            // i+step*2+1 when i==0;
            // so when step*2+1 is larger than the last index list.size()-1
            if (step * 2 + 2 > list.size()) {
                break;
            }
        }
        
        if(count%2==0){
            return list;
        }else{
            return sparelist;
        }
    }

    public static <T extends Comparable<T>> boolean isSorted(List<T> list){
        T previous=list.get(0);
        for(int i=1;i<list.size();i++){
            if(previous.compareTo(list.get(i))>0){
                return false;
            }
            previous=list.get(i);
        }
        return true;
    }
    public static void show(List<Integer> list){
        for (int i : list) {
            System.out.printf("%d,", i);
        }
        System.out.println("\n");
    }
    public static void test(int total){
        Random r=new Random();
        for(int i=0;i<total;i++){
            List<Integer> list=generate(r.nextInt(1,total));
            List<Integer> res=sort(list);
            System.out.println(isSorted(res));
            if(isSorted(res)==false){
                show(res);
            }
        } 
    }
    public static void main(String[] args) {
        List<Integer> list=generate(100000000);
        List<Integer> list2=new ArrayList<>();
        for(Integer i:list){
            list2.add(i);
        }
        long start=System.currentTimeMillis();
        List<Integer> res=mergeSortWithoutRecurT.sort(list);
        long end=System.currentTimeMillis();
        System.out.println(end-start);
        System.out.println(isSorted(res));

        start = System.currentTimeMillis();
        Collections.sort(list2);
        end = System.currentTimeMillis();
        System.out.println(end-start);
        // test(1000);
    }

}
