package alerts;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import model.FutureWatering;
import persistence.FutureWateringRepository;

@Component
public class WateringReminderService {
	
	private static final Logger log = LoggerFactory.getLogger(WateringReminderService.class);
	
	@Autowired
	FutureWateringRepository futureWateringRepository;
	
	// for testing: run every minute "0 * * * * *"
	// run every day at 5 am "0 0 5 * * *"
	@Scheduled(cron = "0 * * * * *") 
	public void wateringAlertPoller() {
		log.info("Checking database for today's watering reminders");
		
		// check the FutureWatering repository for reminder dates of today
		List<FutureWatering> todaysReminders = futureWateringRepository.findFutureWateringsForToday();
		
		// TODO: send alerts
		
	}

}
