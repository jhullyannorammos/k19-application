package br.com.application.listeners;

import java.util.logging.Logger;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class Phases implements PhaseListener {
	
	@Override
	public void afterPhase(PhaseEvent event) {
		Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).info(event.getPhaseId() + " encerrou!");
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).info(event.getPhaseId() + " iniciou!");
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

}
