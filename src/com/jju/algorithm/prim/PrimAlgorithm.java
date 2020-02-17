package com.jju.algorithm.prim;

import java.util.Arrays;

//Prm(普利姆)算法---解决修路问题
public class PrimAlgorithm {

    public static void main(String[] args) {
        //测试
        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int verxs = data.length;
        //邻接矩阵关系使用二维数组表示，用10000这个大数表示两个点不连通
        int [][] weight = new int[][]{
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000}
        };

        //创建MGraph对象
        MGraph graph = new MGraph(verxs);
        //创建一个MinTree对象
        MinTree minTree = new MinTree();
        //创建
        minTree.createGraph(graph, verxs, data, weight);
        //显示
        minTree.showGraph(graph);

        //测试普利姆算法
        minTree.prim(graph, 0);
    }

}

//创建最小生成树
class MinTree{

    /**
     *  创建图的邻接矩阵
     * @param graph         图对象
     * @param verxs         图对应的顶点个数
     * @param data          图的各个顶点的值
     * @param weight        图的邻接矩阵
     */
    public void createGraph(MGraph graph, int verxs, char [] data, int [][] weight){
        int i, j;
        for (i = 0; i < verxs; i++) {       //顶点
            graph.data[i] = data[i];
            for (j = 0; j < verxs; j++) {
                graph.weight[i][j] = weight[i][j];
            }

        }
    }

    //显示图的邻接矩阵
    public void showGraph(MGraph graph){
        for (int[] link : graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * 编写prim算法，得到最小生成树
     * @param graph     图
     * @param v         表示从图的第几个顶点开始生成'A'->0 'B'->1 ...
     */
    public void prim(MGraph graph, int v){
        //visited 标记顶点是否被访问过
        int [] visited = new int[graph.verxs];
        //visited默认元素的值都是0，表示没有访问过
//        for (int i = 0; i < graph.verxs; i++) {
//            visited[i] = 0;
//        }

        //把当前这个节点标记为已访问
        visited[v] = 1;
        //h1 和 h2 记录两个顶点的下标
        int h1 = -1;
        int h2 = -1;
        int minWeight = 10000;  //将minWeight初始化成一个大数，后面在遍历过程中，会被替换
        for (int k = 1; k < graph.verxs; k++) { //因为有 graph.verxs 顶点，普利姆算法结束后，有 graph.verxs - 1条边

            //确定每一次生成的子图，和哪个节点和这次遍历的节点的距离最近
            for (int i = 0; i < graph.verxs; i++) {         //i节点表示被访问过的节点
                for (int j = 0; j < graph.verxs; j++) {     //j节点表示还没有访问过的节点
                    if(visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight){
                        //替换 minWeight（寻找已经访问过的节点和未访问过的节点间的权值最小的边）
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }

            //找到一条边是最小的
            System.out.println("边<" + graph.data[h1] + "," + graph.data[h2] + "> 权值：" + minWeight);
            //将当前这个节点标记为已经访问的
            visited[h2] = 1;
            //minWeight 重新设置为最大值 10000
            minWeight = 10000;

        }
    }

}

class MGraph{
    int verxs;   //表示图的节点个数
    char [] data;   //存放节点数据
    int [][] weight;    //存放边，就是邻接矩阵

    public MGraph(int verxs){
        this.verxs = verxs;
        data = new char[verxs];
        weight = new int[verxs][verxs];
    }
}
