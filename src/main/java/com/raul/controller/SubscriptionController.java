package com.raul.controller;

import com.raul.data.Subscription;
import com.raul.repositories.SubscriptionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;




/**
 * Created by Raul on 1/30/17.
 */
@RestController
@RequestMapping(path = "/subscription")
public class SubscriptionController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    /**
     * Save a new subscription to the database and auto-generates an Id
     * @param aSubscription
     * @return
     */

    @RequestMapping(value = "save", method = RequestMethod.POST, produces = "application/json")
    public Subscription save(@RequestBody Subscription aSubscription){
        subscriptionRepository.save(aSubscription);
        log.info(" Saved Subscription: "+ aSubscription);
        return subscriptionRepository.findOne(aSubscription.getId());
    }

    /**
     * Update subscription by Id
     * @param aSubscription
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public Subscription update(@RequestBody Subscription aSubscription){
        subscriptionRepository.save(aSubscription);
        log.info("Updated Subscription:"+ aSubscription);
        return subscriptionRepository.findOne(aSubscription.getId());
    }

    /**
     * Finds a subscription by Id
     * @param id
     * @return
     */
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public Subscription show(@PathVariable Long id){
        log.info("Subscription id:"+ id);
        return subscriptionRepository.findOne(id);
    }

    /**
     * Delete message by Id
     * @param id
     * @return
     */
    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public Subscription delete(@PathVariable Long id){
        Subscription deleted = subscriptionRepository.findOne(id);
        subscriptionRepository.delete(id);
        log.info("Deleted Subscription id:"+ id);

        return deleted;
    }

    /**
     * List all subscriptions and filters by page,size, direction and sortby id.
     * @param page
     * @param size
     * @param sortby
     * @param direction
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Iterable<Subscription> list (@RequestParam(value ="page", required = false)Integer page,
                                   @RequestParam(value = "size", required = false)Integer size,
                                   @RequestParam(value = "sortby", required = false) String sortby,
                                   @RequestParam(value = "dir", required = false) Sort.Direction direction){

        log.debug(String.format("Begin listAll (page:%s, size:%s, sortby:%s, dir:%s):",page,size,sortby,direction));
        if (page == null){
            page = 0;
        }
        if (size == null){
            size = 100;
        }
        // DEFAULT Sort property
        if (sortby == null) {
            sortby = "type";
        }

        // DEFAULT Sort direction
        if (direction == null) {
            direction = Sort.Direction.DESC;
        }
        Sort s = new Sort(direction, sortby);
        PageRequest pr = new PageRequest(page, size, s);
        Iterable<Subscription> found =  subscriptionRepository.findAll(pr);
        log.debug(String.format("End listAll: %s", found));

        return found;

    }


    /**
     * Handles exceptions
     * @param e
     * @return
     */
    @ExceptionHandler(value = Throwable.class)
    public String nfeHandler(Throwable e){
        e.printStackTrace();
        return "Something Went Wrong!!!";
    }

}
