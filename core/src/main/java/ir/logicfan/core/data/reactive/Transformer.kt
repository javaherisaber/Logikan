/*
 * Copyright (C) Logicfan, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited Proprietary and confidential
 * Written by Mahdi Javaheri Saber <m.javaherisaber@gmail.com>, Mohammad Hosein Abedini <mohammadhoseinabedini@gmail.com>  2020.
 */

package ir.logicfan.core.data.reactive

import io.reactivex.ObservableTransformer

/**
 * Let you get rid of defining T value two times
 */
abstract class Transformer<T> : ObservableTransformer<T, T>