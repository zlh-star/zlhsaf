package gameOfLife;

import javax.swing.*;

import java.awt.*;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.util.concurrent.TimeUnit;

public class games extends JFrame {



    private JButton openFileBtn = new JButton("ѡ��������Ϸ�ļ�");

    private JButton startGameBtn = new JButton("��ʼ");

    private JLabel msPromtLabel = new JLabel("�������(msΪ��λ)");

    private JTextField msTextField = new JTextField();

    /**

     * ��Ϸ�Ƿ�ʼ�ı�־

     */

    private boolean isStart = false;



    /**

     * ��Ϸ�����ı�־

     */

    private boolean stop = false;



    private cell cellMatrix;

    private JPanel buttonPanel = new JPanel(new FlowLayout( ));

    private JPanel flowPanel = new JPanel();



    private JTextField[][] textMatrix;





    /**

     * ����Ĭ�ϼ��200ms

     */

    private static final int DEFAULT_DURATION = 300;



    //�������

    private int ms = DEFAULT_DURATION;



    public games() {

        setTitle("������Ϸ");

        openFileBtn.addActionListener(new OpenFileActioner());

        startGameBtn.addActionListener(new StartGameActioner());



        buttonPanel.add(openFileBtn);

        buttonPanel.add(startGameBtn);

        buttonPanel.add(msPromtLabel);

        buttonPanel.add(msTextField);

        buttonPanel.setBackground(Color.WHITE);



        getContentPane().add("North", buttonPanel);



        this.setSize(1000, 1200);

        this.setVisible(true);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }





    private class OpenFileActioner implements ActionListener {



        @Override

        public void actionPerformed(ActionEvent e) {

            JFileChooser fcDlg = new JFileChooser(".");

            fcDlg.setDialogTitle("��ѡ���ʼ�����ļ�");

            int returnVal = fcDlg.showOpenDialog(null);

            if (returnVal == JFileChooser.APPROVE_OPTION) {



                isStart = false;

                stop = true;

                startGameBtn.setText("��ʼ");



                String filepath = fcDlg.getSelectedFile().getPath();

                cellMatrix = safzlh.initMatrixFromFile(filepath);

                initGridLayout();

                showMatrix();

                flowPanel.updateUI();

            }

        }





    }



    private void showMatrix() {



        int[][] matrix = cellMatrix.getMatrix();

        for (int y = 0; y < matrix.length; y++) {

            for (int x = 0; x < matrix[0].length; x++) {

                if (matrix[y][x] == 1) {

                    textMatrix[y][x].setBackground(Color.BLACK);

                } else {

                    textMatrix[y][x].setBackground(Color.WHITE);

                }

            }

        }

    }



    /**

     * ������ʾ��flowlayout����

     */

    private void initGridLayout() {

        int rows = cellMatrix.getHeight();

        int cols = cellMatrix.getWidth();

        flowPanel = new JPanel();

        flowPanel.setLayout(new GridLayout(rows, cols));

        textMatrix = new JTextField[rows][cols];

        for (int y = 0; y < rows; y++) {

            for (int x = 0; x < cols; x++) {

                JTextField text = new JTextField();

                textMatrix[y][x] = text;

                flowPanel.add(text);

            }

        }

        add("Center", flowPanel);

    }





    private class StartGameActioner implements ActionListener {



        @Override

        public void actionPerformed(ActionEvent e) {

            if (!isStart) {



                //��ȡʱ��

                try {

                    ms = Integer.parseInt(msTextField.getText().trim());

                } catch (NumberFormatException e1) {

                    ms = DEFAULT_DURATION;

                }



                new Thread(new GameControlTask()).start();

                isStart = true;

                stop = false;

                startGameBtn.setText("��ͣ");

            } else {

                stop = true;

                isStart = false;

                startGameBtn.setText("��ʼ");

            }

        }

    }



    private class GameControlTask implements Runnable {



        @Override

        public void run() {



            while (!stop) {

                cellMatrix.transform();

                showMatrix();



                try {

                    TimeUnit.MILLISECONDS.sleep(ms);

                } catch (InterruptedException ex) {

                    ex.printStackTrace();

                }

            }



        }

    }



}