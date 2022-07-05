package com.enghamza.mviapp.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.enghamza.mviapp.R
import com.enghamza.mviapp.data.api.RetrofitBuilder
import com.enghamza.mviapp.ui.main.adapter.MoviesAdapter
import com.enghamza.mviapp.ui.main.intent.MainIntent
import com.enghamza.mviapp.ui.main.model.newMovie.Movie
import com.enghamza.mviapp.ui.main.viewmodel.MainViewModel
import com.enghamza.mviapp.ui.main.viewstate.MainViewStates
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {

    lateinit var btn:Button
    lateinit var recyclerView: RecyclerView
    lateinit var progress: ProgressBar
    lateinit var viewModel: MainViewModel
      var moviesAdapter= MoviesAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn=findViewById(R.id.button)
        recyclerView=findViewById(R.id.rv_movies)
        progress=findViewById(R.id.progress)

        setupRecycler()
        setupClicks()
        setupViewModel()
        observeData()
    }
    private fun setupRecycler(){
        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.adapter=moviesAdapter
    }
    private fun setupClicks() {
        btn.setOnClickListener {
            lifecycleScope.launchWhenStarted {
                viewModel.moviesChanelIntents.send(MainIntent.GetMovies)
            }
        }

    }
    private fun setupViewModel() {
        viewModel=ViewModelProvider(this,MainViewModelFactory(RetrofitBuilder.getClient())
        )[MainViewModel::class.java]    }
    private fun  observeData(){
        lifecycleScope.launchWhenStarted {
            viewModel.states.collect {
                when(it){
                    is MainViewStates.Idle->{
                        progress.visibility= View.GONE
                        recyclerView.visibility= View.GONE
                        btn.visibility= View.VISIBLE
                    }
                    is MainViewStates.Loading->{
                        progress.visibility= View.VISIBLE
                        recyclerView.visibility= View.GONE
                        btn.visibility= View.GONE
                    }
                    is MainViewStates.Movies->{
                        progress.visibility= View.GONE
                        btn.visibility= View.GONE
                        recyclerView.visibility= View.VISIBLE
                        createRecyclerView(it.list!!)
                    }
                    is MainViewStates.Error->{
                        progress.visibility= View.GONE
                        recyclerView.visibility= View.GONE
                        btn.visibility= View.VISIBLE
                        Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_LONG).show()
                    }
                }
            }

        }
    }
    fun createRecyclerView(list: List<Movie>){
        moviesAdapter.addData(list)
        moviesAdapter.notifyDataSetChanged()
    }

}