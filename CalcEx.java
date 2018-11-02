/*

Math expression evaluator program using dynamic array stack and linked list stack
Name: Deepak Pagaro

email: dpagaro@gmu.edu
assignment start date: October 3,2017
*/







class CalcEx{
	public static void main(String[] args) {
		try {
			if(args.length==0) {
				System.out.println("Enter filename containing the sample math expressions as input\n");
				
			}
			Solver.solve(args[0]);
		}catch(Exception e) {
			System.out.println("Please provide a valid sample input file");
		}
	}






	
}
