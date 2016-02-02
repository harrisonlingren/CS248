// Nate Dodge, Harrison Lingren, Chance Seger, Jacob Wierman
// Tic-Tac-Toe GUI found on University of North Carolina at Chapel Hill

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;

public class JTT extends JFrame {
	private static final String TITLE = "Jery-Tac-Toe";
	private static final int WIDTH = 1280;
	private static final int HEIGHT = 720;

	private Container content;
	private JLabel result;
	private static JButton[] cells;
	private JButton exitButton;
	private JButton initButton;
	private CellButtonHandler[] cellHandlers;
	private ExitButtonHandler exitHandler;
	private InitButtonHandler initHandler;
  private ImageIcon neutralIcon = new ImageIcon("unpressed.png");
  private ImageIcon noughtIcon = new ImageIcon("pressedRed.png");
  private ImageIcon crossIcon = new ImageIcon("pressedBlue.png");
  private static JLabel userGun = new JLabel(new ImageIcon("user.png"));
  private static JLabel cpuGun = new JLabel(new ImageIcon("cpu.png"));
  
  
	private static boolean noughts;
	private boolean gameOver;

	public JTT() {
		// Necessary initialization code
		setTitle(TITLE);
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
    
    // Set background
    setLayout(new BorderLayout());
    setContentPane(new JLabel(new ImageIcon("bg.png")));
    setLayout(null);
    
		// Create cells and handlers
		cells = new JButton[9];
		cellHandlers = new CellButtonHandler[9];
		for (int i = 0; i < 9; i++) {
			//char ch = (char) ('0' + i + 1);
			cells[i] = new JButton("",neutralIcon);
			cellHandlers[i] = new CellButtonHandler();
			cells[i].addActionListener(cellHandlers[i]);
		}

		// Create init and exit buttons and handlers
		ImageIcon exitIcon = new ImageIcon("exit.png");
    exitButton = new JButton("",exitIcon);
		exitHandler = new ExitButtonHandler();
		exitButton.addActionListener(exitHandler);
    
    ImageIcon initIcon = new ImageIcon("init.png");
		initButton = new JButton("",initIcon);
		initHandler = new InitButtonHandler();
		initButton.addActionListener(initHandler);

		// Create result label
		result = new JLabel("GAME START", SwingConstants.CENTER);
		result.setForeground(Color.white);

		// Add elements to the grid content pane
		for (int i = 0; i < 9; i++) {
			add(cells[i]);
      cells[i].setHorizontalTextPosition(SwingConstants.CENTER);
      cells[i].setOpaque(false);
      cells[i].setContentAreaFilled(false);
      cells[i].setBorderPainted(false);
		}
    
    add(userGun);
    add(cpuGun);
		
    userGun.setBounds(20,235,223,81);
    userGun.setVisible(false);
    cpuGun.setBounds(1035,235,223,81);
    cpuGun.setVisible(false);
    
    add(initButton);
		add(result);
		add(exitButton);
    
    // set sizes and locations of each button
    result.setBounds(540,560,100,100);
    exitButton.setBounds(720,560,102,102);
    initButton.setBounds(420,540,127,131);
    
    cells[0].setBounds(300,100,88,88);
    cells[1].setBounds(585,100,88,88);
    cells[2].setBounds(870,100,88,88);
    cells[3].setBounds(435,260,88,88);
    cells[4].setBounds(585,260,88,88);
    cells[5].setBounds(750,260,88,88);
    cells[6].setBounds(300,440,88,88);
    cells[7].setBounds(585,440,88,88);
    cells[8].setBounds(870,440,88,88);
    
    // make exit/reset buttons transparent
    exitButton.setHorizontalTextPosition(SwingConstants.CENTER);
    exitButton.setOpaque(false);
    exitButton.setContentAreaFilled(false);
    exitButton.setBorderPainted(false);
    initButton.setHorizontalTextPosition(SwingConstants.CENTER);
    initButton.setOpaque(false);
    initButton.setContentAreaFilled(false);
    initButton.setBorderPainted(false);
    
    result.setFont(result.getFont().deriveFont(14.0f));
    
    
		// Initialize
		init();
	}

	public void init() {
		// Initialize booleans
		noughts = true;
		gameOver = false;

		userGun.setVisible(false);
		cpuGun.setVisible(false);
		// Initialize text in buttons
		for (int i = 0; i < 9; i++) {
			char ch = (char) ('0' + i + 1);
			cells[i].setText("" + ch);
      cells[i].setIcon(neutralIcon);
		}

		// Initialize result label
		result.setText("");

		// get first player
		promptUser();

		setVisible(true);
	}

	// needs something to determine if it's the CPU move or not then calls only when true
	public static void compMove() {

		// offensive completion
		if ((cells[0].getText().equals("X") && cells[0].getText().equals(cells[1].getText()))
				&& cells[2].getText().equals("3")) {
			cells[2].doClick();
		} else if ((cells[0].getText().equals("X") && cells[0].getText().equals(cells[2].getText()))
				&& cells[1].getText().equals("2")) {
			cells[1].doClick();
		} else if ((cells[1].getText().equals("X") && cells[1].getText().equals(cells[2].getText()))
				&& cells[0].getText().equals("1")) {
			cells[0].doClick();
		}

		else if ((cells[0].getText().equals("X") && cells[0].getText().equals(cells[4].getText()))
				&& cells[8].getText().equals("9")) {
			cells[8].doClick();
		} else if ((cells[0].getText().equals("X") && cells[0].getText().equals(cells[8].getText()))
				&& cells[4].getText().equals("5")) {
			cells[4].doClick();
		} else if ((cells[4].getText().equals("X") && cells[4].getText().equals(cells[8].getText()))
				&& cells[0].getText().equals("1")) {
			cells[0].doClick();
		}

		else if ((cells[0].getText().equals("X") && cells[0].getText().equals(cells[3].getText()))
				&& cells[7].getText().equals("8")) {
			cells[7].doClick();
		} else if ((cells[0].getText().equals("X") && cells[0].getText().equals(cells[7].getText()))
				&& cells[3].getText().equals("4")) {
			cells[3].doClick();
		} else if ((cells[3].getText().equals("X") && cells[3].getText().equals(cells[7].getText()))
				&& cells[0].getText().equals("1")) {
			cells[0].doClick();
		}

		else if ((cells[1].getText().equals("X") && cells[1].getText().equals(cells[3].getText()))
				&& cells[6].getText().equals("7")) {
			cells[6].doClick();
		} else if ((cells[1].getText().equals("X") && cells[1].getText().equals(cells[6].getText()))
				&& cells[3].getText().equals("4")) {
			cells[3].doClick();
		} else if ((cells[3].getText().equals("X") && cells[3].getText().equals(cells[6].getText()))
				&& cells[1].getText().equals("2")) {
			cells[1].doClick();
		}

		else if ((cells[1].getText().equals("X") && cells[1].getText().equals(cells[5].getText()))
				&& cells[8].getText().equals("9")) {
			cells[8].doClick();
		} else if ((cells[1].getText().equals("X") && cells[1].getText().equals(cells[8].getText()))
				&& cells[5].getText().equals("6")) {
			cells[5].doClick();
		} else if ((cells[5].getText().equals("X") && cells[5].getText().equals(cells[8].getText()))
				&& cells[1].getText().equals("2")) {
			cells[1].doClick();
		}

		else if ((cells[2].getText().equals("X") && cells[2].getText().equals(cells[4].getText()))
				&& cells[6].getText().equals("7")) {
			cells[6].doClick();
		} else if ((cells[2].getText().equals("X") && cells[2].getText().equals(cells[6].getText()))
				&& cells[4].getText().equals("5")) {
			cells[4].doClick();
		} else if ((cells[4].getText().equals("X") && cells[4].getText().equals(cells[6].getText()))
				&& cells[2].getText().equals("3")) {
			cells[2].doClick();
		}

		else if ((cells[2].getText().equals("X") && cells[2].getText().equals(cells[5].getText()))
				&& cells[7].getText().equals("8")) {
			cells[7].doClick();
		} else if ((cells[2].getText().equals("X") && cells[2].getText().equals(cells[7].getText()))
				&& cells[5].getText().equals("6")) {
			cells[5].doClick();
		} else if ((cells[5].getText().equals("X") && cells[5].getText().equals(cells[7].getText()))
				&& cells[2].getText().equals("3")) {
			cells[2].doClick();
		}

		else if ((cells[3].getText().equals("X") && cells[3].getText().equals(cells[4].getText()))
				&& cells[5].getText().equals("6")) {
			cells[5].doClick();
		} else if ((cells[3].getText().equals("X") && cells[3].getText().equals(cells[5].getText()))
				&& cells[4].getText().equals("5")) {
			cells[4].doClick();
		} else if ((cells[4].getText().equals("X") && cells[4].getText().equals(cells[5].getText()))
				&& cells[3].getText().equals("4")) {
			cells[3].doClick();
		}

		else if ((cells[6].getText().equals("X") && cells[6].getText().equals(cells[7].getText()))
				&& cells[8].getText().equals("9")) {
			cells[8].doClick();
		} else if ((cells[6].getText().equals("X") && cells[6].getText().equals(cells[8].getText()))
				&& cells[7].getText().equals("8")) {
			cells[7].doClick();
		} else if ((cells[7].getText().equals("X") && cells[7].getText().equals(cells[8].getText()))
				&& cells[6].getText().equals("7")) {
			cells[6].doClick();
		}
		
		// defensive blocking
		else if ((cells[0].getText().equals("O") && cells[0].getText().equals(cells[1].getText()))
				&& cells[2].getText().equals("3")) {
			cells[2].doClick();
		} else if ((cells[0].getText().equals("O") && cells[0].getText().equals(cells[2].getText()))
				&& cells[1].getText().equals("2")) {
			cells[1].doClick();
		} else if ((cells[1].getText().equals("O") && cells[1].getText().equals(cells[2].getText()))
				&& cells[0].getText().equals("1")) {
			cells[0].doClick();
		}

		else if ((cells[0].getText().equals("O") && cells[0].getText().equals(cells[4].getText()))
				&& cells[8].getText().equals("9")) {
			cells[8].doClick();
		} else if ((cells[0].getText().equals("O") && cells[0].getText().equals(cells[8].getText()))
				&& cells[4].getText().equals("5")) {
			cells[4].doClick();
		} else if ((cells[4].getText().equals("O") && cells[4].getText().equals(cells[8].getText()))
				&& cells[0].getText().equals("1")) {
			cells[0].doClick();
		}

		else if ((cells[0].getText().equals("O") && cells[0].getText().equals(cells[3].getText()))
				&& cells[7].getText().equals("8")) {
			cells[7].doClick();
		} else if ((cells[0].getText().equals("O") && cells[0].getText().equals(cells[7].getText()))
				&& cells[3].getText().equals("4")) {
			cells[3].doClick();
		} else if ((cells[3].getText().equals("O") && cells[3].getText().equals(cells[7].getText()))
				&& cells[0].getText().equals("1")) {
			cells[0].doClick();
		}

		else if ((cells[1].getText().equals("O") && cells[1].getText().equals(cells[3].getText()))
				&& cells[6].getText().equals("7")) {
			cells[6].doClick();
		} else if ((cells[1].getText().equals("O") && cells[1].getText().equals(cells[6].getText()))
				&& cells[3].getText().equals("4")) {
			cells[3].doClick();
		} else if ((cells[3].getText().equals("O") && cells[3].getText().equals(cells[6].getText()))
				&& cells[1].getText().equals("2")) {
			cells[1].doClick();
		}

		else if ((cells[1].getText().equals("O") && cells[1].getText().equals(cells[5].getText()))
				&& cells[8].getText().equals("9")) {
			cells[8].doClick();
		} else if ((cells[1].getText().equals("O") && cells[1].getText().equals(cells[8].getText()))
				&& cells[5].getText().equals("6")) {
			cells[5].doClick();
		} else if ((cells[5].getText().equals("O") && cells[5].getText().equals(cells[8].getText()))
				&& cells[1].getText().equals("2")) {
			cells[1].doClick();
		}

		else if ((cells[2].getText().equals("O") && cells[2].getText().equals(cells[4].getText()))
				&& cells[6].getText().equals("7")) {
			cells[6].doClick();
		} else if ((cells[2].getText().equals("O") && cells[2].getText().equals(cells[6].getText()))
				&& cells[4].getText().equals("5")) {
			cells[4].doClick();
		} else if ((cells[4].getText().equals("O") && cells[4].getText().equals(cells[6].getText()))
				&& cells[2].getText().equals("3")) {
			cells[2].doClick();
		}

		else if ((cells[2].getText().equals("O") && cells[2].getText().equals(cells[5].getText()))
				&& cells[7].getText().equals("8")) {
			cells[7].doClick();
		} else if ((cells[2].getText().equals("O") && cells[2].getText().equals(cells[7].getText()))
				&& cells[5].getText().equals("6")) {
			cells[5].doClick();
		} else if ((cells[5].getText().equals("O") && cells[5].getText().equals(cells[7].getText()))
				&& cells[2].getText().equals("3")) {
			cells[2].doClick();
		}

		else if ((cells[3].getText().equals("O") && cells[3].getText().equals(cells[4].getText()))
				&& cells[5].getText().equals("6")) {
			cells[5].doClick();
		} else if ((cells[3].getText().equals("O") && cells[3].getText().equals(cells[5].getText()))
				&& cells[4].getText().equals("5")) {
			cells[4].doClick();
		} else if ((cells[4].getText().equals("O") && cells[4].getText().equals(cells[5].getText()))
				&& cells[3].getText().equals("4")) {
			cells[3].doClick();
		}

		else if ((cells[6].getText().equals("O") && cells[6].getText().equals(cells[7].getText()))
				&& cells[8].getText().equals("9")) {
			cells[8].doClick();
		} else if ((cells[6].getText().equals("O") && cells[6].getText().equals(cells[8].getText()))
				&& cells[7].getText().equals("8")) {
			cells[7].doClick();
		} else if ((cells[7].getText().equals("O") && cells[7].getText().equals(cells[8].getText()))
				&& cells[6].getText().equals("7")) {
			cells[6].doClick();
		}

		// specific cases to prevent losing
		else if (cells[4].getText().equals("X") && cells[8].getText().equals("O") && cells[3].getText().equals("4")) {
			cells[3].doClick();
		} else if (cells[4].getText().equals("X") && cells[6].getText().equals("O") && cells[5].getText().equals("6")) {
			cells[5].doClick();
		}
		
		// first move based on location of user's move
		else if ((cells[0].getText().equals("O") || (cells[6].getText().equals("O"))) && cells[3].getText().equals("4")) {
			cells[3].doClick();
		} else if ((cells[2].getText().equals("O") || (cells[8].getText().equals("O"))) && cells[5].getText().equals("6")) {
			cells[5].doClick();
		} else if ((cells[1].getText().equals("O") || (cells[7].getText().equals("O"))) && cells[5].getText().equals("6")) {
			cells[5].doClick();
		} else if ((cells[3].getText().equals("O") || (cells[5].getText().equals("O"))) && cells[4].getText().equals("5")) {
			cells[4].doClick();
		} else if (cells[4].getText().equals("O") && cells[3].getText().equals("4")) {
			cells[3].doClick();
		} 
		
		// prevents stupid moves
		else if (cells[0].getText().equals("1")) {
			cells[0].doClick();
		} else if ((cells[0].getText().equals("1") || cells[0].getText().equals("X")) && (cells[1].getText().equals("2") || cells[1].getText().equals("X")) && (cells[2].getText().equals("3"))) {
			cells[2].doClick();
		} else if ((cells[0].getText().equals("1") || cells[0].getText().equals("X")) && (cells[4].getText().equals("5")) && (cells[8].getText().equals("9") || cells[8].getText().equals("X"))) {
			cells[4].doClick();
		} else if ((cells[0].getText().equals("1") || cells[0].getText().equals("X")) && (cells[3].getText().equals("4")) && (cells[7].getText().equals("8") || cells[7].getText().equals("X"))) {
			cells[3].doClick();
		} else if ((cells[1].getText().equals("2") || cells[1].getText().equals("X")) && (cells[3].getText().equals("4")) && (cells[6].getText().equals("7") || cells[6].getText().equals("X"))) {
			cells[3].doClick();
		} else if ((cells[1].getText().equals("2") || cells[1].getText().equals("X")) && (cells[5].getText().equals("6")) && (cells[8].getText().equals("9") || cells[8].getText().equals("X"))) {
			cells[5].doClick();
		} else if ((cells[2].getText().equals("3") || cells[2].getText().equals("X")) && (cells[4].getText().equals("5")) && (cells[6].getText().equals("7") || cells[6].getText().equals("X"))) {
			cells[4].doClick();
		} else if ((cells[2].getText().equals("3") || cells[2].getText().equals("X")) && (cells[5].getText().equals("6")) && (cells[7].getText().equals("8") || cells[7].getText().equals("X"))) {
			cells[5].doClick();
		} else if ((cells[3].getText().equals("4") || cells[3].getText().equals("X")) && (cells[4].getText().equals("5")) && (cells[5].getText().equals("6") || cells[5].getText().equals("X"))) {
			cells[4].doClick();
		} else if ((cells[6].getText().equals("7") || cells[6].getText().equals("X")) && (cells[7].getText().equals("8")) && (cells[8].getText().equals("9") || cells[8].getText().equals("X"))) {
			cells[7].doClick();
		} 
		
		// if nothing else move to an empty spot
		else if (cells[4].getText().equals("5")) {
			cells[4].doClick();
		} else if (cells[1].getText().equals("2")) {
			cells[1].doClick();
		} else if (cells[2].getText().equals("3")) {
			cells[2].doClick();
		} else if (cells[5].getText().equals("6")) {
			cells[5].doClick();
		} else if (cells[6].getText().equals("7")) {
			cells[6].doClick();
		} else if (cells[7].getText().equals("8")) {
			cells[7].doClick();
		} else if (cells[8].getText().equals("9")) {
			cells[8].doClick();
		}
		
	}
  
	// checks to see if someone won the game
	public boolean checkWinner() {
		if (cells[0].getText().equals(cells[1].getText())
				&& cells[1].getText().equals(cells[2].getText())) {
			return true;
		} else if (cells[0].getText().equals(cells[4].getText())
				&& cells[4].getText().equals(cells[8].getText())) {
			return true;
		} else if (cells[0].getText().equals(cells[3].getText())
				&& cells[3].getText().equals(cells[7].getText())) {
			return true;
		} else if (cells[1].getText().equals(cells[3].getText())
				&& cells[3].getText().equals(cells[6].getText())) {
			return true;
		} else if (cells[1].getText().equals(cells[5].getText())
				&& cells[5].getText().equals(cells[8].getText())) {
			return true;
		} else if (cells[2].getText().equals(cells[4].getText())
				&& cells[4].getText().equals(cells[6].getText())) {
			return true;
		} else if (cells[2].getText().equals(cells[5].getText())
				&& cells[5].getText().equals(cells[7].getText())) {
			return true;
		} else if (cells[3].getText().equals(cells[4].getText())
				&& cells[4].getText().equals(cells[5].getText())) {
			return true;
		} else if (cells[6].getText().equals(cells[7].getText())
				&& cells[7].getText().equals(cells[8].getText())) {
			return true;
		} else if (!cells[0].getText().equals("1")
				&& !cells[1].getText().equals("2")
				&& !cells[2].getText().equals("3")
				&& !cells[3].getText().equals("4")
				&& !cells[4].getText().equals("5")
				&& !cells[5].getText().equals("6")
				&& !cells[6].getText().equals("7")
				&& !cells[7].getText().equals("8")
				&& !cells[8].getText().equals("9")) {
			return true;
		} else {
			return false;
		}
	}

	public static void promptUser() {
		// prompt for User or Computer first
		String inputValue = JOptionPane
				.showInputDialog("Who is going first? \n Type \'U\' for User, type \'C\' for Computer");

		if (inputValue.equals("U") || inputValue.equals("u")) {
			// continue
			noughts = true;
		} else if (inputValue.equals("C") || inputValue.equals("c")) {
			noughts = false;
      compMove();
		} else {
			JOptionPane.showMessageDialog(null,
					"Invalid input. Please try again.", "alert",
					JOptionPane.ERROR_MESSAGE);
			promptUser();
		}
	}

	public static void main(String[] args) 
    throws InterruptedException {
		JTT gui = new JTT();
	}

	private class CellButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// If game over, ignore
			if (gameOver) {
				return;
			}

			// Get button pressed
			JButton pressed = (JButton) (e.getSource());

			// Get text of button
			String text = pressed.getText();

			// If noughts or crosses, ignore
			if (text.equals("O") || text.equals("X")) {
				return;
			}

			// Add nought or cross
			if (noughts) {
				pressed.setText("O");
				pressed.setIcon(noughtIcon);
			} else {
				pressed.setText("X");
        pressed.setIcon(crossIcon);
			}

			// Check winner
			if (checkWinner()) {
				// End of game
        
				gameOver = true;

				// Display winner message
				if (!cells[0].getText().equals("1")
						&& !cells[1].getText().equals("2")
						&& !cells[2].getText().equals("3")
						&& !cells[3].getText().equals("4")
						&& !cells[4].getText().equals("5")
						&& !cells[5].getText().equals("6")
						&& !cells[6].getText().equals("7")
						&& !cells[7].getText().equals("8")
						&& !cells[8].getText().equals("9")) {
					result.setText("DRAW GAME");
				} else {
					if (noughts) {
						// result.setText("USER WIN");
            userGun.setVisible(true);
            cpuGun.setVisible(false);
					} else {
						// result.setText("CPU WIN");
            userGun.setVisible(false);
            cpuGun.setVisible(true);
					}
				}
			} else {
				// Change player
				noughts = !noughts;

				// Display player message
				if (noughts) {
					// result.setText("Turn: USER");
				} else {
					// result.setText("Turn:  CPU");
					compMove();
				}
			}
		}
	}

	// closes window if exit button is pressed
	private class ExitButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

	// restarts game if clear button is pressed
	private class InitButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			init();
		}
	}
}  