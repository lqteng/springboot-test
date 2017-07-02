package springboot.test;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

@RestController
@EnableAutoConfiguration
public class HelloController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass()); 
	
	private static CacheManager cacheManager = CacheManager.newInstance();

	@RequestMapping("/")
	public String index() {
		logger.info("Hello World!");  
		return "Hello World!";
	}

	@RequestMapping("/hello/{name}")
	public List<JobStatus> hello(@PathVariable String name) {
		long start = System.currentTimeMillis();
		
		Ehcache cache = cacheManager.getEhcache("jobs");
		cache.
		List keys = cache.getKeys();
		List<JobStatus> l = new ArrayList<JobStatus>();
		for (int i = 0; i < keys.size(); i++) {
			Element e = cache.get(keys.get(i));
			if (e != null) {
				JobStatus s = (JobStatus)e.getObjectValue();
				l.add(s);
			}
		}
		
		
		long used = System.currentTimeMillis() - start;
		logger.info(l + ", Used time: " + used + "ms.");  
		
		return l;
	}

}
