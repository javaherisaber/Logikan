/*
 * Copyright (C) Logicfan, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited Proprietary and confidential
 * Written by Mahdi Javaheri Saber <m.javaherisaber@gmail.com>, Mohammad Hosein Abedini <mohammadhoseinabedini@gmail.com>  2020.
 */

package ir.logicfan.core.data.network.mock

import android.content.Context
import ir.logicfan.core.data.util.FileUtils
import java.io.IOException
import javax.inject.Inject

/**
 * provide mock json for offlineInterceptor
 *
 * @property requestPathToJsonMap map of api path in endpoint to json path in assets
 */
class MockJsonProvider @Inject
constructor(
    private val context: Context,
    private val requestPathToJsonMap: Map<String, String>
) {

    companion object {
        const val MOCK_PATH_IN_ASSETS = "mock_json"
    }

    /**
     * read mock json or if exception occur's return null
     *
     * @param request api request
     * @return json in string object
     */
    @Throws(IOException::class)
    fun getMockJsonOrNull(request: String): String? {
        val fileName = requestPathToJsonMap[request]
        return fileName?.run {
            // json exists in assets directory
            FileUtils.readTextFileFromAssets(context, "$MOCK_PATH_IN_ASSETS/$this")
        }
    }
}
