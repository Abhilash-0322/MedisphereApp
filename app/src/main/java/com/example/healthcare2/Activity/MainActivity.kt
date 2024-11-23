package com.example.healthcare2.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthcare.Adapter.CategoryAdapter
import com.example.healthcare2.Adapter.TopDoctorAdapter
import com.example.healthcare2.R
import com.example.healthcare2.ViewModel.MainViewModel
import com.example.healthcare2.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel=MainViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initCategory()
        initTopDoctors()

    }

    private fun initTopDoctors() {
        binding.apply {
            progressBarTopDoctors.visibility=View.VISIBLE
            viewModel.doctors.observe(this@MainActivity,Observer{
               recyclerViewTopDoctors.layoutManager=LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
               recyclerViewTopDoctors.adapter=TopDoctorAdapter(it)
               progressBarTopDoctors.visibility=View.GONE
            })
            viewModel.loadDoctors()

             doctorListTxt.setOnClickListener{
                 startActivity(Intent(this@MainActivity,TopDoctorsActivity::class.java))
             }

        }
    }

    private fun initCategory() {
        binding.progressBar3.visibility= View.VISIBLE
        viewModel.category.observe(this, Observer {
            binding.viewCategory.layoutManager=LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
            binding.viewCategory.adapter=CategoryAdapter(it)
            binding.progressBar3.visibility=View.GONE
        })
        viewModel.loadCategory()
    }
}