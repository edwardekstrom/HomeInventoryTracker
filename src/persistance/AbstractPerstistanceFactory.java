package persistance;

public abstract class AbstractPerstistanceFactory {
	
	public Persistor buildPersistor(){
		return null;
	}
	
	public static AbstractPerstistanceFactory returnFactory(int i){
		if(i == 1){
			return new DatabasePersistanceFactory();
		}else{
			return new JavaPersistanceFactory();
		}
	}

}
