/*

Math expression evaluator program using dynamic array stack and linked list stack
Name: Deepak Pagaro

email: dpagaro@gmu.edu
assignment start date: October 3,2017
*/


import java.util.*;
import java.io.*;


public class Solver {
	private static ArrayList<String> inputAsIs=new ArrayList<String>();
	private static ArrayList<String> expressions=new ArrayList<String>();

	//this function does the actual  basic math operations 
	public  static String calculate(float op1,float op2,String operator){
		       float result=0;		
			switch(operator){
				case "+":
					result=op1+op2;	
					break;
				case "-":
					result=op1-op2;
					break;
				case "/":
					result=op1/op2;
					break;
				case "*":
					result=op1*op2;
					break;
			}		
			return Float.toString(result);
	}
	//algorithm to  evaluate expression from postfix using linked list implementation of stack	
	public static String evaluateExpressionUsingLinkedListStack(String[] postfix){		
		StackLinkedList sll=new StackLinkedList();
		String result,op1,op2;
		result="";		
		for(int i=0;i<postfix.length;i++)
		{	
			if(postfix[i]==null)
				break;			
			if(sll.isEmpty())
			{
				sll.push(postfix[i]);
			}
			else if(postfix[i].charAt(0)=='*'||postfix[i].charAt(0)=='/'||postfix[i].charAt(0)=='-'||postfix[i].charAt(0)=='+')
			{	
				op2=sll.pop();	
				op1=sll.pop();
				result=calculate(Float.valueOf(op1),Float.valueOf(op2),postfix[i]);
				sll.push(result);
			}
			else {
				sll.push(postfix[i]);
			
			}
		}
		result=sll.pop();
		return result;
	}
	
	//algorithm to calculate evaluate expression from postfix using dynamic array implementation of stack	
	public static String evaluateExpressionUsingArrayStack(String[] postfix){	
		
		ArrayStack as =new ArrayStack();
		String result,op1,op2;
		result="";		
		for(int i=0;i<postfix.length;i++)
		{	
			if(postfix[i]==null)
				break;			
			if(as.isEmpty())
			{
				as.push(postfix[i]);
			}
			else if(postfix[i].charAt(0)=='*'||postfix[i].charAt(0)=='/'||postfix[i].charAt(0)=='-'||postfix[i].charAt(0)=='+')
			{	
				op2=as.pop();	
				op1=as.pop();
				result=calculate(Float.valueOf(op1),Float.valueOf(op2),postfix[i]);
				as.push(result);
			}
			else {
				as.push(postfix[i]);
			
			}
		}
		result=as.pop();
		return result;
	}
	//reads a file and takes expression in string arraylist
	public static void readFile(String fileName){
		
		String test;
		try{
		File filePath=new File(fileName);	
		Scanner fileReader=new Scanner(filePath);
		while(fileReader.hasNextLine()){
			String line=fileReader.nextLine();
			test=line;
			line=line.replace(" ","");
			if(line.length()==0)
				continue;
			expressions.add(line);
			inputAsIs.add(test);
			
			}
		fileReader.close();
		}
		catch(IOException e){
			e.printStackTrace();
	
		}
		
	}
	// algorithm to convert infix expression to postfix using dynamic array stack implementation
	public static String[] postFixUsingDynamicArrayStack(String[] infix){
		
		ArrayStack as=new ArrayStack();
		int postCount=0;	
		String[] postfix=new String[infix.length];
		for(int i=0;i<infix.length;i++)
		{	//checks if it is NOT an operand
			if(infix[i].charAt(0)=='('||infix[i].charAt(0)==')'||infix[i].charAt(0)=='*'||infix[i].charAt(0)=='/'||infix[i].charAt(0)=='-'||infix[i].charAt(0)=='+'){
			
				if(as.isEmpty()){
					
					as.push(infix[i]);

				}
				else if(infix[i].charAt(0)==')'){
			
					while(as.isEmpty()==false){
						if((as.top()).charAt(0)!='(')
						postfix[postCount++]=as.pop();
						else break;
					}
					as.pop();
				}
				else if(infix[i].charAt(0)=='('){
			
						as.push(infix[i]);

				}
				else if(precedenceValue(as.top())<precedenceValue(infix[i]))
				{	
					as.push(infix[i]);

				}
				
				else if(precedenceValue(as.top())>precedenceValue(infix[i]))
				{	
					while(as.isEmpty()==false)
					{						
					if(precedenceValue(as.top())>=precedenceValue(infix[i]))
						postfix[postCount++]=as.pop();
					else break;
					}
					as.push(infix[i]);
				}
				else if(precedenceValue(as.top())==precedenceValue(infix[i])){
					
					postfix[postCount++]=as.pop();
					as.push(infix[i]);
				
				}
			}
			//if it is an operand just add to result		
			else{	
				
				postfix[postCount++]=infix[i];
				
		




			}
			
		}
		//pops remaining elements from stack
		while(as.isEmpty()==false)
		{
			
			postfix[postCount++]=as.pop();
		}
		
		
		return postfix;
	}
// algorithm to convert infix expression to postfix using linked list implementation of stack
	public static String[] postFixUsingLinkedListStack(String[] infix){
		
		StackLinkedList sll=new StackLinkedList();
		int postCount=0;	
		String[] postfix=new String[infix.length];
		for(int i=0;i<infix.length;i++)
		{	//checks if it is NOT an operand
			if(infix[i].charAt(0)=='('||infix[i].charAt(0)==')'||infix[i].charAt(0)=='*'||infix[i].charAt(0)=='/'||infix[i].charAt(0)=='-'||infix[i].charAt(0)=='+'){
			
				if(sll.isEmpty()){
					
					sll.push(infix[i]);

				}
				else if(infix[i].charAt(0)==')'){
			
					while(sll.isEmpty()==false){
						if((sll.top()).charAt(0)!='(')
						postfix[postCount++]=sll.pop();
						else break;
					}
					sll.pop();
				}
				else if(infix[i].charAt(0)=='('){
			
						sll.push(infix[i]);

				}
				else if(precedenceValue(sll.top())<precedenceValue(infix[i]))
				{	
					sll.push(infix[i]);

				}
				
				else if(precedenceValue(sll.top())>precedenceValue(infix[i]))
				{	
					while(sll.isEmpty()==false)
					{						
					if(precedenceValue(sll.top())>=precedenceValue(infix[i]))
						postfix[postCount++]=sll.pop();
					else break;
					}
					sll.push(infix[i]);
				}
				else if(precedenceValue(sll.top())==precedenceValue(infix[i])){
					
					postfix[postCount++]=sll.pop();
					sll.push(infix[i]);
				
				}
			}
			//if it is an operand just add to result		
			else{	
				
				postfix[postCount++]=infix[i];
				
		




			}
			
		}
		//pops remaining elements from stack
		while(sll.isEmpty()==false)
		{
			
			postfix[postCount++]=sll.pop();
		}
		
		
		return postfix;
	}
	// The following function checks if expression has balanced paranthesis
	public static boolean balancedParanthesisErrorCheck(String test){
		int length=test.length();
		int profile=0;
		for(int i=0;i<length;i++){
			if(test.charAt(i)=='(')			
				profile++;
			if(test.charAt(i)==')')
				profile--;
			if(profile<0)
			{
				
				return true;  //if profile goes negative
			}			//that means the paranthesis is not balanced,i.e, first paranthesis is ) or ()) pattern 
		}
		if(profile>0){
			 // if string has more left paranthesis
			return true;
		}
		return false; // if profile is 0 means the string is balanced
	}
// this function checks for invalid characters in expression
	public static boolean invalidCharacterErrorCheck(String test){
	 	int length=test.length();
		boolean flag=false;
		for(int i=0;i<length;i++){
		if(test.charAt(i)=='('||test.charAt(i)==')'||test.charAt(i)=='+'||test.charAt(i)=='-'||test.charAt(i)=='*'||test.charAt(i)=='/')
		{
			continue;
		}
		else if(test.charAt(i)=='0'||test.charAt(i)=='1'||test.charAt(i)=='2'||test.charAt(i)=='3'||test.charAt(i)=='4'||test.charAt(i)=='5'||test.charAt(i)=='6'||test.charAt(i)=='7'||test.charAt(i)=='8'||test.charAt(i)=='9'){
				flag=false;
		}
		else{
				
				return true;
			   
			}
	}
		
		return flag;
	}
// number of operators should be one less than number of operands
// by the rule of mathematics and this function checks error for that rule
public static boolean operatorOperandCountError(String[] token){
	int operatorCount=0,operandCount=0;
	boolean flag=false;	
	for(int i=0;i<token.length;i++){
		if(token[i]==null) break;
		if(token[i].charAt(0)=='*'||token[i].charAt(0)=='/'||token[i].charAt(0)=='-'||token[i].charAt(0)=='+') 
			operatorCount++;
		else
			operandCount++;
	}
	if(operatorCount==(operandCount-1))
		flag=false;
	else
		flag=true;
	return flag;
}
// this function helps in checking the precedence of operator
public static int precedenceValue(String operator)
	{
		int flag=0;
		switch(operator){
			
			case "*":
				flag=2;
				break;
			case "/":
				flag=2;
				break;
			case "+":
				flag=1;
				break;
			case "-":
				flag=1;
				break;
			
			
			
			
		}
		
		return flag;
	}
	public static void solve( String fileName)  {
		String[] postfix;
		String[] expToken;
		String test;
		String result;
		System.out.println("***********************Math Expression Evaluator***********************\n\n");
		try{
						
			
			readFile(fileName);
			for(int i=0;i<expressions.size();i++){
				
				test=expressions.get(i);
				if(invalidCharacterErrorCheck(test)){
					System.out.println("*****************************************************");
					System.out.println("\nInput as it is:"+inputAsIs.get(i));
					System.out.println("Invalid expression: "+test+"\nerror type: invalid character in expression");
					
					continue;



				}
				if(balancedParanthesisErrorCheck(test)){
					System.out.println("*****************************************************");
					System.out.println("\nInput as it is:"+inputAsIs.get(i));
					System.out.println("Invalid expression: "+test+"\nerror type:unbalanced paranthesis in expression");
					
					continue;



				}
				expToken=new String[test.length()];
				// Split function is used to break the expression string into tokens based on the regular expression
				expToken=(test.split("(?<=[\\(-+*/\\)\\D])|(?=[(\\(-+*/\\)\\D])"));
							
				postfix=new String[expToken.length];
				postfix=postFixUsingDynamicArrayStack(expToken);
				if(operatorOperandCountError(postfix))
				{	
					System.out.println("*****************************************************");
					System.out.println("\nInput as it is:"+inputAsIs.get(i));
					System.out.println("Invalid expression:"+test+"\nerror type:invalid number of operator/operand ");
					
					continue;
				}
				System.out.println("*****************************************************");
				System.out.println("\nInput as it is:"+inputAsIs.get(i));
				System.out.print("Expression(infix):");
				for(int j=0;j<expToken.length;j++)
				System.out.print(expToken[j]+" ");
				System.out.println("\nUsing dynamic array stack:");
				System.out.print("Postfix:");
				for(int z=0;z<postfix.length;z++)
				{
					if(postfix[z]==null) break;
				System.out.print(postfix[z]+" ");
				}
				System.out.println();
				result=evaluateExpressionUsingArrayStack(postfix);
				System.out.println("result:"+result);
				System.out.println("\nUsing linked list stack:");
				postfix=postFixUsingLinkedListStack(expToken);
				System.out.print("Postfix:");
				for(int q=0;q<postfix.length;q++)
				{
					if(postfix[q]==null) break;
				System.out.print(postfix[q]+" ");
				}
				System.out.println();
				result=evaluateExpressionUsingLinkedListStack(postfix);
				System.out.println("result:"+result);
				
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
