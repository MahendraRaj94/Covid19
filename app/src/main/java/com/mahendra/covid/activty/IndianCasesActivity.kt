package com.mahendra.covid.activty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mahendra.covid.R
import com.mahendra.covid.fragment.IndianStatesFragment
import com.mahendra.covid.fragment.MainFragment

class IndianCasesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_indian_cases)

        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, IndianStatesFragment.newInstance(), IndianStatesFragment::class.java.name)
            .commitAllowingStateLoss()

    }
}
