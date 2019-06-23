package ir.logicfan.core.domain.repository

import io.reactivex.Observable
import okhttp3.ResponseBody

interface GenericRepository {
    fun download(url: String): Observable<ResponseBody>
}