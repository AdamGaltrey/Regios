package couk.Adamki11s.Regios.CustomEvents;

import org.bukkit.event.CustomEventListener;
import org.bukkit.event.Event;

public abstract class RegionEventListener extends CustomEventListener {

	@Override
	public void onCustomEvent(Event event) {
		if (event instanceof RegionEnterEvent) {

			this.onRegionEnter((RegionEnterEvent) event);

		} else if (event instanceof RegionExitEvent) {

			this.onRegionExit((RegionExitEvent) event);

		} else if (event instanceof RegionLightningStrikeEvent) {

			this.onRegionLightningStrike((RegionLightningStrikeEvent) event);

		} else if (event instanceof RegionCreateEvent) {

			this.onRegionCreate((RegionCreateEvent) event);

		} else if (event instanceof RegionDeleteEvent) {

			this.onRegionDelete((RegionDeleteEvent) event);

		} else if (event instanceof RegionLoadEvent) {

			this.onRegionLoad((RegionLoadEvent) event);

		} else if (event instanceof RegionBackupEvent) {

			this.onRegionBackup((RegionBackupEvent) event);

		} else if (event instanceof RegionRestoreEvent) {

			this.onRegionRestore((RegionRestoreEvent) event);

		} else if (event instanceof RegionCommandEvent) {

			this.onRegionCommand((RegionCommandEvent) event);

		}
	}

	public abstract void onRegionEnter(RegionEnterEvent event);

	public abstract void onRegionExit(RegionExitEvent event);

	public abstract void onRegionLightningStrike(RegionLightningStrikeEvent event);

	public abstract void onRegionCreate(RegionCreateEvent event);

	public abstract void onRegionDelete(RegionDeleteEvent event);

	public abstract void onRegionLoad(RegionLoadEvent event);

	public abstract void onRegionBackup(RegionBackupEvent event);

	public abstract void onRegionRestore(RegionRestoreEvent event);

	public abstract void onRegionCommand(RegionCommandEvent event);

}
