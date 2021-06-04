package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import simulation.Address;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MyView extends JFrame implements ActionListener {
	
	private JButton [][] grid;
	
	public MyView() throws IOException {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(0, 0, 800, 600);
		setVisible(true);
		ImageIcon icnn = new ImageIcon((ImageIO.read(new File("grass.jpg")))); 
	
		
		//JLabel background = new JLabel();
		//background.setIcon(icnn);
		//background.setBounds(0, 0, 400, 400);
		//background.setSize(1200, 1200);
		JTextArea text = new JTextArea();
		text.setEditable(false);
		text.setText("Current Cycle:" + "\n" + "Number of Causalties:");
		//text.setText("Number of Causalties:");

		add(text, BorderLayout.NORTH);
		
		JPanel rescue = new JPanel();
		rescue.setLayout(new GridLayout(10,10));
		
		//game.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		rescue.setBounds(20, 50, 400, 400);
		//game.setBackground(icnn);
		
		//game.setOpaque(false);
//		for(int i = 0; i<100; i++) {
//			JButton b = new JButton(icnn);
//			b.setSize(40, 40);
//			//b.setBorder(BorderFactory.createLineBorder(Color.WHITE));
//			//b.setSelectedIcon(icnn);
//			//b.setOpaque(false);
//			//b.setContentAreaFilled(false);
//			rescue.add(b);
//		}
		
		grid = new JButton [10][10];
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++) {
				grid[i][j] = new JButton(icnn);
				grid[i][j].setSize(40, 40);
				rescue.add(grid[i][j]);
			}

		JPanel units = new JPanel();
		units.setLayout(new GridLayout(5,1));
		add(units, BorderLayout.EAST);
		//units.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		units.setSize(100, 800);
		units.setBackground(Color.WHITE);
		//units.setOpaque(false);
		
		for(int i = 0; i<5; i++) {
			JButton bb;
			Icon icon;
			switch(i) {
			
			case 0: {
				//icon = new ImageIcon(ImageIO.read(new File("ftk.jpg")));
				bb = new JButton("Firetruck \n");
				bb.setSize(40, 30);
				//bb.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				units.add(bb);
				break;
			}
			case 1: {
				//icon = new ImageIcon(ImageIO.read(new File("amb.png")));
				bb = new JButton("Ambulance \n");
				bb.setSize(60, 30);
				//bb.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				units.add(bb);
				break;
			}
			case 2: {
				//icon = new ImageIcon(ImageIO.read(new File("evc.jpg")));
				bb = new JButton("Evacuator \n");
				bb.setSize(60, 30);
				//bb.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				units.add(bb);
				break;
			}
			case 3: {
				//icon = new ImageIcon(ImageIO.read(new File("ftk.jpg")));
				bb = new JButton("Disease Control Unit");
				bb.setSize(60, 30);
				//bb.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				units.add(bb);
				break;
			}
			case 4: {
				//icon = new ImageIcon(ImageIO.read(new File("ftk.jpg")));
				bb = new JButton("Gas Control Unit");
				bb.setSize(60, 30);
				//bb.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				units.add(bb);
				break;
			}
			
			}
			
			
			
			
			
		}
		JPanel info = new JPanel();
		JButton nextCycle = new JButton("Next Cycle");
		info.add(nextCycle);
		add(info, BorderLayout.SOUTH);
		
		add(rescue, BorderLayout.CENTER);
		//this.add(background, BorderLayout.CENTER);

		revalidate();
		repaint();
	}
public static void main (String args[]) throws IOException {
	MyView m = new MyView();
}
@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
}
}
