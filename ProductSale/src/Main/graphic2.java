package Main;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class graphic2 {
	public static void main(String[] args) {
		JFrame frame= new JFrame();
		frame.setTitle("Your Name");
		Container cp = frame.getContentPane();
		cp.setLayout(new FlowLayout());
		
		Box b0 = new Box(BoxLayout.Y_AXIS);
		JLabel label1 = new JLabel("상품 1");
		JLabel label2 = new JLabel("상품 2");
		JLabel label3 = new JLabel("상품 3");      
		JLabel label4 = new JLabel("상품 4");
		JLabel label5 = new JLabel("상품 5");
		b0.add(label1);
		b0.add(label2);
		b0.add(label3);     //라벨추가
		b0.add(label4);
		b0.add(label5);
		cp.add(b0);
		
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
