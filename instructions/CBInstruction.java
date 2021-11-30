package instructions;

import java.util.HashMap;

public class CBInstruction extends BInstruction {
	String name;
	public String BR_address;
	static HashMap <String,String> getCond;
	String cond;
	
	public CBInstruction (String name, String BR_address,String Rt) {
		
		if (getCond == null) {
			initCond();
		}
		this.name = name;
		this.BR_address = BR_address;
		cond = getCond.get(Rt);
	}
	
	
	@Override
	public void printInstruction(){
		//TODO figure out how B instructions are usally laied out
		System.out.println(name + "." + cond + " " + branchName);
	}
	
	public void initCond() {
		getCond = new HashMap <String,String>();
		getCond.put("00000", "EQ");
		getCond.put("00001", "NE");
		getCond.put("00010", "HS");
		getCond.put("00011", "LO");
		getCond.put("00100", "MI");
		getCond.put("00101", "PL");
		getCond.put("00110", "VS");
		getCond.put("00111", "VC");
		getCond.put("01000", "HI");
		getCond.put("01001", "LS");
		getCond.put("01011", "GE");
		getCond.put("01100", "LT");
		getCond.put("01101", "GT");
		getCond.put("01110", "LE");
	}
}
