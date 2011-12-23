package couk.Adamki11s.Regios.CustomExceptions;

public class RegionPointsNotSetException extends Exception {

	private static final long serialVersionUID = 7185626294201347942L;

	String name;

	public RegionPointsNotSetException(String name) {
		this.name = name;
	}

	@Override
	public void printStackTrace() {
		System.out.println("------------------------------");
		System.out.println("[Regios][Exception] Points not set for Region '" + this.name + "'!");
		System.out.println("------------------------------");
		super.printStackTrace();
		System.out.println("------------------------------");
	}

}
