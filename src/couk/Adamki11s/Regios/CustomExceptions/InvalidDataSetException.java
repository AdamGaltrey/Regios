package couk.Adamki11s.Regios.CustomExceptions;

public class InvalidDataSetException extends Exception {

	private static final long serialVersionUID = -439035065502578900L;

	String set;

	public InvalidDataSetException(String set) {
		this.set = set;
	}

	@Override
	public void printStackTrace() {
		System.out.println("------------------------------");
		System.out.println("[Regios][Exception] The following properties in the dataset were null : \n '" + set);
		System.out.println("------------------------------");
		super.printStackTrace();
		System.out.println("------------------------------");
	}

}
