package ir.logicfan.core.data.repository

import io.reactivex.Observable
import okhttp3.ResponseBody

interface GenericRepository {
    fun download(url: String): Observable<ResponseBody>
}