package MainFile;

import java.io.File;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;

import instructions.*;

public class MainFile {

	static ArrayList <String> opcodes = new ArrayList <String>();
	
	static HashMap <String,String> codeToName = new HashMap <String,String>();

	public static void main (String [] args) throws IOException{
		
		 ArrayList <Instruction> programInstructions = new ArrayList <Instruction> ();
		 Arraylist <int> addressList = new ArrayList <int>();
		 File file = new File(args[0]);
		 byte[] fileData = new byte[(int) file.length()];
		 try{
		 	DataInputStream dis = new DataInputStream(new FileInputStream(file));
		 	dis.readFully(fileData);
		 	dis.close();
		 } catch(IOException e){
		 	throw e;
		 }
		 
		 for (int i = 0; i < fileData.length; i=i+4) {
			 String bitString = "";
			 bitString = bitString + Integer.toBinaryString(fileData[0] & 0xFF);
			 bitString = bitString + Integer.toBinaryString(fileData[1] & 0xFF);
			 bitString = bitString + Integer.toBinaryString(fileData[2] & 0xFF);
			 bitString = bitString +  Integer.toBinaryString(fileData[3] & 0xFF);
			 
			 Instruction toAdd = getInstruction(bitString);
			 
			 if (toAdd instanceof BInstrucion || toAdd instanceof CBInstrucion) {
				 if (toAdd instanceof BInstrucion) {
					 toAdd = (BInstruction) toAdd;
				 } else {
					 toAdd = (CBInstruciton) toAdd;
				 }
				 toAdd.setBranchName((i/4) + Integer.parseInt(toAdd.BR_address));

			 
			 programInstructions.add(toAdd);
		 }
		 
		 
		
	}
	
	
	public static Instruction getInstruction (String bitString) {
		
		ArrayList <String> copyCodes = new ArrayList <String>();
		
		for (int i = 0; i < opcodes.size(); i++) {
			copyCodes.add(opcodes.get(i));
		}
		
		int currIndex = 0;
		while (copyCodes.size() > 1) {
			char currChar = bitString.charAt(currIndex);
			for (int i = 0; i < copyCodes.size(); i++) {
				if (copyCodes.get(i).charAt(currIndex) != currChar) {
					copyCodes.remove(i);
					i = i - 1;
				}
			}
			currIndex = currIndex + 1;
		}
		
		String name = codeToName.get(copyCodes.get(0));
		
		String [] nameAndType = name.split(",");
		
		Instruction returnInstruction = null;
		
		switch (nameAndType[1]) {
		case "R":
			if (nameAndType [0].equals("LSL") || nameAndType [0].equals("LSR")) {
				returnInstruction = new ShiftInstruction(nameAndType[0],bitString.substring(11,16),bitString.substring(16,22),bitString.substring(22,27),bitString.substring(27,32));
			} else {
				returnInstruction = new RInstruction(nameAndType[0],bitString.substring(11,16),bitString.substring(16,22),bitString.substring(22,27),bitString.substring(27,32));
			}
		case "I":
			returnInstruction = new IInstruction(nameAndType[0],bitString.substring(10,22),bitString.substring(22,27),bitString.substring(27,32));
		case "B":
			returnInstruction = new BInstruction(nameAndType[0],bitString.substring(7));
		case "CB":
			returnInstruction = new CBInstruction(nameAndType[0],bitString.substring(7,27),bitString.substring(27));
		case "D":
			returnInstruction = new DInstruction(nameAndType[0],bitString.substring(11,20),bitString.substring(20,22),bitString.substring(22,27),bitString.substring(27,32));
		default:
		   System.out.println("hey Jeffrey you forgot " +nameAndType[1] +" idiot");
		
		}
	
		return returnInstruction;
	}
	
	
	public static void initInstructions () {
		
		opcodes.add("10001011000");
		codeToName.put("10001011000", "ADD,R");
		
		opcodes.add("1001000100");
		codeToName.put("1001000100", "ADDI,I");
		
		opcodes.add("10001010000");
		codeToName.put("10001010000", "AND,R");
		
		opcodes.add("1001001000");
		codeToName.put("1001001000", "ANDI,I");
		
		opcodes.add("000101");
		codeToName.put("000101", "B,B");
		
		opcodes.add("01010100");
		codeToName.put("01010100", "B.cond,CB");
		
		opcodes.add("100101");
		codeToName.put("100101", "BL,B");
		
		opcodes.add("11010110000");
		codeToName.put("11010110000", "BR,R");

		opcodes.add("10110101");
		codeToName.put("10110101", "CBNZ,CB");
		
		opcodes.add("10110100");
		codeToName.put("10110100", "CBZ,CB");
		
		opcodes.add("11001010000");
		codeToName.put("11001010000", "EOR,R");
		
		opcodes.add("1101001000");
		codeToName.put("1101001000", "EORI,I");
				
		opcodes.add("11111000010");
		codeToName.put("11111000010", "LDUR,D");
		
		opcodes.add("11010011011");
		codeToName.put("11010011011", "LSL,R");
		
		opcodes.add("11010011010");
		codeToName.put("11010011010", "LSR,R");
		
		opcodes.add("10101010000");
		codeToName.put("10101010000", "ORR,R");
		
		opcodes.add("1011001000");
		codeToName.put("1011001000", "ORRI,I");
		
		opcodes.add("11111000000");
		codeToName.put("11111000000", "STUR,D");
		
		opcodes.add("11001011000");
		codeToName.put("11001011000", "SUB,R");
		
		opcodes.add("1101000100");
		codeToName.put("1101000100", "SUBI,I");
		
		opcodes.add("1111000100");
		codeToName.put("1111000100", "SUBIS,I");
		
		opcodes.add("11101011000");
		codeToName.put("11101011000", "SUBS,R");
		
		opcodes.add("10011011000");
		codeToName.put("10011011000", "MUL,R");
		
		opcodes.add("PRNT");
		codeToName.put("11111111101", "PRNT,R");
		
		opcodes.add("PRNL");
		codeToName.put("11111111101", "PRNL,R");
		
		opcodes.add("DUMP");
		codeToName.put("11111111110", "DUMP,R");
		
		opcodes.add("HALT");
		codeToName.put("11111111111", "HALT,R");
		
	}
	
	
	
}
