import com.destanti.MovieDB.Base.GlobalConfig
import com.destanti.MovieDB.Services.MovieService
import com.destanti.MovieDB.data.Model.MovieDetailModel

import com.destanti.MovieDB.Services.MovieDataSource
import com.destanti.MovieDB.data.Model.MovieList
import com.destanti.MovieDB.data.Model.ReviewModel
import com.destanti.MovieDB.data.Model.VideoDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieRemoteDataSource(var movieService: MovieService): MovieDataSource {

    companion object {
        private var INSTANCE: MovieRemoteDataSource? = null

        @JvmStatic
        fun getInstance(): MovieRemoteDataSource {
            if (INSTANCE == null) {
                synchronized(MovieRemoteDataSource::javaClass) {
                    val retrofit = Retrofit.Builder()
                            .baseUrl(GlobalConfig.baseUrl)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()

                    INSTANCE = MovieRemoteDataSource(retrofit.create(MovieService::class.java))
                }
            }
            return INSTANCE!!
        }

    }

    override fun getMovieDetail(id: Int, callback: MovieDataSource.LoadMovieDetailCallback) {
        movieService.getMovieDetail(movieId = id, apiKey = GlobalConfig.apiKey).enqueue(object : Callback<MovieDetailModel> {
            override fun onResponse(call: Call<MovieDetailModel>,
                                    response: Response<MovieDetailModel>) {
//                Log.d("test","url sports response Team "+ response.body())
//                Log.d("test","url sports response Team url "+ response.raw().request().url())

                response.body()?.let { callback.onSuccess(it) }
            }

            override fun onFailure(call: Call<MovieDetailModel>, t: Throwable) {
                callback.onFailed(t)
            }
        })

    }

    override fun getVideoDetail(id: Int, callback: MovieDataSource.LoadVideoDetailCallback) {
        movieService.getVideoDetail(movieId = id, apiKey = GlobalConfig.apiKey).enqueue(object : Callback<VideoDetail>{
            override fun onResponse(call: Call<VideoDetail>, response: Response<VideoDetail>) {
                response.body()?.let { callback.onSuccess(it) }
            }

            override fun onFailure(call: Call<VideoDetail>, t: Throwable) {
                callback.onFailed(t)
            }
        })
    }

    override fun getUserReviewList(id: Int, callback: MovieDataSource.LoadUserReviewCallback) {
        movieService.getUserReviewList(movieId = id, apiKey = GlobalConfig.apiKey).enqueue(object : Callback<ReviewModel>{
            override fun onResponse(call: Call<ReviewModel>, response: Response<ReviewModel>) {
                response.body()?.let { callback.onSuccess(it) }
            }

            override fun onFailure(call: Call<ReviewModel>, t: Throwable) {
                callback.onFailed(t)
            }
        })
    }

    override fun getMovieListByGenre(
        id: Int,
        page: Int,
        callback: MovieDataSource.LoadMovieListByGenreCallback
    ) {
        movieService.getMovieListByGenre(apiKey = GlobalConfig.apiKey, page = page, genreId = id).enqueue(object  : Callback<MovieList>{
            override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
                response.body()?.let { callback.onSuccess(it) }
            }

            override fun onFailure(call: Call<MovieList>, t: Throwable) {
                callback.onFailed(t)
            }
        })
    }
}
