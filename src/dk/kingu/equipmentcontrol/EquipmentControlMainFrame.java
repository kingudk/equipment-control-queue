package dk.kingu.equipmentcontrol;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontMetrics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class EquipmentControlMainFrame extends JFrame implements ComponentListener {

	private JPanel contentPane;
	private JSplitPane splitPane;
	private JLabel pistolCountLabel;
	private JLabel rifleCountLabel;
	
	private PlaceHolder currentRifle = new PlaceHolder();
	private PlaceHolder currentPistol = new PlaceHolder();
	
	private Action incrementRifle = new UpdateAction(currentRifle);
	private Action incrementPistol = new UpdateAction(currentPistol);
	
	private final static String INCREMENT_RIFLE_KEY = "incrementRifle";
	private final static String INCREMENT_PISTOL_KEY = "incrementPistol";
	
	
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
		Font textFont = new Font("Dialog", Font.PLAIN, 24);
		Font countFont = new Font("Dialog", Font.PLAIN, 150);
		Border panelBorder = new EmptyBorder(50, 5, 5, 5);	
		Border labelBorder = new EmptyBorder(5, 5, 25, 5);
		
		JPanel riflePanel = new JPanel();
		riflePanel.setBorder(panelBorder);
		riflePanel.setMinimumSize(minimumSize);
		splitPane.setLeftComponent(riflePanel);
		
		JSplitPane rifleSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
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
		
		JPanel pistolPanel = new JPanel();
		pistolPanel.setBorder(panelBorder);
		pistolPanel.setMinimumSize(minimumSize);
		splitPane.setRightComponent(pistolPanel);
		
		JSplitPane pistolSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
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
		
		contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('r'), INCREMENT_RIFLE_KEY);
		contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('p'), INCREMENT_PISTOL_KEY);
		
		contentPane.getActionMap().put(INCREMENT_RIFLE_KEY, incrementRifle);
		contentPane.getActionMap().put(INCREMENT_PISTOL_KEY, incrementPistol);
	}

	protected void update() {
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
	
	private class UpdateAction extends AbstractAction {

		private PlaceHolder counter;
		
		UpdateAction(PlaceHolder counter) {
			this.counter = counter;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
		//	System.out.println("got event " + arg0);
			counter.increment();
			update();
		}
	}

}
