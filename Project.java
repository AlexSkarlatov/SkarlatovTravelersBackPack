package HW2;

/*
 * name:
 *
 * Date: 2/1/189
 *
 *
 * Description:	this is a project class meant to hold the items and their properties more easily
 * it will likely consist of one constructor,
 * and three getters.
 *
 *
 *
 */





public class Project {
	//data members here
	String projName ;
	int weeks ;
	int value;

	//constructor here
	public Project(String name, int weeks, int value)
	{
		this.projName = name;
		this.weeks = weeks;
		this.value = value;
	}
	
	
	public String getName(){
		return this.projName;
	}
	public int getWeeks(){
		return this.weeks;
	}
	public int getValue(){
		return this.value;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * 	//this works
	@Override
	public String toString()
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		return  this.projName + "   " + this.weeks + "    "		+ this.value ;
	}
	
	
	
	
}
