package Main;

import java.awt.Button;
import java.awt.Container;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class graphic1 extends JFrame {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setTitle("Your Name");
		Container cp = frame.getContentPane();
		cp.setLayout(new FlowLayout());

		Box b1 = new Box(BoxLayout.X_AXIS);

		JButton button1 = new JButton("����");
		JButton button2 = new JButton("������");

		button1.addActionListener(new ActionListener(){ //�͸�Ŭ������ ������ �ۼ�
			public void actionPerformed(ActionEvent e){
				JButton btn = (JButton) e.getSource();
				if(btn.getText().equals("����"))
					btn.setText("Hello");
				else 
					btn.setText("Click");
			}
		});
		
		ImageIcon ic = new ImageIcon("./images/gg.jpg");
		JLabel lbImage1 = new JLabel(ic);
		frame.add(lbImage1);
		frame.setVisible(true);
		frame.setBounds(10, 10, 800, 600);
		
		b1.add(button1);
		b1.add(button2);
		cp.add(b1);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.setVisible(true);
	}
}
