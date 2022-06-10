package swing;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;

/**
 * @Author Yanghz
 * @Since 2022/4/27
 * @Description
 */
public class MySwingApplication {

    public static void main(String[] args) {
        createWindow();
    }

    private static void createWindow() {
        JFrame frame = new JFrame("Swing使用边框示例");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createUI(frame);
        frame.setSize(560, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    private static void createUI(JFrame frame){
        Border blackline = BorderFactory.createLineBorder(Color.red);
        Border raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
        Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        Border raisedbevel  = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        Border loweredbevel = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        Border empty = BorderFactory.createEmptyBorder();

        JPanel panel = new JPanel();
        LayoutManager layout = new FlowLayout();
        panel.setLayout(layout);

        JPanel panel1 = new JPanel();
        panel1.add(new JLabel("红色边框"));
        panel1.setBorder(blackline);

        JPanel panel2 = new JPanel();
        panel2.add(new JLabel("边框凸起"));
        panel2.setBorder(raisedetched);

        JPanel panel3 = new JPanel();
        panel3.add(new JLabel("边框凹下"));
        panel3.setBorder(loweredetched);

        JPanel panel4 = new JPanel();
        panel4.add(new JLabel("凸起的斜面边框"));
        panel4.setBorder(raisedbevel);

        JPanel panel5 = new JPanel();
        panel5.add(new JLabel("降低斜面边框"));
        panel5.setBorder(loweredbevel);

        JPanel panel6 = new JPanel();
        panel6.add(new JLabel("空边框"));
        panel6.setBorder(empty);

        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);
        panel.add(panel4);
        panel.add(panel5);
        panel.add(panel6);

        frame.getContentPane().add(panel, BorderLayout.CENTER);
    }
}
