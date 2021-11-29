package instructions;

public class DInstruction extends Instruction {

	String name;
	int saveReg;
	int reg1;
	int offset;
	
	public DInstruction (String name, String DT_address, String Rn, String Rt, String op) {
		this.name = name;
		saveReg = Integer.parseInt(Rt,2);
		reg1 = Integer.parseInt(Rn,2);
		offset = Integer.parseInt(DT_address,2);
	}
	
	@Override
	public void printInstruction(){
		System.out.println(name + " X" + saveReg + " [" + reg1 + " #" + offset + "]");
	}
	
}
