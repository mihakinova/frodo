package com.fernandocejas.example.frodo.sample;

import com.fernandocejas.frodo.annotation.RxLogSubscriber;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

@RxLogSubscriber
public class MyObserver implements Observer<String> {
  @Override
  public void onSubscribe(Disposable d) {

  }

  @Override
  public void onNext(String value) {
    //empty
  }

  @Override
  public void onError(Throwable throwable) {
    //empty
  }

  @Override
  public void onComplete() {
    //empty
  }
}
