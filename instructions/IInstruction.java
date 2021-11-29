package instructions;

public class IInstruction extends Instruction {

	String name;
	int immediate;
	int rn;
	int rd;
	
	public IInstruction (String name, String immediate, String Rn, String Rd) {
		this.name = name;
		this.immediate = Integer.parseInt(immediate,2);
		rn = Integer.parseInt(Rn,2);
		rd = Integer.parseInt(Rd,2);
	}
	
	@Override
	public void printInstruction(){
		System.out.println(name + " X" + rd + " [" + rn + " #" + immediate + "]");
	}
	
}
