package br.com.fiap.gs_kotlin

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.gs_kotlin.viewmodel.ItemsAdapter
import br.com.fiap.gs_kotlin.viewmodel.ItemsViewModel
import br.com.fiap.gs_kotlin.viewmodel.ItemsViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ItemsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Dicas"

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val itemsAdapter = ItemsAdapter { item ->
            viewModel.removeItem(item)
        }

        recyclerView.adapter = itemsAdapter

        val button = findViewById<Button>(R.id.button)
        val tipText = findViewById<EditText>(R.id.tip)
        val descriptionText = findViewById<EditText>(R.id.description)

        button.setOnClickListener {
            if (tipText.text.isEmpty()) {
                tipText.error = "Preencha um valor"
                return@setOnClickListener
            }
            if (descriptionText.text.isEmpty()) {
                descriptionText.error = "Preencha um valor"
                return@setOnClickListener
            }

            viewModel.addItem(tipText.text.toString(), descriptionText.text.toString())
            tipText.text.clear()
            descriptionText.text.clear()
        }

        val viewModelFactory = ItemsViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ItemsViewModel::class.java)
        viewModel.itemsLiveData.observe(this) { items ->
            itemsAdapter.updateItems(items)
        }
    }
}
