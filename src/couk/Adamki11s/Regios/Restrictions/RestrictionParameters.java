package couk.Adamki11s.Regios.Restrictions;

public class RestrictionParameters {

	private int regionLimit, regionWidthLimit, regionHeightLimit, regionLengthLimit;


	public RestrictionParameters(int regionLimit, int regionWidthLimit, int regionHeightLimit, int regionLengthLimit) {
		this.regionLimit = regionLimit;
		this.regionWidthLimit = regionWidthLimit;
		this.regionHeightLimit = regionHeightLimit;
		this.regionLengthLimit = regionLengthLimit;
	}
	
	public int getRegionLimit() {
		return regionLimit;
	}

	public int getRegionWidthLimit() {
		return regionWidthLimit;
	}

	public int getRegionHeightLimit() {
		return regionHeightLimit;
	}

	public int getRegionLengthLimit() {
		return regionLengthLimit;
	}
	
}
