package alerts;

import java.util.List;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import model.FutureWatering;
import persistence.FutureWateringRepository;

/**
 * A service to check for today's watering reminders and send notifications.
 * 
 * @author morganmazer
 *
 */
@Component
public class WateringReminderService {
	
	private static final Logger log = LoggerFactory.getLogger(WateringReminderService.class);
	
	// the user's email
	@Value("${notification.to.email}")
	private String TO_EMAIL;
		
	// from email address
	@Value("${notification.from.email}")
	private String FROM_EMAIL;
	
	// from email address
	@Value("${notification.from.password}")
	private String FROM_PASSWORD;
	

	@Autowired
	FutureWateringRepository futureWateringRepository;
	
	// for testing: run every minute "0 * * * * *"
	// run every day at 5 am "0 0 5 * * *"
	@Scheduled(cron = "0 0 5 * * *")
	public void wateringAlertPoller() {
		log.info("Checking database for today's watering reminders");
		
		// check the FutureWatering repository for reminder dates of today
		List<FutureWatering> todaysReminders = futureWateringRepository.findFutureWateringsForToday();
		
		// send alerts
		for (FutureWatering reminder : todaysReminders) {
			sendWateringReminder(reminder);
		}
	}
	
	public void sendWateringReminder(FutureWatering reminder) {
		log.info("Sending watering reminder: " + reminder.toString());
		
		String subject = "Please Water Me reminder!";
		String body = constructEmailBody(reminder);
		
		try {
			GmailUtil gmailUtil = new GmailUtil();
        	gmailUtil.sendEmail(TO_EMAIL, FROM_EMAIL, FROM_PASSWORD, subject, body, false);
        } catch (MessagingException e) {
        	log.info("Unable to send email");
        	e.printStackTrace();
        }
	}
	
	private String constructEmailBody(FutureWatering reminder) {
		String body = "This is a reminder that your " + reminder.getPlant().getSpecies() + " located in "
				+ reminder.getPlant().getLocation() + " is due for watering today, " + reminder.getReminderDate()
				+ ". Once you've watered it, be sure to log the watering in the Please Water Me application!";
		
		return body;
	}

}
