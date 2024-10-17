package com.hanu.sec3;

import com.hanu.common.Util;
import com.hanu.sec1.subscriber.SubscriberImpl;
import com.hanu.sec3.helper.Sec3Helper;
@SuppressWarnings("all")
public class FluxVsList {
    public static void main(String[] args) {
//        Sec3Helper.getNamesUsingList(10)
//                .forEach(System.out::println);
        //Sec3Helper.getNamesUsingFlux(10)
          //      .subscribe(Util.subscriber());

        SubscriberImpl subscriber = new SubscriberImpl();
        Sec3Helper.getNamesUsingFlux(10)
                .subscribe(subscriber);
        subscriber.getSubscription().request(5);
    }
}
