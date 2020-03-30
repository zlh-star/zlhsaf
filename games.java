package gameOfLife;

import javax.swing.*;

import java.awt.*;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.util.concurrent.TimeUnit;

public class games extends JFrame {



    private JButton openFileBtn = new JButton("选择运行游戏文件");

    private JButton startGameBtn = new JButton("开始");

    private JLabel msPromtLabel = new JLabel("动画间隔(ms为单位)");

    private JTextField msTextField = new JTextField();

    /**

     * 游戏是否开始的标志

     */

    private boolean isStart = false;



    /**

     * 游戏结束的标志

     */

    private boolean stop = false;



    private cell cellMatrix;

    private JPanel buttonPanel = new JPanel(new FlowLayout( ));

    private JPanel flowPanel = new JPanel();



    private JTextField[][] textMatrix;





    /**

     * 动画默认间隔200ms

     */

    private static final int DEFAULT_DURATION = 300;



    //动画间隔

    private int ms = DEFAULT_DURATION;



    public games() {

        setTitle("生命游戏");

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

            fcDlg.setDialogTitle("请选择初始配置文件");

            int returnVal = fcDlg.showOpenDialog(null);

            if (returnVal == JFileChooser.APPROVE_OPTION) {



                isStart = false;

                stop = true;

                startGameBtn.setText("开始");



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

     * 创建显示的flowlayout布局

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



                //获取时间

                try {

                    ms = Integer.parseInt(msTextField.getText().trim());

                } catch (NumberFormatException e1) {

                    ms = DEFAULT_DURATION;

                }



                new Thread(new GameControlTask()).start();

                isStart = true;

                stop = false;

                startGameBtn.setText("暂停");

            } else {

                stop = true;

                isStart = false;

                startGameBtn.setText("开始");

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