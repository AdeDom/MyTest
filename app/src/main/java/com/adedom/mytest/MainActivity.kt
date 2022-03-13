package com.adedom.mytest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.adedom.mytest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val mainRemoteDataSource: MainRemoteDataSource = MainRemoteDataSourceImpl()
        val mainLocalDataSource: MainLocalDataSource = MainLocalDataSourceImpl()
        val mainRepository: MainRepository = MainRepositoryImpl(
            mainLocalDataSource,
            mainRemoteDataSource
        )
        val mainViewModelFactory = MainViewModelFactory(mainRepository)
        viewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)

        val mainAdapter = MainAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(baseContext)
        binding.recyclerView.adapter = mainAdapter

        viewModel.mainList.observe(this) { list ->
            mainAdapter.submitList(list)
        }

        viewModel.fetchMainList()
    }
}

class MainViewModel(
    private val mainRepository: MainRepository,
) : ViewModel() {

    private val _mainList = MutableLiveData<List<MainModel>>()
    val mainList: LiveData<List<MainModel>> = _mainList

    fun fetchMainList() {
        try {
            _mainList.value = mainRepository.fetchMainList()
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }
}