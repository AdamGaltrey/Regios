package couk.Adamki11s.Regios.CustomExceptions;

public class RegionExistanceException extends Exception {

	private static final long serialVersionUID = 3693626204391099730L;

	String name;

	public RegionExistanceException(String name) {
		this.name = name;
	}

	@Override
	public void printStackTrace() {
		System.out.println("------------------------------");
		System.out.println("[Regios][Exception] No Region with the name '" + this.name + "' exists!");
		System.out.println("------------------------------");
		super.printStackTrace();
		System.out.println("------------------------------");
	}


}
