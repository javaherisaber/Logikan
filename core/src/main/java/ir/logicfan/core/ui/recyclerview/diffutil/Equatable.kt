/*
 * Copyright (C) Logicfan, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited Proprietary and confidential
 * Written by Mahdi Javaheri Saber <m.javaherisaber@gmail.com>, Mohammad Hosein Abedini <mohammadhoseinabedini@gmail.com>  2020.
 */

package ir.logicfan.core.ui.recyclerview.diffutil

/**
 * To be used by generic DiffUtil classes in areContentTheSame function
 */
interface Equatable {
    override fun equals(other: Any?): Boolean
}