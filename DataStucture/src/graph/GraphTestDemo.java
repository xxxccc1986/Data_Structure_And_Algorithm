package graph;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 图结构测试
 * @date 2022/11/14 20:51
 */
public class GraphTestDemo {
    public static void main(String[] args) {
        Graph graph = new Graph(5);
        String[] val = {"A","B","C","D","E"};
        for (String s : val) {
            graph.insertVertex(s);
        }

        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);

        // graph.showGraphMatrix();
        // System.out.printf("当前顶点的第一个相邻顶点下标是：%d \n",graph.getFirstNearVertex(1)  );
        // System.out.printf("当前顶点的下一个相邻顶点下标是：%d \n",graph.getNextNearVertex(0,0));

        System.out.println("深度遍历：");
        // graph.dfs(0);
        graph.dfs();
        System.out.println("\n广度遍历：");
        graph.bfs();
    }
}
