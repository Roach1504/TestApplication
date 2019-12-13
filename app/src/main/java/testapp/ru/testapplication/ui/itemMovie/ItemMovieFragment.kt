package testapp.ru.testapplication.ui.itemMovie

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie_fragment.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.core.parameter.parametersOf
import testapp.ru.testapplication.R
import org.koin.androidx.viewmodel.ext.android.viewModel
import testapp.ru.testapplication.ui.ISetTitle

@ExperimentalCoroutinesApi
class ItemMovieFragment : Fragment(R.layout.item_movie_fragment) {

    private val args: ItemMovieFragmentArgs by navArgs()

    private val viewModel: ItemMovieViewModel by viewModel {
        parametersOf(args.id)
    }

    var onSetTitle: ISetTitle? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onSetTitle = context as? ISetTitle
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val item = viewModel.getItem() ?: return
        onSetTitle?.setTitle(item.localized_name ?: "")
        val year = if (item.year == null) ""
        else item.year.toString()

        textView_year_values.text = year

        val rating = if (item.rating == null) {
            textView_rating_values.setTextColor(Color.BLACK)
            "0.0"
        } else {
            val rating = item.rating ?: 0.0
            if (rating < 5.0) {
                textView_rating_values.setTextColor(resources.getColor(R.color.read))
            } else textView_rating_values.setTextColor(resources.getColor(R.color.green))
            rating.toString()
        }
        textView_rating_values.text = rating

        textView_original_name.text = item.name

        textView_description.text = item.description

        Picasso.with(this.context)
            .load(item.image_url.toString())
            .placeholder(R.mipmap.image_not_found)
            .error(R.mipmap.image_not_found)
            .into(imageView_poster)
    }
}