package ir.logicfan.core.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerAppCompatActivity;
import ir.logicfan.core.data.pref.BasePrefManager;
import ir.logicfan.core.di.qulifier.ActivityFragmentManager;
import ir.logicfan.core.util.LocaleManager;

import javax.inject.Inject;

/**
 * Created by Mahdi on 12/16/2017.
 * All of our activities extends this class to inherit top level functionality
 */

public abstract class BaseActivity extends DaggerAppCompatActivity {

    @Inject
    BasePrefManager prefManager;

    @Inject
    @ActivityFragmentManager
    protected FragmentManager fragmentManager;

    private Unbinder butterKnifeUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LocaleManager.setLocale(this, prefManager.getSettingsPrefLangList());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    protected void replaceFragmentWithFadeAnimation(@IdRes int container, Fragment fragment, boolean addToBackStack) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(container, fragment);
        if (addToBackStack) {
            ft.addToBackStack(null); // argument name is optional
        }
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);  // add animation to replacement process
        ft.commit();
    }

    protected void setButterKnifeUnbinder(Activity activity) {
        butterKnifeUnbinder = ButterKnife.bind(activity);
    }

    @Override
    protected void onDestroy() {
        if (butterKnifeUnbinder != null) {
            butterKnifeUnbinder.unbind();
        }
        super.onDestroy();
    }
}
