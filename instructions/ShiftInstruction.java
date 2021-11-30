package instructions;

public class ShiftInstruction extends Instruction {
	
	String name;
	int saveReg;
	int reg1;
	int shamt;
	
	public ShiftInstruction (String name, String Rm, String shamt, String Rn, String Rd) {
		this.name = name;
		saveReg = Integer.parseInt(Rd,2);
		reg1 = Integer.parseInt(Rn,2);
		shamt = Integer.parseInt(shamt,2);
	}
	
	@Override
	public void printInstruction(){
		System.out.println(name + " X" + saveReg + " X" + reg1 + " #" + shamt);
	}

}
