package model.units;

import exceptions.CannotTreatException;
import exceptions.IncompatibleTargetException;
import model.events.WorldListener;
import model.people.Citizen;
import model.people.CitizenState;
import simulation.Address;
import simulation.Rescuable;

public class Ambulance extends MedicalUnit {

	public Ambulance(String unitID, Address location, int stepsPerCycle, WorldListener worldListener) {
		super(unitID, location, stepsPerCycle, worldListener);
	}

	@Override
	public void treat() throws CannotTreatException {
		if (canTreat(this.getTarget())) {
			getTarget().getDisaster().setActive(false);

			Citizen target = (Citizen) getTarget();
			if (target.getHp() == 0) {
				jobsDone();
				return;
			} else if (target.getBloodLoss() > 0) {
				target.setBloodLoss(target.getBloodLoss() - getTreatmentAmount());
				if (target.getBloodLoss() == 0)
					target.setState(CitizenState.RESCUED);
			}

			else if (target.getBloodLoss() == 0)

				heal();
		} else
			throw new CannotTreatException(this, getTarget(), "Citizen is already Safe");

	}

	public void respond(Rescuable r) throws IncompatibleTargetException, CannotTreatException {
		if (r instanceof Citizen) {
			Citizen c = (Citizen) r;
			if (c.getBloodLoss() == 0)
				throw new CannotTreatException(this, r, "Citizen is already safe.");
			else {

				if (getTarget() != null && ((Citizen) getTarget()).getBloodLoss() > 0
						&& getState() == UnitState.TREATING)
					reactivateDisaster();
				finishRespond(r);
			}

		} else
			throw new IncompatibleTargetException(this, r, "Ambulance can only respond to citizens.");
	}
}
