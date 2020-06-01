package fr.epf.ratp.service


import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LignesAPI {

    @GET("lines/{type}")
    suspend fun getLignes(@Path("type") type: String) : getLignesResults // je stocke le résultat dans chaine de caractère

    @GET("stations/{type}/{code}")
    suspend fun getStations(@Path("type") type: String, @Path("code") code: String) : getStationsResults

    @GET("schedules/{type}/{code}/{station}/{way}")
    suspend fun getStationSchedules(@Path("type") type: String, @Path("code") code: String,  @Path("station") station: String, @Path("way") way: String) : getStationSchedulesResults

    @GET("traffic/{type}/{code}")
    suspend fun getTraffic(@Path("type") type: String, @Path("code") code: String) : getTrafficResults
}

data class getTrafficResults(val result: Traffic = Traffic())

data class Traffic(val line: String = "", val slug: String ="", val title: String = "", val message: String ="")

data class getLignesResults(val result: Metros = Metros())

data class Metros(val metros: List<Metro> = emptyList())

data class Metro(val code: String, val name: String="", val directions: String="", val id: String)

data class getStationsResults(val result: Stations = Stations())

data class Stations(val stations: List<Station> = emptyList())

data class Station(val name: String, val slug: String)

data class getStationSchedulesResults( val result: StationsSchedules=StationsSchedules() )

data class StationsSchedules(val schedules: List<StationSchedules> = emptyList())

data class StationSchedules(val message: String, val destination: String)