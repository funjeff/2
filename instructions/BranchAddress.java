package instructions;

public class BranchAddress extends Instruction {
	String name;
	
	public BranchAddress (String name) {
		this.name = name;
	}
	
	@Override
	public void printInstruction(){
		//TODO figure out how B instructions are usally laied out
		System.out.println(name + ":");
	}
}
