package springboot.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

@Component
@EnableScheduling
@EnableCaching
public class ScheduleJob {

	private static CacheManager cacheManager = CacheManager.newInstance();

	public final static long ONE_MINUTE = 60 * 1000;

	@Scheduled(fixedDelay = ONE_MINUTE)
	public void fixedDelayJob() {
		System.out.println(new Date() + ">>fixedDelay执行....");
	}

	@Scheduled(fixedRateString = "${cron.fixedrate}000")
	public void fixedRateJob() {
		System.out.println(new Date() + ">>fixedRate执行....");
	}

	@Scheduled(cron = "${cron.expression}")
	public void cronJob() {
		Ehcache cache = cacheManager.getEhcache("jobs");

		Element e = cache.get("job_cron");
		List<JobStatus> l = null;
		if (e != null) {
			l = (List<JobStatus>) e.getObjectValue();

		} else {
			l = new ArrayList<JobStatus>();
		}

		JobStatus js = new JobStatus();
		js.setJobName("jobcron");
		js.setStartTime(new Date());
		js.setEndTime(new Date());
		js.setResult("1");
		l.add(js);
		cache.put(new Element("job_cron", l));

		System.out.println(new Date() + ">>cron执行....");
	}
}
