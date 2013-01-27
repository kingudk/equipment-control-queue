package dk.kingu.equipmentcontrol;

public class PlaceHolder {

	private Integer value;
	
	PlaceHolder() {
		value = new Integer(0);
	}
	
	public void increment() {
		value++;
	}
	
	public String getValue() {
		return "" + value;
	}
	
	public void reset() {
		value = new Integer(0);
	}
	
	public void setValue(Integer value) {
		this.value = value;
	}

}
