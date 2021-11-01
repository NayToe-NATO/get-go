package com.codigo.naytoe.getgo

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.codigo.naytoe.getgo.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity(), CarListAdapter.ItemListener {

    private lateinit var binding: ActivitySearchBinding
    private lateinit var adapter: CarListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupClickEvents()
    }

    private fun setupView() {
        binding.inputGroup.btnGo.visibility = View.GONE
        binding.inputGroup.date.tvPlaceholder.text = getString(R.string.date_and_time)
        binding.inputGroup.duration.tvPlaceholder.text = getString(R.string.duration)
        binding.inputGroup.date.tvInput.text = getString(R.string.now)
        binding.inputGroup.duration.tvInput.text = getString(R.string.one_hr)
        Glide.with(this)
            .load(R.drawable.ic_icodated)
            .into(binding.inputGroup.date.ivLogo)
        Glide.with(this)
            .load(R.drawable.ic_icotime)
            .into(binding.inputGroup.duration.ivLogo)

        adapter = CarListAdapter(this)
        binding.rvCar.adapter = adapter

    }

    private fun setupClickEvents() {
        binding.imageView.setOnClickListener {
            finish()
        }
    }

    override fun onClicked() {
        startActivity(Intent(this, SearchActivity::class.java))
    }

}