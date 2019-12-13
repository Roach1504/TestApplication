package testapp.ru.testapplication.dataModels

class MovieList {
    var films = arrayListOf<MovieItem>()
}

class MovieItem {
    var id: Int? = null

    var localized_name: String? = null

    var name: String? = null

    var year: Int? = null

    var rating: Double? = null

    var image_url: String? = null

    var description: String? = null

    var genres: ArrayList<String>? = null

}