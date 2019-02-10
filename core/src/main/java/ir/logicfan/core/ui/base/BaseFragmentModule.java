package ir.logicfan.core.ui.base;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.tbruyelle.rxpermissions2.RxPermissions;
import dagger.Module;
import dagger.Provides;
import ir.logicfan.core.di.qulifier.FragmentChildFragmentManager;
import ir.logicfan.core.di.qulifier.ParentFragment;
import ir.logicfan.core.di.scope.PerFragment;

@Module
public abstract class BaseFragmentModule {

    @Provides
    @FragmentChildFragmentManager
    @PerFragment
    static FragmentManager childFragmentManager(@ParentFragment Fragment fragment) {
        return fragment.getChildFragmentManager();
    }

    @Provides
    @PerFragment
    static RxPermissions rxPermission(@ParentFragment Fragment fragment) {
        return new RxPermissions(fragment);
    }
}
