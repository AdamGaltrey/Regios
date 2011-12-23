package couk.Adamki11s.Regios.Economy;

import couk.Adamki11s.Regios.Mutable.MutableAdministration;
import couk.Adamki11s.Regios.Mutable.MutableEconomy;
import couk.Adamki11s.Regios.Mutable.MutableMessages;
import couk.Adamki11s.Regios.Regions.Region;

public class Buying {
	
	MutableAdministration admin = new MutableAdministration();
	MutableMessages msg = new MutableMessages();
	MutableEconomy econ = new MutableEconomy();
	
	public void buy(String seller, String buyer, Region r, int value){
		econ.editForSale(r, false);
		admin.setOwner(r, buyer);
		r.setOwner(buyer);
		EconomyPending.sendAppropriatePending(seller, buyer, r.getName(), value);
	}

}
