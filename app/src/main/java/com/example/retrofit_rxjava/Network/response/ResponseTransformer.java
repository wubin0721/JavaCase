package com.example.retrofit_rxjava.Network.response;

import com.example.retrofit_rxjava.Network.exception.ApiException;
import com.example.retrofit_rxjava.utils.ReflectUtils;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class ResponseTransformer<T> implements ObservableTransformer<IResponse<T>,T> {

    private CompositeDisposable compositeDisposable;

    public ResponseTransformer(CompositeDisposable compositeDisposable){
        this.compositeDisposable = compositeDisposable;
    }

    public ResponseTransformer(){

    }

    @Override
    public ObservableSource<T> apply(Observable<IResponse<T>> upstream) {
        return upstream
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                            if(compositeDisposable != null){
                                compositeDisposable.add(disposable);
                            }
                    }
                })
                .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends IResponse<T>>>() {
                    @Override
                    public ObservableSource<? extends IResponse<T>> apply(Throwable throwable) throws Exception {
                        //出现异常后统一处理
                        return Observable.error(ApiException.handleException(throwable));
                    }
                })
                .flatMap(new Function<IResponse<T>, ObservableSource<? extends T>>() {
                    @Override
                    public ObservableSource<? extends T> apply(IResponse<T> tiResponse) throws Exception {
                        //请求成功后，接口返回的数据做成功失败的处理
                        if(tiResponse.isSuccess()){
                                if(tiResponse.getData()!=null){
                                    return Observable.just(tiResponse.getData());
                                }else{
                                    try{
                                       //有可能存在返回的数据结果为NULL
                                       //用反射创建一个没有内容的数据实例，这个实例不能用，只为了防止传NULL出现异常
                                        Class<?> clz = ReflectUtils.analysisClassInfo(tiResponse);
                                        T obj = (T) clz.newInstance();
                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }
                                }
                        }
                        return Observable.error(new ApiException(tiResponse.getCode(), tiResponse.getMsg()));
                    }
                })
                .subscribeOn(Schedulers.io())               //切换子线程
                .observeOn(AndroidSchedulers.mainThread());  //切换主线程
    }

    public static <U> ResponseTransformer<U> obtain(CompositeDisposable compositeDisposable){
        return new ResponseTransformer<>(compositeDisposable);
    }

    public static <U> ResponseTransformer<U> obtain(){
        return new ResponseTransformer<>();
    }
}
