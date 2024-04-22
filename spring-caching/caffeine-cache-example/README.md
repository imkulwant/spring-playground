# caffeine-cache-example
Project for Caffeine cache example using Spring Boot

## Populating Cache
Caffeine has three strategies for cache population: 
- Manual
- Synchronous loading
- Asynchronous loading

## Eviction of Values
Caffeine has three strategies for value eviction:
- Size-based
- Time-based
- Reference-based

## Difference between expireAfter and refreshAfter.
When the expired entry is requested, an execution blocks until the new value would have been calculated by the build Function.

But if the entry is eligible for the refreshing, then the cache would return an old value and asynchronously reload the value.

## Endpoints
- http://localhost:8080/customer/address?customerId={customer-id}
- http://localhost:8080/monitor/caffeine/cacheNames
- http://localhost:8080/monitor/caffeine/stats?cacheName={cache-name}