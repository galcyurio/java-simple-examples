package com.github.galcyurio;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;

/**
 * @author galcyurio
 */
public class _2_KeyTypes {

    @Test
    public void publishSubject() {
        final PublishSubject<Integer> subject = PublishSubject.create();
        subject.onNext(1);
        subject.subscribe(System.out::println);
        subject.onNext(2);
        subject.onNext(3);
        subject.onNext(4);
    }

    @Test
    public void replaySubject() {
        final ReplaySubject<Integer> subject = ReplaySubject.create();
        subject.subscribe(i -> System.out.println("Early : " + i));
        subject.onNext(1);
        subject.onNext(2);
        subject.subscribe(i -> System.out.println("Late : " + i));
        subject.onNext(3);
    }

    @Test
    public void replaySubject_createWithSize() {
        final ReplaySubject<Integer> subject = ReplaySubject.createWithSize(2);
        subject.onNext(1);
        subject.onNext(2);
        subject.onNext(3);
        subject.subscribe(System.out::println);
        subject.onNext(4);
    }

    @Test
    public void replaySubject_createWithTime() throws InterruptedException {
        final ReplaySubject<Integer> subject = ReplaySubject.createWithTime(180, TimeUnit.MILLISECONDS, Schedulers.trampoline());
        subject.onNext(1);
        Thread.sleep(100);
        subject.onNext(2);
        Thread.sleep(100);
        subject.onNext(3);
        subject.subscribe(System.out::println);
        subject.onNext(4);
    }

    @Test
    public void behaviorSubject() {
        final BehaviorSubject<Integer> subject = BehaviorSubject.create();
        subject.onNext(1);
        subject.onNext(2);
        subject.onNext(3);
        subject.subscribe(System.out::println);
    }

    @Test
    public void behaviorSubject_onComplete() {
        final BehaviorSubject<Integer> subject = BehaviorSubject.create();
        subject.onNext(1);
        subject.onNext(2);
        subject.onNext(3);
        subject.onComplete();
        subject.subscribe(
            v -> System.out.println("Next: " + v),
            e -> System.out.println("Error"),
            () -> System.out.println("Completed")
        );
    }

    @Test
    public void asyncSubject() {
        AsyncSubject<Integer> subject = AsyncSubject.create();
        subject.subscribe(System.out::println);
        subject.onNext(0);
        subject.onNext(1);
        subject.onNext(2);
        subject.onComplete();
    }
}