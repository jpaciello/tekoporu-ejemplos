package py.gov.setics.asistente.event;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import javax.ejb.AccessTimeout;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;

@Stateless
public class EventObserver implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5333747827481836108L;
	@Inject
	private Logger logger;

	@AccessTimeout(value = 1, unit = TimeUnit.MINUTES)
	public void observe(@Observes TestEvent event) {
		if ("five".equals(event.getMessage())) {
			logger.info("¡Evento recibido!");
		}
	}
}
