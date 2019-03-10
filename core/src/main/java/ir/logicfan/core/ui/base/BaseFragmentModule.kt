package ir.logicfan.core.ui.base

import androidx.fragment.app.Fragment
import com.tbruyelle.rxpermissions2.RxPermissions
import dagger.Module
import dagger.Provides
import ir.logicfan.core.di.qulifier.ParentFragment
import ir.logicfan.core.di.scope.PerFragment

@Module
abstract class BaseFragmentModule {

    @Module
    companion object {

        @Provides
        @PerFragment
        @JvmStatic
        fun rxPermission(@ParentFragment fragment: Fragment): RxPermissions {
            return RxPermissions(fragment)
        }
    }
}
