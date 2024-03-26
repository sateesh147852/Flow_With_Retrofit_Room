package com.flow.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.flow.R
import com.flow.adapter.ShoppingAdapter
import com.flow.data.local.ShoppingDao
import com.flow.data.local.ShoppingDatabase
import com.flow.databinding.ActivityMainBinding
import com.flow.model.ShoppingItem
import com.flow.viewmodel.ShoppingViewModel
import com.flow.viewmodel.ShoppingViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), View.OnClickListener {


    private lateinit var binding: ActivityMainBinding
    private lateinit var shoppingDao: ShoppingDao
    private lateinit var viewModel: ShoppingViewModel
    private var shoppingAdapter: ShoppingAdapter = ShoppingAdapter(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        initRoomDatabase()
        initViewModel()
    }

    private fun initViews() {
        binding.rvShoppingItems.adapter = shoppingAdapter
        binding.btSubmit.setOnClickListener(this)
        binding.btNext.setOnClickListener(this)
    }

    private fun initViewModel() {
        val shoppingViewModelFactory =
            ShoppingViewModelFactory(shoppingDao)
        viewModel = ViewModelProvider(this, shoppingViewModelFactory)[ShoppingViewModel::class.java]
        viewModel.getShoppingItems()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.sharedFlow.collectLatest {
                    updateAdapter(it)
                }
            }
        }
    }

    private fun updateAdapter(shoppingItems: List<ShoppingItem>) {
        binding.progressCircular.visibility = View.GONE
        shoppingAdapter.updateItem(shoppingItems)
    }

    private fun initRoomDatabase() {
        val shoppingDatabase = ShoppingDatabase.getInstance(this)
        shoppingDatabase?.let {
            shoppingDao = it.getShoppingDao()
        }
    }

    override fun onClick(view: View) {
        when (view.id) {

            R.id.btSubmit -> {
                lifecycleScope.launch(Dispatchers.IO) {
                    viewModel.addItemsIntoDataBase(
                        ShoppingItem(
                            binding.etId.text.toString().toInt(),
                            binding.etPrice.text.toString().toInt(),
                            binding.etAmount.text.toString().toFloat(),
                            binding.etItemName.text.toString(),
                        )
                    )
                }
            }

            R.id.btNext -> {
                Intent(this, SecondActivity::class.java).also {
                    startActivity(it)
                }
            }
        }
    }

}