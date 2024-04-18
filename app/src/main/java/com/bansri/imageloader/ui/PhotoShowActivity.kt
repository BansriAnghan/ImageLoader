package com.bansri.imageloader.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.bansri.imageloader.databinding.ActivityImageShowBinding

class PhotoShowActivity : AppCompatActivity() {
    private lateinit var binding: ActivityImageShowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageShowBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // loading the image thanks to its url
        Picasso.get().load(intent.getStringExtra(EXTRA_URL))
            .into(binding.imageShowView)
        // click listener
        binding.imageShowLayout.setOnClickListener { supportFinishAfterTransition() }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        supportFinishAfterTransition()
    }

    companion object {
        const val EXTRA_URL = "EXTRA_URL"
    }
}
