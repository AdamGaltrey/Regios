package couk.Adamki11s.Regios.CustomExceptions;

public class RegionNameExistsException extends Exception {

	private static final long serialVersionUID = -2549977683630348764L;

	String name;

	public RegionNameExistsException(String name) {
		this.name = name;
	}

	@Override
	public void printStackTrace() {
		System.out.println("------------------------------");
		System.out.println("[Regios][Exception] A Region with the name '" + this.name + "' already exists!");
		System.out.println("------------------------------");
		super.printStackTrace();
		System.out.println("------------------------------");
	}

}
