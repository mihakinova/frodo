package com.fernandocejas.example.frodo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.fernandocejas.example.frodo.sample.MyObserver;
import com.fernandocejas.example.frodo.sample.MyObserverVoid;
import com.fernandocejas.example.frodo.sample.ObservableSample;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SamplesActivity extends Activity {

  private Button btnRxLogObservable;
  private Button btnRxLogSubscriber;

  private View.OnClickListener rxLogObservableListener = new View.OnClickListener() {
    @Override
    public void onClick(View v) {
      ObservableSample observableSample = new ObservableSample();

      observableSample.stringItemWithDefer()
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe();

      observableSample.numbers()
          .subscribeOn(Schedulers.newThread())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
              toastMessage("onNext() Integer--> " + String.valueOf(integer));
            }
          });

      observableSample.moreNumbers().toList().blockingGet();

      observableSample.names()
          .subscribeOn(Schedulers.newThread())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(new Consumer<String>() {
            @Override
            public void accept(String string) {
              toastMessage("onNext() String--> " + string);
            }
          });

      observableSample.error()
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(new Observer<String>() {
            @Override
            public void onComplete() {
              //nothing here
            }

            @Override
            public void onError(Throwable e) {
              toastMessage("onError() --> " + e.getMessage());
            }

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
              //nothing here
            }
          });

      observableSample.list()
          .subscribeOn(Schedulers.newThread())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(new Consumer<List<ObservableSample.MyDummyClass>>() {
            @Override
            public void accept(List<ObservableSample.MyDummyClass> myDummyClasses) {
              toastMessage("onNext() List--> " + myDummyClasses.toString());
            }
          });

      observableSample.doNothing()
          .subscribeOn(Schedulers.newThread())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe();
    }
  };

  private View.OnClickListener rxLogSubscriberListener = new View.OnClickListener() {
    @Override
    public void onClick(View v) {
      final ObservableSample observableSample = new ObservableSample();
      toastMessage("Subscribing to observables...Check logcat output...");

      observableSample.strings()
          .subscribeOn(Schedulers.newThread())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(new MyObserver());

      observableSample.stringsWithError()
          .subscribeOn(Schedulers.newThread())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(new MyObserver());

      observableSample.doNothing()
          .subscribeOn(Schedulers.newThread())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(new MyObserverVoid());
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_samples);
    this.mapGUI();
  }

  private void mapGUI() {
    this.btnRxLogObservable = (Button) findViewById(R.id.btnRxLogObservable);
    this.btnRxLogSubscriber = (Button) findViewById(R.id.btnRxLogSubscriber);

    this.btnRxLogObservable.setOnClickListener(rxLogObservableListener);
    this.btnRxLogSubscriber.setOnClickListener(rxLogSubscriberListener);
  }

  private void toastMessage(String message) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
  }
}
