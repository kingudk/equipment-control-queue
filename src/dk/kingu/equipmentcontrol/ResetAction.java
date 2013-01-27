package dk.kingu.equipmentcontrol;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class ResetAction extends AbstractAction {

	private PlaceHolder counter;
	private final UpdateableDisplay display;
	
	public ResetAction(PlaceHolder counter, UpdateableDisplay display) {
		this.counter = counter;
		this.display = display;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		counter.reset();
		display.update();
	}

}
