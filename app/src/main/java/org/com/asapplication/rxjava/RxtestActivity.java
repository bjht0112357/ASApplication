package org.com.asapplication.rxjava;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.com.asapplication.R;
import org.com.asapplication.apputils.AppLogger;
import org.com.asapplication.rxjava.bean.GirlBean;
import org.com.asapplication.rxjava.bean.Translation;
import org.com.asapplication.rxjava.network.ApiRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import rx.functions.Func0;

public class RxtestActivity extends AppCompatActivity {

    @BindView(R.id.tvOut)
    TextView tvOut;
    @BindView(R.id.btnTestCache)
    TextView btnTestCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxtest);
        ButterKnife.bind(this);
        setTitle("RxtestActivity");

        //rxjava2 官方文档
        //https://mcxiaoke.gitbooks.io/rxdocs/content/operators/Combining-Observables.html


//        getNetworkData();
//        ablObserver();
//        ablConsumer();
//        defer();
//        repeat();


//        timer();
//        from();
//        intervalRange();
//        scan();
//        reduce();
        /*===========组合操作符==========*/
//        zip();
//        merge();
//        concat();
//        startWith();
//        switchOnNext();
        /*===========组合操作符==========*/
        /*===========过滤操作符==========*/
//        filter();
//        distinct();
//        skip();
//        element();
//        take();
//        sample();
        /*===========过滤操作符==========*/
        /*===========条件操作符==========*/
//        all();
//        takeUntil();
//        takeWhile();
//        skipUntil();
//        skipWhile();
//        sequenceEqual();
//        contains();
//        isEmpty();
//        amb();
        /*===========条件操作符==========*/
        btnTestCache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvOut.setText("");
                getNetworkData();
            }
        });


    }

    /**
     * 在源Observable发射的数据前插入另一个Observable发射的数据
     */
    private void startWith() {
        Observable observable1 = Observable.just(4, 5, 6, 8);
        Observable observable2 = Observable.just("one", "two");
        observable1.startWith(observable2)
                .doOnNext(new Consumer() {
                    @Override
                    public void accept(Object o) {
                        AppLogger.e("startWith " + String.valueOf(o));
                    }
                })
                .subscribe();
    }


    /*=======================条件操作符========================*/
    @SuppressLint("CheckResult")
    private void all() {
        Observable.just(1, 3, 5).all(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer % 2 == 1;
            }
        }).subscribe(new Consumer<Boolean>() {// Consumer是简易版的Observer，这里调用的只接收onNext方法
            @Override
            public void accept(Boolean aBoolean) {
                AppLogger.e("all " + aBoolean);
            }
        });
    }

    /**
     * 使用一个标志Observable是否发射数据来判断，当标志Observable没有发射数据时，
     * 正常发射数据，而一旦标志Observable发射过了数据则后面的数据都会被丢弃。
     */
    @SuppressLint("CheckResult")
    private void takeUntil() {
        //输出结果为1 3
        Observable.just(1, 3, 5).takeUntil(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                //当大于2时就停止发送，但当前的还是会发送如 打印 3
                return integer > 2;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                AppLogger.e("takeUntil " + integer);
            }
        });
    }

    /**
     * 当某个数据满足条件时就会发送该数据，反之则不发送
     * 注：顺序发送，当不满足条件时就停止
     * 如下显示的结果 为 1 2 而不是1 2 2
     */
    private void takeWhile() {
        Observable.just(1, 2, 3, 4, 2).takeWhile(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer < 3;
            }
        }).doOnNext(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                AppLogger.e("takeWhile " + integer);
            }
        }).subscribe();
    }

    /**
     * 丢弃原始Observable发射的数据，直到第二个Observable发射了一项数据
     */
    private void skipUntil() {
        Observable.intervalRange(30, 20, 500, 100, TimeUnit.MILLISECONDS)
                .skipUntil(Observable.intervalRange(20, 20, 1000, 100, TimeUnit.MILLISECONDS))
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) {
                        AppLogger.e("skipUntil " + aLong);
                    }
                })
                //此时用这个主要是 测试环境 有执行时间 所以用阻塞比较好
                .blockingSubscribe();
        //结果
        //skipUntil 35
        //skipUntil 36
        //...
    }

    /**
     * 可以设置条件，当某个数据不满足条件时发送，后续将不再判断
     * 如下打印的结果为 3 4 3 2
     */
    private void skipWhile() {
        Observable.just(1, 2, 3, 4, 3, 2).skipWhile(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer < 3;
            }
        }).doOnNext(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                AppLogger.e("skipWhile " + integer);
            }
        }).subscribe();
    }

    /**
     * 判断两个 Observable 发送的事件是否相同。
     */
    private void sequenceEqual() {
        Observable.sequenceEqual(Observable.just(1, 2), Observable.just(1, 2, 3))
                .doOnEvent(new BiConsumer<Boolean, Throwable>() {
                    @Override
                    public void accept(Boolean aBoolean, Throwable throwable) {
                        AppLogger.e("sequenceEqual " + aBoolean);
                    }
                })
                .subscribe();
    }

    /**
     * 判断发送的事件中是否包含某个元素，有则返回true
     */
    private void contains() {
        Observable.just("one", "two").contains("two")
                .doOnEvent(new BiConsumer<Boolean, Throwable>() {
                    @Override
                    public void accept(Boolean aBoolean, Throwable throwable) {
                        AppLogger.e("contains " + aBoolean);
                    }
                }).subscribe();
    }

    /**
     * 判断Observable事件序列是否为空
     */
    private void isEmpty() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) {
                //有e.onNext(...);结果为false 否则为true
//                    e.onNext(1);
                e.onComplete();
            }
        })
                .isEmpty()
                .doOnEvent(new BiConsumer<Boolean, Throwable>() {
                    @Override
                    public void accept(Boolean aBoolean, Throwable throwable) {
                        AppLogger.e("isEmpty " + aBoolean);
                    }
                })
                .subscribe();
    }

    /**
     * amb() 要传入一个 Observable 集合，但是只会发送最先发送事件的 Observable 中的事件，其余 Observable 将会被丢弃。
     */
    private void amb() {
        List<Observable<Long>> list = new ArrayList<>();
        list.add(Observable.intervalRange(5, 3, 5, 1, TimeUnit.SECONDS));
        //initialdelay 3先触发 所以只发送以下的 Observable 其他丢弃
        list.add(Observable.intervalRange(1, 3, 3, 1, TimeUnit.SECONDS));
        Observable.amb(list)
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) {
                        AppLogger.e("amb " + aLong);
                    }
                })
                .subscribe();
    }
    /*=======================条件操作符========================*/

    /**
     * RxJava 过滤操作符 sample
     * sample操作符是定期扫描源Observable产生的结果，在指定的间隔周期内进行采样
     */
    private void sample() {

        Observable.interval(1, TimeUnit.SECONDS).sample(2, TimeUnit.SECONDS).subscribe(new Observer<Long>() {

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Long aLong) {
                AppLogger.e("sample" + aLong);
            }
        });
        // 03-19 11:37:09.237 onNext(): sample0
        // 03-19 11:37:11.236 onNext(): sample2
        // 03-19 11:37:13.237 onNext(): sample4
    }

    /**
     * filter 筛选过滤器
     */
    private void filter() {
        Observable.just(1, 2, 3, 4, 5, 6)//创建了一个有6个数字的被观察者
                .filter(new Predicate<Integer>() {//添加筛选器
                    @Override
                    public boolean test(Integer integer) {//对每个事件进行筛选，返回true的保留
                        return integer % 2 == 0;
                    }
                })
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) {
                        AppLogger.e("filter " + integer);
                    }
                })
                .subscribe();//这里的观察者依然不重要
    }

    private void element() {
        Observable.just("one", "two").elementAt(1)
                .doOnEvent(new BiConsumer<String, Throwable>() {
                    @Override
                    public void accept(String s, Throwable throwable) {
                        AppLogger.e("elementAt " + s);
                    }
                })
                .subscribe();

        Observable.range(0, 10)
                .ignoreElements()
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        AppLogger.e("ignoreElements " + throwable.getMessage());
                    }
                }).doOnComplete(new Action() {
            @Override
            public void run() {
                AppLogger.e("ignoreElements Complete");
            }
        })
                .subscribe();
    }

    @SuppressLint("CheckResult")
    private void skip() {
        //丢弃Observable发射的前N项数据
        Observable.range(0, 5)
                .skip(3)
                .subscribe(i -> AppLogger.e("skip msg" + i));
        //丢弃Observable发射的后N项数据
        Observable.range(0, 5)
                .skipLast(3)
                .subscribe(i -> AppLogger.e("skipLast msg" + i));
        //丢弃Observable发射1ms时间内的数据
        Observable.range(0, 100)
                .skipLast(1, TimeUnit.MILLISECONDS)
                .subscribe(i -> AppLogger.e("skipLast TimeUnit " + i));
    }

    /**
     * Distinct 的过滤规则是：只允许还没有发射过的数据项通过。
     */
    @SuppressLint("CheckResult")
    private void distinct() {
        Observable.just(1, 2, 1, 2, 3)
                //这个函数根据原始Observable发射的数据项产生一个 Key，
                // 然后，比较这些Key而不是数据本身，来判定两个数据是否是不同的
                .distinct(integer -> Math.random())
                .subscribe(o -> AppLogger.e("distinct random" + o));

        //无参版本(只显示不重复数据) 就是内部实现了的keySelector通过生成的key就是value本身
        Observable.just(1, 3, 1, 5, 6, 3).distinct().subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                AppLogger.e("distinct " + integer);
            }
        });
    }

    /**
     * 将当前的消息序列无限制循环发送。repeat(3)传入一个参数表示循环的次数
     */
    @SuppressLint("CheckResult")
    private void repeat() {
        Observable.just("1", "2", "3")
                .repeat(3)
                .subscribe(s -> {
                    AppLogger.e("repeat" + s);
                });
    }

    @SuppressLint("CheckResult")
    private void take() {
        // 变体 count系列:只发射前面的N项数据
        Observable.range(0, 10)
                .take(3)
                .subscribe(o -> AppLogger.e("take" + o));
        //变体 time系列: 发射Observable开始的那段时间发射 的数据
        Observable.range(0, 10)
                .take(100, TimeUnit.MILLISECONDS)
                .subscribe(o -> AppLogger.e("take TimeUnit" + o));
        // takeLast
        //变体 count系列:只发射后面的N项数据
        Observable.range(0, 10)
                .takeLast(3)
                .subscribe(o -> AppLogger.e("takeLast" + o));
        //变体 time系列: 发射在原始Observable的生命周 期内最后一段时间内发射的数据
        Observable.range(0, 10)
                .takeLast(100, TimeUnit.MILLISECONDS)
                .subscribe(o -> AppLogger.e("takeLast" + o));
        //takeUntil
        //发送complete的结束条件 当然发送结束之前也会包括这个值
        Observable.just(2, 3, 4, 5)
                //发送complete的结束条件 当然发送结束之前也会包括这个值
                .takeUntil(integer -> integer > 3)
                .subscribe(o -> AppLogger.e("takeUntil" + o));//2,3,4
        //takeWhile
        //当不满足这个条件 会发送结束 不会包括这个值
        Observable.just(2, 3, 4, 5)
                //当不满足这个条件 会发送结束 不会包括这个值
                .takeWhile(integer -> integer <= 4)
                .subscribe(o -> AppLogger.e("takeWhile" + o));//2,3,4
    }

    private void from() {
        Observable.fromArray("data1", "data2").subscribe(s -> {
            AppLogger.e("fromArray" + s);
        });
        Observable.fromCallable(new Callable<List<String>>() {
            @Override
            public List<String> call() {
                return Arrays.asList("hello", "gaga");
            }
        }).subscribe(new Consumer<List<String>>() {
            @Override
            public void accept(List<String> strings) {
                AppLogger.e(strings.toString());
            }
        });

        Observable.fromIterable(Arrays.asList("one", "two", "three"))
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        AppLogger.e("fromIterable" + s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


        Observable.fromFuture(Observable.just("sad")
                .toFuture())
                .doOnComplete(() -> AppLogger.e("complete")).subscribe();
    }

    /**
     * defer产生一个新的Obserable
     */
    private void defer() {
        //注意此处defer的参数是Func0，而不是OnSubscrie
        Observable.defer(new Func0<Observable<Translation>>() {
            @Override
            //注意此处的call方法没有Subscriber参数
            public Observable<Translation> call() {
                //获取网络信息
                return ApiRequest.getAppService().getMessage("fy", "auto", "auto", "hello%20world").subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        }).subscribe(new DisposableObserver<Translation>() {
            @Override
            public void onNext(Translation translation) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * start,count:同range
     * initialDelay 发送第一个值的延迟时间
     * period 每两个发射物的间隔时间 period 这个值一旦设定后是不可变化的
     * unit,scheduler
     */
    private void intervalRange() {
        AppLogger.e("intervalRange  begin");
        Observable.intervalRange(5, 8, 3000, 100,
                TimeUnit.MILLISECONDS, Schedulers.io())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        AppLogger.e("intervalRange" + aLong);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 在延迟一段给定的时间后发射
     */
    private void timer() {
        AppLogger.e("timer");
        Observable.timer(2000, TimeUnit.MILLISECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        AppLogger.e("timer  delay");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //zip只要有一个Observable的发射完成而终止发射如下：observable1发射完后就终止
    private void zip() {
        //上游水管第一个事件
        Observable<Integer> observable1 = Observable.range(1, 6);
        //上游水管第二个事件
        Observable<Integer> observable2 = Observable.range(6, 10);
        //合并事件
        Observable.zip(observable1, observable2, new BiFunction<Integer, Integer, String>() {
            @Override
            public String apply(Integer integer, Integer integer2) {
                return String.valueOf(integer + integer2);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) {
                AppLogger.e("zip: " + s);
            }
        });
    }

    /**
     * 组合多个被观察者一起发送数据，合并后 按时间线并行执行
     */
    private void merge() {
        //上游水管第一个事件
        Observable<Long> observable1 = Observable.intervalRange(1, 5, 300, 100, TimeUnit.MILLISECONDS);
        //上游水管第二个事件
        Observable<Long> observable2 = Observable.intervalRange(5, 5, 100, 200, TimeUnit.MILLISECONDS);
        //事件
        Observable<Long> merge = Observable.merge(observable1, observable2);
        merge.subscribe(new DisposableObserver<Long>() {
            @Override
            public void onNext(Long value) {
                AppLogger.e("merge: " + value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * ???
     */
    private void switchOnNext() {
        //https://blog.csdn.net/weixin_36709064/article/details/82919785#33%20RxBusBindingTest
        Flowable sourceFlowable = Flowable.just(Flowable.just(8, 10, 11), Flowable.just(5, 10, 11), Flowable.just(10, 20, 30));
        Flowable.switchOnNext(sourceFlowable).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer value) {
                System.out.println("value:" + value);
            }
        });
        Observable<Observable<Integer>> just = Observable.just(Observable.just(8, 10, 11), Observable.just(5, 10, 11), Observable.just(10, 20, 30));
        Observable.switchOnNext(just)
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer value) {
                        System.out.println("RxBusBindingTest: " + value);
                    }
                })
                .subscribe();
    }

    private void join() {
//      String[] args1 = new String[]{"张欣1", "张欣2", "张欣3", "张欣4", "zhangxin5"};
//      String[] args2 = new String[]{"春晓1", "春晓2", "春晓3", "春晓4"};
//      Integer[] args3 = new Integer[]{1, 2, 3, 4, 5, 6};
//      Observable<String> o1 = Observable.fromArray(args1);
//      Observable<String> o2 = Observable.fromArray(args2);
//      //相同的数组可以进行合并
//      Observable<String> joinObservable = o2.join(o1, new Function<String, ObservableSource<? extends Object>>() {
//          @Override
//          public ObservableSource<? extends Object> apply(String s) throws Exception {
//              return null;
//          }
//      }, new Function<Long, ObservableSource<Long>>() {
//          @Override
//          public ObservableSource<Long> apply(Long tRight) throws Exception {
//              return null;
//          }
//      }, new BiFunction<String, Long, R>() {
//          @Override
//          public R apply(String s, Long tRight) throws Exception {
//              return null;
//          }
//      });
    }

    /**
     * concat操作符组合多个被观察者一起发送数据，合并后 按发送顺序串行执行
     *
     * @return Observable<Integer>
     */
    private Observable<Integer> concat() {
        //上游水管第一个事件
        Observable<Integer> observable1 = Observable.just(1, 2, 7, 8);
        //上游水管第二个事件
        Observable<Integer> observable2 = Observable.just(3, 4, 5);
        //事件
        Observable<Integer> concat = Observable.concat(observable1, observable2);
        concat.subscribe(new DisposableObserver<Integer>() {
            @Override
            public void onNext(Integer integer) {
                AppLogger.e("concat: " + integer);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        return concat;
    }

    private void scan() {
        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5);
        observable.scan((a, b) -> {
            return a + b; //这个结果将作为最终输出结果到onNext里面
        }).subscribe(new DisposableObserver<Integer>() {
            @Override
            public void onNext(Integer result) {
                AppLogger.e("scan=" + result);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * 与scan()操作符类似，作用是将数据以一定的逻辑聚合起来，这两个的区别在于scan()没处理一次数据将会发送一个事件给观察者，
     * 但是reduce()会将所有数据聚合在一起之后才会发送给观察者，还有一点区别就是scan的返回值是Observable
     */
    private void reduce() {
        Observable.just(1, 2, 3, 4)//创建了一个有4个数字的被观察者
                .reduce(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer t1, Integer t2) {
                        return t1 + t2;//累加操作，如果是-就是累减操作。。依此类推
                    }
                })
                .doOnEvent(new BiConsumer<Integer, Throwable>() {
                    @Override
                    public void accept(Integer integer, Throwable throwable) {
                        AppLogger.e("reduce " + String.valueOf(integer));
                    }
                })
                .subscribe();
    }

    private void ablObserver() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) {
                //被观察者执行动作
                emitter.onNext("emitter1");
                emitter.onNext("emitter2");
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                //观察者监听动作，打印信息
                AppLogger.e(s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    @SuppressLint("CheckResult")
    private void ablConsumer() {
//        Observable.create((ObservableOnSubscribe<String>) emitter -> {
////            //被观察者执行动作
////            emitter.onNext("emitter1");
////            emitter.onNext("emitter2");
////        }).subscribe(new Consumer<String>() {
////            @Override
////            public void accept(String s) throws Exception {
////                AppLogger.e(s);
////            }
////        });
        Observable.just("item1", "item2", "item3")
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) {
                        AppLogger.e(s);
                    }
                });
    }

    private void getNetworkData() {
//        ApiLifecycleRequest.getInstance().getMessage(this,"fy", "auto", "auto", "hello%20world", new Response<Translation>() {
//            @Override
//            public void onError(String error) {
//            }
//
//            @Override
//            public void onResponse(Translation translation) {
//                tvOut.setText(translation.getContent().getOut());
//            }
//        });
        ApiRequest.getAppService().getMeiZhi().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GirlBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(GirlBean girlBean) {
                        tvOut.setText(girlBean.getResults().get(0).getUrl());
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
