package gameOfLife;

import java.io.*;
public class safzlh {



    /**

     * 从文件路径初始化Cell对象

     *

     * @param path

     * @return

     */

    public static cell initMatrixFromFile(String path) {

        BufferedReader reader = null;

        try {

            reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));

            String line = reader.readLine();

            String[] array = line.split(" ");

            int width = Integer.parseInt(array[0]);

            int height = Integer.parseInt(array[1]);

            int ms = Integer.parseInt(array[2]);

            int totalNum = Integer.parseInt(array[3]);

            int[][] matrix = new int[height][width];

            for (int i = 0; i < height; i++) {

                line = reader.readLine();

                array = line.split(" ");

                for (int j = 0; j < array.length; j++) {

                    matrix[i][j] = Integer.parseInt(array[j]);

                }

            }



            cell cellMatrix = new cell(height, width, ms, totalNum, matrix);

            return cellMatrix;



        } catch (FileNotFoundException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            if (reader != null) {

                try {

                    reader.close();

                } catch (IOException e) {

                    e.printStackTrace();

                }

            }

        }

        return null;

    }



}