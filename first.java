package gameOfLife;

import java.io.File;

import java.io.FileWriter;

import java.io.IOException;

import java.io.PrintWriter;

import java.util.Random;



/**

 * �������case

 */

public class first {



    /**

     * �����ļ�����

     */

    public static final int FILE_NUM=1;



    public static void main(String[] args) {



        for (int i = 0; i < FILE_NUM; i++) {



            createCaseFile();

            System.out.println(i+1);

        }

        System.out.println("�ɹ�����1��case�ļ�");

    }



    /**

     * �������԰���

     */

    private static void createCaseFile() {



        Random random = new Random();

        int rows = 1 + random.nextInt(100);

        int cols = 1 + random.nextInt(100);

        int ms = 200;

        int num = 300;

        File file = new File(cols+"_"+rows+"_"+System.nanoTime() + ".txt");

        PrintWriter writer = null;

        try {

            writer = new PrintWriter(new FileWriter(file));

            StringBuilder sb = new StringBuilder(cols + " " + rows + " " + ms + " " + num);

            writer.write(sb.append("\n").toString());



            //��ʼ���г�ʼ��

            for (int y = 0; y < rows; y++) {

                sb = new StringBuilder();

                for (int x = 0; x < cols; x++) {



                    if (random.nextInt(3) % 3 == 0) {

                        sb.append("1 ");

                    } else {

                        sb.append("0 ");

                    }

                }

                sb.deleteCharAt(sb.length()-1).append("\n");

                writer.write(sb.toString());

            }





        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            if (writer != null) {

                writer.close();

            }

        }



    }



}