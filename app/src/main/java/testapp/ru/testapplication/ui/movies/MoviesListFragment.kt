package testapp.ru.testapplication.ui.movies

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.movies_list_fragment.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import testapp.ru.testapplication.R
import org.koin.androidx.viewmodel.ext.android.viewModel
import testapp.ru.testapplication.dataModels.MovieItem
import testapp.ru.testapplication.ui.base.OnItemClickListener
import testapp.ru.testapplication.ui.base.addOnItemClickListener
import testapp.ru.testapplication.ui.itemMovie.ItemMovieFragment

@ExperimentalCoroutinesApi
class MoviesListFragment: Fragment(R.layout.movies_list_fragment) {

    private val viewModel: MoviesListViewModel by viewModel()

    private val adapter = MoviesListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView_movies_list.layoutManager = LinearLayoutManager(context)
        recyclerView_movies_list.adapter = adapter
        viewModel.movieList.observe(this, Observer {
            adapter.updateList(it)
        })
        recyclerView_movies_list.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                (adapter.getItem(position) as? MovieItem)?.let { item ->
                    val navDirections = MoviesListFragmentDirections.actionMoviesListFragmentToItemMovieFragment(item.id?:0)
                    findNavController().navigate(navDirections)
                }
            }
        })

    }

}