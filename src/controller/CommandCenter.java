package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import exceptions.CannotTreatException;
import exceptions.DisasterException;
import model.events.SOSListener;
import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;
import model.units.Unit;
import simulation.Rescuable;
import simulation.Simulator;

public class CommandCenter implements SOSListener, ActionListener {

	private Simulator engine;
	private ArrayList<ResidentialBuilding> visibleBuildings;
	private ArrayList<Citizen> visibleCitizens;

	@SuppressWarnings("unused")
	private ArrayList<Unit> emergencyUnits;
	private JButton nextCycleButton = new JButton("Next Cycle");
	private JButton ftrk = new JButton("Firetruck");
	private JButton amb = new JButton("Ambulance");
	private JButton evac = new JButton("Evacuator");
	private JButton dcu = new JButton("Disease Control Unit");
	private JButton gcu = new JButton("Gas Control Unit");
	
	public JButton getNextCycleButton() {
		return nextCycleButton;
	}

	public JButton getFtrk() {
		return ftrk;
	}

	public JButton getAmb() {
		return amb;
	}

	public JButton getEvac() {
		return evac;
	}

	public JButton getDcu() {
		return dcu;
	}

	public JButton getGcu() {
		return gcu;
	}
	

	public CommandCenter() throws Exception {
		engine = new Simulator(this);
		visibleBuildings = new ArrayList<ResidentialBuilding>();
		visibleCitizens = new ArrayList<Citizen>();
		emergencyUnits = engine.getEmergencyUnits();

	}

	@Override
	public void receiveSOSCall(Rescuable r) {
		
		if (r instanceof ResidentialBuilding) {
			
			if (!visibleBuildings.contains(r))
				visibleBuildings.add((ResidentialBuilding) r);
			
		} else {
			
			if (!visibleCitizens.contains(r))
				visibleCitizens.add((Citizen) r);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()== nextCycleButton) {
			try {
				engine.nextCycle();
			} catch (CannotTreatException e1) {
				JOptionPane.showInternalMessageDialog(null, "Already safe!");
				//e1.printStackTrace();
			} catch (DisasterException e1) {
				JOptionPane.showInternalMessageDialog(null, "Too late! \n He's dead Jim.");
				//e1.printStackTrace();
			}
		}
		
	}

}
