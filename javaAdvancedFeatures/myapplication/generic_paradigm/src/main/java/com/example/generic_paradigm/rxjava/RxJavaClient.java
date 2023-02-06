package com.example.generic_paradigm.rxjava;

import android.text.TextUtils;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class RxJavaClient {

    public static void main(String[] args) {

        // 被观察者 shift+alt+上下箭头移动代码行
        ObservableOnSubscribe<String> subscribe = new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                if (!emitter.isDisposed()) {
                    emitter.onNext("当前线程："+Thread.currentThread().getName());
                }
                emitter.onComplete();
            }
        };

        Observable<String> observable = Observable.create(subscribe);

        // 观察者
        Observer<String> observer = new Observer<String>() {

            @Override
            public void onSubscribe(Disposable d) {
                System.out.println(d);
            }

            @Override
            public void onNext(String s) {
                System.out.println("观察者收到消息了：" + s+" 当前线程是："+Thread.currentThread().getName());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        };

        //observable = observable.subscribeOn(Schedulers.io());
        observable.subscribeOn(Schedulers.newThread()).subscribeOn(Schedulers.newThread()).subscribe(observer);
        observable.subscribeOn(Schedulers.io()).subscribeOn(Schedulers.newThread()).subscribe(observer);

//        Flowable.just("hello flowable").subscribe(v-> System.out.println(v));
//        System.out.println("================");
//        Flowable.just("hello flowable").map(v->"ee").subscribe(v-> System.out.println(v));


    }
}
