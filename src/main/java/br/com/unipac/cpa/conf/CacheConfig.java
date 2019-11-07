package br.com.unipac.cpa.conf;

import java.util.concurrent.TimeUnit;

import br.com.unipac.cpa.constants.Constants;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Caffeine;

@EnableCaching
@Configuration
public class CacheConfig {

	@Bean
	public CaffeineCache companyTypesCache() {
		return new CaffeineCache(Constants.COMPANYS_TYPES_IN_CACHE,
				Caffeine.newBuilder().expireAfterWrite(30, TimeUnit.MINUTES).maximumSize(Constants.MAXIMUM_SIZE).build());
	}

	@Bean
	public CaffeineCache companysInCache() {
		return new CaffeineCache(Constants.COMPANYS_IN_CACHE,
				Caffeine.newBuilder().expireAfterWrite(30, TimeUnit.MINUTES).maximumSize(Constants.MAXIMUM_SIZE).build());
	}

	@Bean
	public CaffeineCache coursesCache() {
		return new CaffeineCache(Constants.COURSES_IN_CACHE,
				Caffeine.newBuilder().expireAfterWrite(30, TimeUnit.MINUTES).maximumSize(Constants.MAXIMUM_SIZE).build());
	}

	@Bean
	public CaffeineCache disciplinesCache() {
		return new CaffeineCache(Constants.DISCIPLINES_IN_CACHE,
				Caffeine.newBuilder().expireAfterWrite(30, TimeUnit.MINUTES).maximumSize(Constants.MAXIMUM_SIZE).build());
	}

	@Bean
	public CaffeineCache questionCache() {
		return new CaffeineCache(Constants.QUESTION_IN_CACHE,
				Caffeine.newBuilder().expireAfterWrite(30, TimeUnit.MINUTES).maximumSize(Constants.MAXIMUM_SIZE).build());
	}

	@Bean
	public CaffeineCache periodsCache() {
		return new CaffeineCache(Constants.PERIODS_IN_CACHE,
				Caffeine.newBuilder().expireAfterWrite(30, TimeUnit.MINUTES).maximumSize(Constants.MAXIMUM_SIZE).build());
	}

	@Bean
	public CaffeineCache documentRegionsCache() {
		return new CaffeineCache(Constants.DOCUMENT_REGIONS_IN_CACHE,
				Caffeine.newBuilder().expireAfterWrite(30, TimeUnit.MINUTES).maximumSize(Constants.MAXIMUM_SIZE).build());
	}

	@Bean
	public CaffeineCache localsCache() {
		return new CaffeineCache(Constants.LOCALS_IN_CACHE,
				Caffeine.newBuilder().expireAfterWrite(30, TimeUnit.MINUTES).maximumSize(Constants.MAXIMUM_SIZE).build());
	}

	@Bean
	public CaffeineCache choicesCache() {
		return new CaffeineCache(Constants.CHOICES_IN_CACHE,
				Caffeine.newBuilder().expireAfterWrite(30, TimeUnit.MINUTES).maximumSize(Constants.MAXIMUM_SIZE).build());
	}

	@Bean
	public CaffeineCache evaluationsCache() {
		return new CaffeineCache(Constants.EVALUATIONS_IN_CACHE,
				Caffeine.newBuilder().expireAfterWrite(30, TimeUnit.MINUTES).maximumSize(Constants.MAXIMUM_SIZE).build());
	}

	@Bean
	public CaffeineCache personTypesCache() {
		return new CaffeineCache(Constants.PERSON_TYPES_IN_CACHE,
				Caffeine.newBuilder().expireAfterWrite(30, TimeUnit.MINUTES).maximumSize(Constants.MAXIMUM_SIZE).build());
	}


	@Bean
	public CaffeineCache studentsCache() {
		return new CaffeineCache(Constants.STUDENTS_IN_CACHE,
				Caffeine.newBuilder().expireAfterWrite(30, TimeUnit.MINUTES).maximumSize(Constants.MAXIMUM_SIZE).build());
	}

	@Bean
	public CaffeineCache professorsCache() {
		return new CaffeineCache(Constants.PROFESSORS_IN_CACHE,
				Caffeine.newBuilder().expireAfterWrite(30, TimeUnit.MINUTES).maximumSize(Constants.MAXIMUM_SIZE).build());
	}

	@Bean
	public CaffeineCache likertSkalasCache() {
		return new CaffeineCache(Constants.LIKERT_SKALA_IN_CACHE,
				Caffeine.newBuilder().expireAfterWrite(30, TimeUnit.MINUTES).maximumSize(Constants.MAXIMUM_SIZE).build());
	}

}
