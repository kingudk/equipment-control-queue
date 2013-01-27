package dk.kingu.equipmentcontrol;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class EquipmentControlMainFrame extends JFrame implements ComponentListener, UpdateableDisplay {

	private JPanel contentPane;
	private JSplitPane splitPane;
	private JLabel pistolCountLabel;
	private JLabel rifleCountLabel;
	
	private PlaceHolder currentRifle = new PlaceHolder();
	private PlaceHolder currentPistol = new PlaceHolder();
	
	private Action incrementRifle = new UpdateAction(currentRifle, this);
	private Action resetRifle = new ResetAction(currentRifle, this);
	private Action setRifle = new SetNumberAction(currentRifle, this);
	private Action incrementPistol = new UpdateAction(currentPistol, this);
	private Action resetPistol = new ResetAction(currentPistol, this);
	private Action setPistol = new SetNumberAction(currentPistol, this);
	
	private final static String INCREMENT_RIFLE_KEY = "incrementRifle";
	private final static String INCREMENT_PISTOL_KEY = "incrementPistol";
	private final static String RESET_RIFLE_KEY = "resetRifle";
	private final static String RESET_PISTOL_KEY = "resetPistol";
	private final static String SET_RIFLE_KEY = "setRifle";
	private final static String SET_PISTOL_KET = "setPistol";
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EquipmentControlMainFrame frame = new EquipmentControlMainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EquipmentControlMainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.addComponentListener(this);
		
		
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setAlignmentX(Component.CENTER_ALIGNMENT);
		splitPane.setAlignmentY(Component.CENTER_ALIGNMENT);
		splitPane.setDividerLocation(350);
		splitPane.setDividerSize(1);
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		Dimension minimumSize = new Dimension(100, 50);
		Font textFont = new Font("Dialog", Font.PLAIN, 80);
		Font countFont = new Font("Dialog", Font.PLAIN, 150);
		Border panelBorder = new EmptyBorder(50, 5, 5, 5);	
		Border labelBorder = new EmptyBorder(5, 5, 25, 5);
		
		JPanel riflePanel = new JPanel();
		riflePanel.setBorder(panelBorder);
		riflePanel.setMinimumSize(minimumSize);
		splitPane.setLeftComponent(riflePanel);
		riflePanel.setLayout(new BoxLayout(riflePanel, BoxLayout.Y_AXIS));
		
		JSplitPane rifleSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		rifleSplit.setAlignmentY(Component.CENTER_ALIGNMENT);
		rifleSplit.setAlignmentX(Component.CENTER_ALIGNMENT);
		rifleSplit.setDividerSize(0);
		rifleSplit.setBorder(null);
		riflePanel.add(rifleSplit);
		
		JLabel rifleTextLabel = new JLabel("Current rifle #:");
		rifleTextLabel.setHorizontalAlignment(SwingConstants.CENTER);
		rifleTextLabel.setBorder(labelBorder);
		rifleTextLabel.setFont(textFont);
		rifleSplit.setLeftComponent(rifleTextLabel);
		rifleCountLabel = new JLabel(" ###  ", JLabel.CENTER);
		rifleCountLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		rifleCountLabel.setFont(countFont);
		rifleSplit.setRightComponent(rifleCountLabel);
		
		JPanel rifleButtonPanel = new JPanel();
		rifleButtonPanel.setBorder(null);
		riflePanel.add(rifleButtonPanel);
		JButton setRifleButton = new JButton(setRifle);
		setRifleButton.setText("Set number");
		rifleButtonPanel.add(setRifleButton);
		JButton resetRifleButton = new JButton(resetRifle);
		resetRifleButton.setText("Reset number");
		rifleButtonPanel.add(resetRifleButton);
		
		JPanel pistolPanel = new JPanel();
		pistolPanel.setBorder(panelBorder);
		pistolPanel.setMinimumSize(minimumSize);
		splitPane.setRightComponent(pistolPanel);
		pistolPanel.setLayout(new BoxLayout(pistolPanel, BoxLayout.Y_AXIS));
		
		JSplitPane pistolSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		pistolSplit.setAlignmentY(Component.CENTER_ALIGNMENT);
		pistolSplit.setAlignmentX(Component.CENTER_ALIGNMENT);
		pistolSplit.setDividerSize(0);
		pistolSplit.setBorder(null);
		pistolPanel.add(pistolSplit);
		
		JLabel pistolTextLabel = new JLabel("Current pistol #:");
		pistolTextLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pistolTextLabel.setBorder(labelBorder);
		pistolTextLabel.setFont(textFont);
		pistolSplit.setLeftComponent(pistolTextLabel);
		pistolCountLabel = new JLabel(" ###  ", JLabel.CENTER);
		pistolCountLabel.setFont(countFont);
		pistolSplit.setRightComponent(pistolCountLabel);
		
		JPanel pistolButtonPanel = new JPanel();
		pistolButtonPanel.setBorder(null);
		pistolPanel.add(pistolButtonPanel);
		JButton setPistolButton = new JButton(setPistol);
		setPistolButton.setText("Set number");
		pistolButtonPanel.add(setPistolButton);
		JButton resetPistolButton = new JButton(resetPistol);
		resetPistolButton.setText("Reset number");
		pistolButtonPanel.add(resetPistolButton);
		
		contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('r'), INCREMENT_RIFLE_KEY);
		contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('p'), INCREMENT_PISTOL_KEY);
		
		contentPane.getActionMap().put(INCREMENT_RIFLE_KEY, incrementRifle);
		contentPane.getActionMap().put(INCREMENT_PISTOL_KEY, incrementPistol);
	}

	public void update() {
		rifleCountLabel.setText(currentRifle.getValue());
		pistolCountLabel.setText(currentPistol.getValue());
	}
	
	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentResized(ComponentEvent arg0) {
		splitPane.setDividerLocation(contentPane.getWidth() / 2);		
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
