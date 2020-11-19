package ir.logicbase.core.di.module.rxpermission

import androidx.fragment.app.Fragment
import com.tbruyelle.rxpermissions2.RxPermissions
import dagger.Module
import dagger.Provides
import ir.logicbase.core.di.qulifier.ChildFragment
import ir.logicbase.core.di.scope.PerChildFragment

@Module
abstract class RxPermissionChildFragmentModule {

    companion object {
        @Provides
        @PerChildFragment
        fun rxPermission(@ChildFragment fragment: Fragment): RxPermissions {
            return RxPermissions(fragment)
        }
    }
}