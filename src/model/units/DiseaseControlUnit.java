package model.units;

import exceptions.CannotTreatException;
import exceptions.IncompatibleTargetException;
import model.events.WorldListener;
import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;
import model.people.CitizenState;
import simulation.Address;
import simulation.Rescuable;

public class DiseaseControlUnit extends MedicalUnit {

	public DiseaseControlUnit(String unitID, Address location, int stepsPerCycle, WorldListener worldListener) {
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
			} else if (target.getToxicity() > 0) {
				target.setToxicity(target.getToxicity() - getTreatmentAmount());
				if (target.getToxicity() == 0)
					target.setState(CitizenState.RESCUED);
			}

			else if (target.getToxicity() == 0)
				heal();
		} else
			throw new CannotTreatException(this, this.getTarget(), "Citizen is already safe");

	}

	public void respond(Rescuable r) throws IncompatibleTargetException, CannotTreatException {
		if (r instanceof Citizen) {
			Citizen c = (Citizen) r;
			if (c.getToxicity() == 0)
				throw new CannotTreatException(this, r, "Citizen is already safe.");
			else {
				if (getTarget() != null && ((Citizen) getTarget()).getToxicity() > 0
						&& getState() == UnitState.TREATING)
					reactivateDisaster();
				finishRespond(r);
			}
		} else
			throw new IncompatibleTargetException(this, r, "Disease Control Unit can only respond to citizens.");

	}
}
