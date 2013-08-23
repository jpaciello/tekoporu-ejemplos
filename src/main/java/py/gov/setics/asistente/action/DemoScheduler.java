package py.gov.setics.asistente.action;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.jboss.weld.Container;
import org.jboss.weld.context.bound.BoundRequestContext;
import org.slf4j.Logger;
import org.ticpy.tekoporu.annotation.Startup;
import org.ticpy.tekoporu.scheduler.ISchedulerAction;
import org.ticpy.tekoporu.scheduler.Scheduler;
import org.ticpy.tekoporu.util.Beans;

import py.gov.setics.asistente.business.BookmarkBC;
import py.gov.setics.asistente.domain.Bookmark;

@Scheduler(expression = "00:00:00 EVERY_SECOND 10")
public class DemoScheduler implements ISchedulerAction, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7185856082262566555L;
	
	private static DemoScheduler instance = null;
	
	@Inject
	private Logger log;	
	
	@Inject
	private BookmarkBC bookmarkBC;

	private BoundRequestContext context;
	private Map<String, Object> request;
	
	@Startup
	public void onStartup() {
		
		instance = this;		
	}
	
	private void initContext() {
		 
		BoundRequestContext context = Container.instance().deploymentManager().instance().select(BoundRequestContext.class).get();
		Map<String, Object> request = new HashMap<String, Object>();
		context.associate(request);
		context.activate();
	}
	 
	private void closeContext() {
		
		if(context != null && context.isActive()) {
			context.deactivate();
			context.dissociate(request);
		}
	}
	
	private void scheduledTask() {
		
		log.info("Ejecutando tarea programada...");
		
		initContext();		
		log.info(">>>>>Bookmark item count: " + bookmarkBC.count());
		bookmarkBC.insert(new Bookmark("Test bookmark", "Test link"));		
		closeContext();
	}
	
	public void execute() {
		
		DemoScheduler scheduler = instance;
		if(scheduler != null)
			scheduler.scheduledTask();
	}
}