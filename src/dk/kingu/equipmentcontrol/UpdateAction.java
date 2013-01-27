package dk.kingu.equipmentcontrol;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class UpdateAction extends AbstractAction {

	private PlaceHolder counter;
	private final UpdateableDisplay display;
	
	public UpdateAction(PlaceHolder counter, UpdateableDisplay display) {
		this.counter = counter;
		this.display = display;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
	//	System.out.println("got event " + arg0);
		counter.increment();
		display.update();
	}

}
