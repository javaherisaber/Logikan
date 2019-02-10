package ir.logicfan.core.ui.base;

import android.app.Activity;
import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import ir.logicfan.core.di.qulifier.ActivityContext;
import ir.logicfan.core.di.qulifier.ActivityFragmentManager;
import ir.logicfan.core.di.scope.PerActivity;

@Module
public abstract class BaseActivityModule {

    @Provides
    @ActivityFragmentManager
    @PerActivity
    static FragmentManager activityFragmentManager(AppCompatActivity activity) {
        return activity.getSupportFragmentManager();
    }

    @Binds
    @ActivityContext
    @PerActivity
    abstract Context activityContext(Activity activity);
}
