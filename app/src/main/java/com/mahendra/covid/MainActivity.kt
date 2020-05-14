package com.mahendra.covid

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.mahendra.covid.databinding.ActivityMainBinding
import com.mahendra.covid.fragment.MainFragment
import com.mahendra.covid.fragment.TotalCasesFragment
import com.mahendra.covid.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private var viewModel : MainActivityViewModel? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        supportActionBar?.setSubtitle("Last updated 5 mins ago")

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        binding.viewModel = viewModel
        menu.setOnItemSelectedListener {
            when(it){
                R.id.home ->{
                    supportActionBar?.setTitle("Covid-19 Live Tracking")
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment,TotalCasesFragment.newInstance(),TotalCasesFragment::class.java.name)
                        .commitAllowingStateLoss()
                }
                R.id.about ->{

                }
                R.id.country ->{
                    supportActionBar?.setTitle("Covid-19 Countrywise")
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment,MainFragment.newInstance(),MainFragment::class.java.name)
                        .commitAllowingStateLoss()
                }
            }
        }
        menu.setItemSelected(R.id.home,true)

    }

}
