package com.fernandocejas.example.frodo.sample;

import com.fernandocejas.frodo.annotation.RxLogSubscriber;

import org.reactivestreams.Subscription;

import io.reactivex.FlowableSubscriber;
import io.reactivex.annotations.NonNull;

@RxLogSubscriber
public class MySubscriberBackpressure implements FlowableSubscriber<Integer> {

    @Override
    public void onNext(Integer value) {
        //empty
    }

    @Override
    public void onError(Throwable throwable) {
        //empty
    }

    @Override
    public void onComplete() {

    }


    @Override
    public void onSubscribe(@NonNull Subscription s) {

    }
}
