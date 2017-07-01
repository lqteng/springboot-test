package springboot.test;

import java.util.Date;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ScheduleJob {

	public final static long ONE_MINUTE = 60 * 1000;

	@Scheduled(fixedDelay = ONE_MINUTE)
	public void fixedDelayJob() {
		System.out.println(new Date() + ">>fixedDelay执行....");
	}

	@Scheduled(fixedRate = ONE_MINUTE)
	public void fixedRateJob() {
		System.out.println(new Date() + ">>fixedRate执行....");
	}

	@Scheduled(cron = "${cron.expression}")
	public void cronJob() {
		System.out.println(new Date() + ">>cron执行....");
	}
}
