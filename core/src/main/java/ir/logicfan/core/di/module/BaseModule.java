package ir.logicfan.core.di.module;

import android.app.Application;
import android.content.Context;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import ir.logicfan.core.data.pref.BasePrefManager;
import ir.logicfan.core.di.qulifier.ApplicationContext;

@Module
public abstract class BaseModule {

    @Binds
    @ApplicationContext
    abstract Context context(Application application);

    @Provides
    static CompositeDisposable disposable() {
        return new CompositeDisposable();
    }

    @Provides
    static BasePrefManager basePrefManager(@ApplicationContext Context context) {
        return new BasePrefManager(context);
    }
}
