package ru.referon.listofmovies

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import ru.referon.listofmovies.adapter.Adapter
import ru.referon.listofmovies.databinding.ActivityMainBinding
import ru.referon.listofmovies.viewmodel.ViewModel


class MainActivity : AppCompatActivity() {
    val viewModel: ViewModel by viewModels()
    val adapter = Adapter()
    var page = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.loadMovies(page)
        binding.list.adapter = adapter
        viewModel.data.observe(this) {
            binding.progress.isVisible = it.loading
            adapter.submitList(it.movie?.results)
        }

       binding.list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    page += 20
                    viewModel.loadMovies(page)
                }
                if(!recyclerView.canScrollVertically(-1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (page < 20){
                        return
                    } else{
                        page -= 20
                        viewModel.loadMovies(page)
                    }
                }
            }
        })
    }
}