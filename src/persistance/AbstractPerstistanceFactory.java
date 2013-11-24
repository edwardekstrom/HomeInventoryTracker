package persistance;

public abstract class AbstractPerstistanceFactory {
	
	/**Builds a Java Persistance if 1 and DB Persistance if 0
	 * 
	 * @param enumeratedType
	 * 
	 * @precondition passed and enum relating to the needed persistance, and no persistor exists
	 * @postcondition given the correct persistance
	 * 
	 * @return returns the correct type of constructor given an enum
	 */
	public Persistor buildPersistor(int enumeratedType){
		return null;
	}

}
