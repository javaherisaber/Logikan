package ir.logicfan.core.ui.base;

import android.content.Context;
import android.view.View;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerFragment;
import ir.logicfan.core.di.qulifier.ActivityContext;
import ir.logicfan.core.di.qulifier.FragmentChildFragmentManager;

import javax.inject.Inject;

public abstract class BaseFragment extends DaggerFragment {

    @Inject
    @ActivityContext
    protected Context activityContext;

    @Inject
    @FragmentChildFragmentManager
    protected FragmentManager childFragmentManager;

    private Unbinder butterKnifeUnbinder;

    protected final void addFragment(@IdRes int containerViewId, Fragment fragment) {
        childFragmentManager.beginTransaction()
                .add(containerViewId, fragment)
                .commit();
    }

    protected void setButterKnifeUnbinder(@NonNull Object target, @NonNull View source) {
        butterKnifeUnbinder = ButterKnife.bind(target, source);
    }

    @Override
    public void onDestroy() {
        if (butterKnifeUnbinder != null) {
            butterKnifeUnbinder.unbind();
        }
        super.onDestroy();
    }
}
