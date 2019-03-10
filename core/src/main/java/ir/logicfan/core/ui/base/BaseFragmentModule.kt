package ir.logicfan.core.ui.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.tbruyelle.rxpermissions2.RxPermissions
import dagger.Module
import dagger.Provides
import ir.logicfan.core.di.qulifier.FragmentChildFragmentManager
import ir.logicfan.core.di.qulifier.ParentFragment
import ir.logicfan.core.di.scope.PerFragment

@Module
abstract class BaseFragmentModule {

    companion object {

        @Provides
        @FragmentChildFragmentManager
        @PerFragment
        fun childFragmentManager(@ParentFragment fragment: Fragment): FragmentManager {
            return fragment.childFragmentManager
        }

        @Provides
        @PerFragment
        fun rxPermission(@ParentFragment fragment: Fragment): RxPermissions {
            return RxPermissions(fragment)
        }
    }
}
