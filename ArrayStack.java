/*

Calculator program using dynamic array stack and linked list stack
Name: Deepak Pagaro

email: dpagaro@gmu.edu
assignment start date: October 3,2017
*/


// Dynamic array stack 
public class ArrayStack implements Stack{
	String[] stack=new String[100];
	int top=-1;	
	//increases the length of array 
	public void expandArray(){
		int newlen=stack.length+10;
		String[] temp=new String[newlen];
		for(int i=0;i<stack.length;i++){
			temp[i]=stack[i];
		}
		stack=temp;
	}
	// push operation for stack
	public void push(String element){
		if(top+1>=stack.length){
			expandArray();
			System.out.println("******");
		}
		
			stack[++top]=element;

		

	}
	//pop operation for stack
	public String pop(){
		String temp;
		if(top==-1){
			System.out.println("Stack is empty!!!\n");
		}
		else{
			temp=stack[top];
			top--;
			return temp;
			
		}
		return "";

	}
	//returns the top element of stack
	public String top(){
		
		return stack[top];
	}
	// checks whether stack is empty
	public boolean isEmpty(){
		if(top==-1) return true;
		else return false;
	}
}
