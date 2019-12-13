package testapp.ru.testapplication.ui.movies

import android.graphics.Color
import android.view.View
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movies_list_adapter.view.*
import testapp.ru.testapplication.R
import testapp.ru.testapplication.dataModels.MovieItem
import testapp.ru.testapplication.ui.base.BaseAdapter
import testapp.ru.testapplication.ui.base.BaseHolder

class MoviesListAdapter : BaseAdapter<MovieItem>() {

    override fun getLayout(viewType: Int): Int = R.layout.movies_list_adapter

    override fun isItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean =
        oldItem.id == newItem.id

    override fun isContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean =
        oldItem.image_url == newItem.image_url || oldItem.name == newItem.name
                || oldItem.rating == newItem.rating || oldItem.year == newItem.year

    override fun createHolder(view: View, viewType: Int): BaseHolder<MovieItem> =
        MoviesListHolder(view)

}

class MoviesListHolder(iv: View) : BaseHolder<MovieItem>(iv) {
    override fun onCreateViewItem(content: MovieItem) {
        with(itemView) {
            var name = content.localized_name?: content.name
            if( name == null) name = ""
            textView_name.text = name

            val year = if ( content.year == null) ""
            else content.year.toString()
            textView_year_values.text = year

            val rating = if( content.rating == null){
                textView_rating_values.setTextColor(Color.BLACK)
                "0.0"
            }
            else{
                val rating = content.rating?:0.0
                if( rating < 5.0){
                    textView_rating_values.setTextColor(resources.getColor(R.color.read))
                }
                else  textView_rating_values.setTextColor(resources.getColor(R.color.green))
                rating.toString()
            }
            textView_rating_values.text = rating
            val ganer = content.genres?: arrayListOf()
            textView_genre_values.text = ganer.toString()
                .replace("[","")
                .replace("]","")
            Picasso.with(this.context)
                .load(content.image_url.toString())
                .placeholder(R.mipmap.image_not_found)
                .error(R.mipmap.image_not_found)
                .into(imageView_poster)
        }
    }

}