package instructions;

public class BInstruction extends Instruction {
	
	String name;
	String BR_address;
	
	public BInstruction (String name, String BR_address) {
		this.name = name;
		this.BR_address = BR_address;
	}
	
	@Override
	public void printInstruction(){
		//TODO figure out how B instructions are usally laied out
		System.out.println(name + " " + BR_address);
	}

}
