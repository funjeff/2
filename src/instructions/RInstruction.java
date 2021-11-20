package instructions;

public class RInstruction extends Instruction {
	
	String name;
	int saveReg;
	int reg1;
	int reg2;
	
	public RInstruction (String name, String Rm, String shamt, String Rn, String Rd) {
		this.name = name;
		saveReg = Integer.parseInt(Rd,2);
		reg1 = Integer.parseInt(Rn,2);
		reg2 = Integer.parseInt(Rm,2);
	}
	
	@Override
	public void printInstruction(){
		System.out.println(name + " X" + saveReg + " X" + reg1 + " X" + reg2);
	}

}
