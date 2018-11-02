/*

Math expression evaluator program using dynamic array stack and linked list stack
Name: Deepak Pagaro

email: dpagaro@gmu.edu
assignment start date: October 3,2017
*/

// Linked list implementation of stack
class StackLinkedList implements Stack{
	//topElement points to the head of the linked list, also the top of stack 
	Node topElement;
	int top=-1;	
	// Constructor to initialize node object
	public StackLinkedList(){
		topElement=new Node();
		topElement=null;		


	}
	public void push(String element){
		Node temp=new Node();
		Node temp1=new Node();
		//adds node at the beginning if linkedlist(stack) is empty
		if(topElement==null)
		{
			temp.token=element;
			temp.next=null;
			topElement=temp;
			top++;
		}
		//adds node at the start of thelinkedlist(stack) ,also seen as the top ,if stack is not empty
		else{
			temp.token=element;
			temp1=topElement;
			temp.next=temp1;
			topElement=temp;
			top++;
		}
			
	}
	//pop elements from stack
	public String pop(){
		String poppedElement;
		Node temp=new Node();
		if(top==-1){
			System.out.println("Stack empty!!!!\n");
		}
		else{
			temp=topElement;
			poppedElement=topElement.token;			
			temp=temp.next;
			topElement=temp;
			top--;
			return(poppedElement);
			
		}

		return "";
		
	}
// returns the top of the stack
	public String top(){
		return topElement.token;

	}
// tells if the stack is empty
	public boolean isEmpty(){
		if(top==-1) return true;
		else return false;
	}
}