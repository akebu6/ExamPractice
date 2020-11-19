package com.myrabohuche.exampractice.datasource.network

import kotlinx.coroutines.Deferred
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

//url  https://docs.google.com/forms/u/0/d/e/
// https://docs.google.com/forms/u/0/d/e/1FAIpQLSetWJ0I1kpufkbHpDvz-whKXHGJYJh1VV-9J5ntYrgFzCkecg/formResponse

//entry.1045781291	"Ikechi"
//entry.1166974658	"08024088573"
//entry.2005620554	"Miraboh"
//entry.546510210	"555555555"


interface ApiService {
    @POST("1FAIpQLSetWJ0I1kpufkbHpDvz-whKXHGJYJh1VV-9J5ntYrgFzCkecg/formResponse")
    @FormUrlEncoded
    fun makeSubmissionAsync(
        @Field("entry.2005620554") firstName: String,
        @Field("entry.1045781291") otherName: String,
        @Field("entry.1166974658") phone: String,
        @Field("entry.546510210") pk: String
    ): Deferred<Unit>
}


//interface ApiService {
//
//    @POST("https://docs.google.com/forms/d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
//    @FormUrlEncoded
//    fun makeSubmissionAsync(
//        @Field("entry.1824927963") emailAddress: String,
//        @Field("entry.1877115667") name: String,
//        @Field("entry.2006916086") lastName: String,
//        @Field("entry.284483984") linkToProject: String
//    ): Deferred<Unit>
//}