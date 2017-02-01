package com.raul.repositories;

import com.raul.data.Subscription;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Raul on 1/30/17.
 */
public interface SubscriptionRepository extends PagingAndSortingRepository<Subscription, Long> {
}
