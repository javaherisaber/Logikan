package ir.logicbase.core.di.module.rxpermission

import androidx.fragment.app.Fragment
import com.tbruyelle.rxpermissions2.RxPermissions
import dagger.Module
import dagger.Provides
import ir.logicbase.core.di.qulifier.ParentFragment
import ir.logicbase.core.di.scope.PerFragment

@Module
abstract class RxPermissionFragmentModule {

    companion object {
        @Provides
        @PerFragment
        fun rxPermission(@ParentFragment fragment: Fragment): RxPermissions {
            return RxPermissions(fragment)
        }
    }
}
