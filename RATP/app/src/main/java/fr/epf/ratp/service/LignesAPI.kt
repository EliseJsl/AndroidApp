package fr.epf.ratp.service


import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LignesAPI {

    @GET("lines/{type}")
    suspend fun getLignes(@Path("type") type: String) : getLignesResults // je stocke le résultat dans chaine de caractère
}

data class getLignesResults(val result: Metros = Metros())

data class Metros(val metros: List<Metro> = emptyList())

data class Metro(val code: String, val name: String="", val directions: String="", val id: String)

