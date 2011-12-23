package couk.Adamki11s.Regios.CustomExceptions;

public class FileExistanceException extends Exception {

	private static final long serialVersionUID = 8335083283843506976L;

	String file;
	boolean exists;

	public FileExistanceException(String file, boolean exists) {
		this.file = file;
		this.exists = exists;
	}
	
	@Override
	public void printStackTrace(){
		System.out.println("------------------------------");
		if (exists) {
			System.out.println("[Regios][Exception] The file : '" + file + "' already exists!");
		} else {
			System.out.println("[Regios][Exception] The file : '" + file + "' does not exist!");
		}
		System.out.println("------------------------------");
		super.printStackTrace();
		System.out.println("------------------------------");

	}

}
