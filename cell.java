package gameOfLife;

import java.util.Arrays;

public class cell {

    /**

     * ����߶�

     */

    private int height;



    /**

     * ������

     */

    private int width;



    /**

     * �����ٶȣ�ÿ����״̬֮��ĺ�����

     */

    private int ms;



    /**

     * �ܵı仯����

     */

    private int transfromNum=0;



    /**

     * ����״̬��1��ʾ�0��ʾ��

     */

    private int[][] matrix;



    public cell(int height, int width, int ms, int transfromNum, int[][] matrix) {

        this.height = height;

        this.width = width;

        this.ms = ms;

        this.transfromNum = transfromNum;

        this.matrix = matrix;

    }



    /**

     * ��һ��״̬����һ��״̬��ת��

     * ���ݹ�������ܽ�ó���������:

     * 1. ������Χ���ŵ�ϸ��Ϊ3�����,��һ��״̬��ϸ������Ϊ��

     * 2. ������Χ���ŵ�ϸ��Ϊ2�����,��һ��״̬����һ״̬��ͬ

     */

    public void transform(){

        int[][] nextMatrix=new int[height][width];

        for (int y = 0; y < matrix.length; y++) {

            for (int x = 0; x < matrix[0].length; x++) {

                nextMatrix[y][x]=0;

                int nearNum= findLifedNum(y,x);

                //����3������һ״̬���ǻ�

                if(nearNum==3){

                    nextMatrix[y][x]=1;

                }

                //����2��������һ״̬һ��

                else if(nearNum==2){

                    nextMatrix[y][x]=matrix[y][x];

                }

            }

        }

        matrix=nextMatrix;

    }







    /**

     * ͳ��ÿ��ϸ����Χ���ŵĸ���

     * @param x ������

     * @param y ������

     * @return

     */

    public int findLifedNum(int y, int x){

        int num=0;

        //���

        if(x!=0){

            num+=matrix[y][x-1];

        }

        //���Ͻ�

        if(x!=0&&y!=0){

            num+=matrix[y-1][x-1];

        }

        //�ϱ�

        if(y!=0){

            num+=matrix[y-1][x];

        }

        //����

        if(x!=width-1&&y!=0){

            num+=matrix[y-1][x+1];

        }

        //�ұ�

        if(x!=width-1){

            num+=matrix[y][x+1];

        }

        //����

        if(x!=width-1&&y!=height-1){

            num+=matrix[y+1][x+1];

        }

        //�±�

        if(y!=height-1){

            num+=matrix[y+1][x];

        }

        //����

        if(x!=0&&y!=height-1){

            num+=matrix[y+1][x-1];

        }

        return num;

    }



    @Override

    public String toString() {



        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < matrix.length; i++) {

            sb.append(Arrays.toString(matrix[i]) + "\n");

        }

        return sb.toString();

    }



    public int getHeight() {

        return height;

    }



    public int getWidth() {

        return width;

    }



    public int[][] getMatrix() {

        return matrix;

    }



    public int getTransfromNum() {

        return transfromNum;

    }



    public int getDuration() {

        return ms;

    }

}