package com.fernandocejas.example.frodo.sample;

import com.fernandocejas.frodo.annotation.RxLogSubscriber;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

@RxLogSubscriber
public class MyObserverVoid implements Observer<Void> {

  @Override public void onError(Throwable e) {
    //empty
  }

  @Override
  public void onComplete() {
    //empty
  }

  @Override
  public void onSubscribe(Disposable d) {
    //empty
  }

  @Override public void onNext(Void aVoid) {
    //empty
  }
}
