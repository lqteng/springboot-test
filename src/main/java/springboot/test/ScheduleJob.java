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
		Ehcache cache = cacheManager.getEhcache("jobs");
		
		Element e = cache.get("job_fixed");
		
		if (e != null) {
			List<String> l = (List<String>) e.getObjectValue();
			l.add(new Date().toString());
			cache.put(new Element("job_fixed", l));
		} else {
			List<String> l = new ArrayList<String>();
			l.add(new Date().toString());
			cache.put(new Element("job_fixed", l));
		}
		
		

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
