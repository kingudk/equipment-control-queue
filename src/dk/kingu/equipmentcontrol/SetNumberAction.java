package dk.kingu.equipmentcontrol;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

public class SetNumberAction extends AbstractAction {

	private PlaceHolder counter;
	private final UpdateableDisplay display;
	private final static String DEFAULT_MSG = "Enter the new number";
	
	public SetNumberAction(PlaceHolder counter, UpdateableDisplay display) {
		this.display = display;
		this.counter = counter;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		counter.setValue(showDialog(""));
		display.update();
	}
	
	
	private Integer showDialog(String message) {
		Integer value;
		String stringValue = (String) JOptionPane.showInputDialog(null, DEFAULT_MSG + message, "Set number",
				JOptionPane.PLAIN_MESSAGE, null, null, counter.getValue());
		
		if(stringValue == null) {
			value = Integer.parseInt(counter.getValue());
		} else if(stringValue.isEmpty() || !stringValue.matches("[0-9]*")) {
			value = showDialog("\n '" + stringValue+ "' is not a valid number");	
		} else {
			value = Integer.parseInt(stringValue);
		}
		
		return value;
	}
	
}
