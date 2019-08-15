package com.movies

/**
 *Created by marco on 2019-08-09
 */
class Constants {
    class API {
        companion object {
            const val CONNECTION_TIMEOUT_SECONDS = 25L
            const val BASE_URL = "https://api.themoviedb.org/3/"
        }

        class Configuration {
            companion object {
                const val CONFIG = "configuration"
            }
        }

        class Movies {
            companion object {
                const val DISCOVER = "discover/movie"
                const val MOVIE_DETAILS = "movie/{id}"
                const val SEARCH = "search/movie"
            }
        }

        class Preferences {
            companion object {
                const val SHARED_PREFERENCE_DEFAULT_NAME = "key_shared_preferences"
                const val KEY_APP_CONFIG = "key_app_config"
            }
        }

        class Extras {
            companion object {
                const val MOVIE_ID = "key_movie_id"
            }
        }
    }
}