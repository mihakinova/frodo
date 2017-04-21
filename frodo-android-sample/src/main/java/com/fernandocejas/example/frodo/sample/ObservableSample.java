package com.fernandocejas.example.frodo.sample;

import android.view.View;
import com.fernandocejas.frodo.annotation.RxLogObservable;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

import static com.fernandocejas.frodo.annotation.RxLogObservable.Scope.EVENTS;
import static com.fernandocejas.frodo.annotation.RxLogObservable.Scope.EVERYTHING;
import static com.fernandocejas.frodo.annotation.RxLogObservable.Scope.NOTHING;
import static com.fernandocejas.frodo.annotation.RxLogObservable.Scope.SCHEDULERS;
import static com.fernandocejas.frodo.annotation.RxLogObservable.Scope.STREAM;

public class ObservableSample {
  public ObservableSample() {
  }

  @RxLogObservable(EVERYTHING)
  public Observable<Integer> numbers() {
    return Observable.just(1, 2);
  }

  @RxLogObservable
  public Observable<Integer> moreNumbers() {
    return Observable.just(1, 2, 3, 4);
  }

  @RxLogObservable(STREAM)
  public Observable<String> names() {
    return Observable.just("Fernando", "Silvia");
  }

  @RxLogObservable
  public Observable<String> error() {
    return Observable.error(new IllegalArgumentException("My error"));
  }

  @RxLogObservable(SCHEDULERS)
  public Observable<List<MyDummyClass>> list() {
    return Observable.just(buildDummyList());
  }

  @RxLogObservable(EVENTS)
  public Observable<String> stringItemWithDefer() {
    return Observable.defer(new Callable<ObservableSource<? extends String>>() {
      @Override public ObservableSource<? extends String> call() throws Exception {
        return Observable.create(new ObservableOnSubscribe<String>() {
          @Override public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
            try {
              e.onNext("String item Three");
              e.onComplete();
            } catch (Exception err) {
              e.onError(err);
            }
          }
        }).subscribeOn(Schedulers.computation());
      }
    });
  }

  /**
   * Nothing should happen here when annotating this method with {@link RxLogObservable}
   * because it does not returns an {@link Observable}.
   */
  @RxLogObservable(NOTHING)
  public List<MyDummyClass> buildDummyList() {
    return Arrays.asList(new MyDummyClass("Batman"), new MyDummyClass("Superman"));
  }

  @RxLogObservable
  public Observable<String> strings() {
    return Observable.just("Hello", "My", "Name", "Is", "Fernando");
  }

  public Observable<String> stringsWithError() {
    return Observable.error(new IllegalArgumentException("My Subscriber error"));
  }

  @RxLogObservable
  public Observable<Void> doNothing() {
    return Observable.empty();
  }

  public static final class MyDummyClass {
    private final String name;

    MyDummyClass(String name) {
      this.name = name;
    }

    @Override
    public String toString() {
      return "Name: " + name;
    }
  }
}
