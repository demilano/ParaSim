package movTest;

import javax.swing.JFrame;

public class Main {

	/**A petri dish with movement test
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setTitle("Frame");
		frame.add(new dish());
		frame.setResizable(false);
		
		frame.setSize(845,588);
		//panel1.setVisible(true);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}