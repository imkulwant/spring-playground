package com.kulsin;

import com.hazelcast.core.HazelcastInstance;
import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.hazelcast4.HazelcastLockProvider;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableSchedulerLock(defaultLockAtMostFor = "PT30S")
public class ShedlockConfig {

    @Bean
    public LockProvider lockProvider(HazelcastInstance hazelcastInstance) {
        return new HazelcastLockProvider(hazelcastInstance);
    }

}
