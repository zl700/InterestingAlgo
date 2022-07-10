import java.util.Random;
import java.util.TreeSet;

public class sortWithRedBlackTree {
    private TreeSet<Node> tree;
    public sortWithRedBlackTree(){
        this.tree=new TreeSet<>();
    }
    public class Node implements Comparable<Node>{
        private int data;
        private Node(int data){
            this.data=data;
        }
        @Override
        public int compareTo(sortWithRedBlackTree.Node o) {
            if(this.data<o.data){
                return -1;
            }else{
                return 1;
            }
        }
        @Override
        public String toString() {
            return "[node:"+this.data+"]";
        }
    }
    public void addAll(int[] data){
        for (int i = 0; i < data.length; i++) {
            tree.add(new Node(data[i]));
        }
    }
    public static int[] generate(int total){
        Random r=new Random();
        int[] arr=new int[total];
        for (int i = 0; i < total; i++) {
            arr[i]=r.nextInt(1000);
        }
        return arr;
    }

    public int[] sort(){
        int[] arr=new int[this.tree.size()];
        int i=0;
        for(Node node:tree){
            arr[i++]=node.data;
        }
        return arr;
    }
    public static void main(String[] args) {        
        sortWithRedBlackTree myTree=new sortWithRedBlackTree();
        int[] arr=generate(10000000);
        long start=System.currentTimeMillis();
        myTree.addAll(arr);
        int[] res=myTree.sort();
        long end=System.currentTimeMillis();
        System.out.println(end-start);
        // for(int i:res){
        //     System.out.printf("%d,",res[i]);
        // }

    }
}
