package alerts;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WateringReminderService {
	
	@Scheduled
	public void wateringAlertPoller() {
		// check the FutureWatering repository for reminder dates of today
		
		// send alerts
		
	}

}
