package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 图创建
 * @date 2022/11/13 22:57
 */
public class Graph {

    private static ArrayList<String> vertexList;//表示存储顶点的List
    private static int[][] edges;//表示无向图的邻接矩阵的二维数组
    private static int numOfEdge; //表示边的个数
    //表示已被遍历的节点数组 对应索引代表第几个顶点，索引的值代表是否被遍历
    private static boolean[] beReadVertex;

    public Graph(int num){
        //初始化二维数组和list集合
        edges = new int[num][num];
        vertexList = new ArrayList<>(num);
        beReadVertex =  new boolean[num];
    }

    /**
     * Description : 添加顶点
     * @date 2022/11/13
     * @param vertex 待添加的顶点
     **/
    public  void insertVertex(String vertex){
        vertexList.add(vertex);
    }

    /**
     * Description : 添加边
     * @date 2022/11/13
     * @param v1 表示当前第一个顶点在所对应的行数
     * @param v2 表示当前第二个顶点在所对应的列数
     * @param value 权值
     **/
    public void insertEdge(int v1,int v2,int value){
        //由于无向图的邻接矩阵是个对称矩阵，所以[v1][v2]或[v2][v1]的值都等于value；
        edges[v1][v2] = value;
        edges[v2][v1] = value;
        //边数+1
        numOfEdge++;
    }

    /* 图的常用方法 */
    /**
     * Description : 获取顶点的个数
     * @date 2022/11/14
     **/
    public int getNumOfVertex(){
        return vertexList.size();
    }

    /**
     * Description : 获取边的个数
     * @date 2022/11/14
     **/
    public int getNumOfEdge(){
        return numOfEdge;
    }

    /**
     * Description : 获取对应顶点的值
     * @date 2022/11/14
     * @param index 索引
     **/
    public String getVertexOfValue(int index){
        return vertexList.get(index);
    }

    /**
     * Description : 返回v1和v2的值
     * @date 2022/11/14
     **/
    public int getValue(int v1,int v2){
        return edges[v1][v2];
    }

    /**
     * Description : 显示图对应的矩阵
     * @date 2022/11/14
     **/
    public void showGraphMatrix(){
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
    }

    /* ============================ 深度优先遍历 ==================================*/
    /**
     * Description : 找到当前顶点的第一个相邻顶点的下标
     * @date 2022/11/14
     * @param index 当前顶点索引
     **/
    public int getFirstNearVertex(int index){
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] != 0){
                return i;
            }
        }
        return -1;
    }


    /**
     * Description : 找到当前顶点的下一个相邻顶点的下标
     * @date 2022/11/14
     * @param indexRow 当前顶点索引所在行
     * @param indexColumn 当前顶点索引所在列
     **/
    public int getNextNearVertex(int indexRow,int indexColumn){
        for (int i = indexColumn+1; i < vertexList.size(); i++) {
            if (edges[indexRow][i] != 0){
                return i;
            }
        }
        return -1;
    }

    /**
     * Description : 深度优先算法
     * ①遍历初始节点 标记为已遍历
     * ②得到初始节点的第一个相邻节点
     * 判断第一个相邻节点的情况
     * Ⅰ判断是否存在(不存在，返回到刚刚访问过的节点寻找它的相邻节点)
     * Ⅱ判断是否已被遍历(已遍历，返回到刚刚访问过的节点寻找它的相邻节点)
     * ③以当前的相邻节点作为初始节点重复以上步骤
     * @date 2022/11/14
     * @param index 传入要遍历的顶点索引
     **/
    public void dfs(int index){
        //获取目标顶点
        String v = getVertexOfValue(index);
        //输出并将index对应的顶点标记为已遍历
        System.out.print(v + "=>");
        beReadVertex[index] = true;

        //查找第一个相邻顶点
        int w = getFirstNearVertex(index);
        //判断w存在情况
        //这里的while相当于遍历二维数组中某一行的所有列。也就是判断某个顶点和其他顶点的关系
        while (w != -1){ //w != -1表示存在
            //判断是否已被遍历，取反为true表示未被遍历，再次执行dfs深度算法
            if (!beReadVertex[w]){
                dfs(w);
            }
            //没有进入上面的if函数表示已被遍历，需要找到当前顶点的其他相邻顶点
            w = getNextNearVertex(index,w);
        }
    }

    /**
     * Description : 图的顶点不一定是相连的，比如非连通图，某个节点和其他节点都不相通，
     * 没有这个方法会无法遍历到这个独立的节点，因此这个重载的方法非常有必要。
     * 其实就是连通图和非连通的区别所导致的，这里跟回溯没有关系，
     * 只是为了遍历在上面dfs深度算法中没有被遍历到的不连通的节点
     * @date 2022/11/15
     **/
    public void dfs(){
        for (int i = 0; i < vertexList.size(); i++) {
            //找到未被遍历的单独节点进行输出
            if (!beReadVertex[i]){
                dfs(i);
            }
        }
    }

    /* ============================ 广度优先遍历 ==================================*/
    /**
     * Description : 广度优先算法
     * ①先创建队列
     * ②输出下标对应节点并标记为已遍历同时将当前节点入队列
     * ③判断队列是否为空
     * 不为空弹出当前队列的头节点并找到对应下标的节点
     * ④找到当前队列头节点下标值的相邻节点
     * ⑤判断相邻节点是否存在
     * Ⅰ如果存在再判断是否已被遍历
     * Ⅱ尚未遍历则输出当前节点并标记为已遍历，同时加入队列中
     * Ⅲ再次寻找当前头节点下标值对应节点其他能够连通但尚未遍历的节点
     * @date 2022/11/15
     * @param index 传入要遍历的顶点索引
     **/
    public void bfs(int index){
        //创建存储顶点访问顺序的队列
        LinkedList<Integer> queue = new LinkedList<>();

        System.out.print(getVertexOfValue(index) + "=>");
        beReadVertex[index] = true;

        //将当前节点索引加入队列
        queue.add(index);
        int u; //当前队列当前头节点所对应的下标
        int w; //当前顶点的邻接顶点

        //判断队列此时是否为空
        while (!queue.isEmpty()){
            //表示此时所有的节点都被遍历过了，没必要再次进行剩下的尝试
            if (beReadVertex[beReadVertex.length-1]){
                return;
            }
            //输出并标记为已遍历
            u = queue.poll();
            //获得当前头节点对应下标的邻接节点
            w = getFirstNearVertex(u);
            //这个wilie的判断条件其实就是在判断w是否存在
            while (w != -1){ //代表找到
                if (!beReadVertex[w]){ //取反为true表示未被遍历
                    //输出并标记为已遍历、加入队列
                    System.out.print(getVertexOfValue(w) + "=>");
                    beReadVertex[w] = true;
                    queue.add(w);
                }
                //查找当前结点u的继w邻接结点后的下一个邻接结点w,这里体现出了广度优先
                w = getNextNearVertex(u,w);
            }
        }
    }


    /**
     * Description : 与上面的深度优先算法的重载方法dfs的原因一样，都是为了遍历非连通图的中的孤立顶点
     * @date 2022/11/15
     **/
    public void bfs(){
        //下面这个重置beReadVertex的容量非必要，只是为了同时执行深度和广度遍历才加上的
        beReadVertex = new boolean[vertexList.size()];
        for (int i = 0; i < vertexList.size(); i++) {
            if (!beReadVertex[i]){
                bfs(i);
            }
        }
    }


}
