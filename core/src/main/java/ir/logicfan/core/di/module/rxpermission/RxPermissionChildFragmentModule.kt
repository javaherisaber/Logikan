/*
 * Copyright (C) Logicfan, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited Proprietary and confidential
 * Written by Mahdi Javaheri Saber <m.javaherisaber@gmail.com>, Mohammad Hosein Abedini <mohammadhoseinabedini@gmail.com>  2020.
 */

package ir.logicfan.core.di.module.rxpermission

import androidx.fragment.app.Fragment
import com.tbruyelle.rxpermissions2.RxPermissions
import dagger.Module
import dagger.Provides
import ir.logicfan.core.di.qulifier.ChildFragment
import ir.logicfan.core.di.scope.PerChildFragment

@Module
abstract class RxPermissionChildFragmentModule {

    @Module
    companion object {

        @Provides
        @PerChildFragment
        @JvmStatic
        fun rxPermission(@ChildFragment fragment: Fragment): RxPermissions {
            return RxPermissions(fragment)
        }
    }
}