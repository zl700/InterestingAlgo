import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class mergeSortT {
    
    public static <T extends Comparable<T>> void merge(List<T> list,List<T> spareList,int ll,int lr,int rl,int rr){
        int i=ll;
        int k=ll;
        int j=rl;
        while(i<=lr&&j<=rr){
            T value_i=list.get(i);
            T value_j=list.get(j);
            if(value_i.compareTo(value_j)<0){
                spareList.set(k,value_i);
                i++;
                k++;
            }else{
                spareList.set(k,value_j);
                j++;
                k++;
            }
        }
        for(;i<=lr;i++){
            spareList.set(k,list.get(i));
            k++;
        }

        for(;j<=rr;j++){
            spareList.set(k,list.get(j));
            k++;
        }
      
    }

    public static <T extends Comparable<T>> void sortR(List<T> list,List<T> spareList,int l,int r){
        if(l>=r){
            return;
        }
        int m_index=(l+r)/2;
        sortR(spareList,list,l, m_index);
        sortR(spareList,list, m_index+1, r);
        merge(spareList,list,l,m_index,m_index+1,r);
    }

    public static <T extends Comparable<T>> void sort(List<T> list){
        List<T> spareList=new ArrayList<>(list);
        sortR(list, spareList, 0,list.size()-1);
    }

    public static List<Integer> generate(int total){
        List<Integer> list=new ArrayList<>();
        Random random=new Random();
        for(int i=0;i<total;i++){
            list.add(random.nextInt());
        } 
        return list;
    }
    public static void main(String[] args) {
        List<Integer> list = generate(10000000);
        long start=System.currentTimeMillis();
        mergeSortT.sort(list);
        long end=System.currentTimeMillis();
        System.out.println(end-start);
    }
}
