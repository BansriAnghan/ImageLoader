package com.bansri.imageloader.ui

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
//import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bansri.imageloader.Injector
import com.bansri.imageloader.databinding.ActivityPickerBinding

/**
 * Main screen for the picker.
 * This will show a list a photos and a search component.
 * The list is has an infinite scroll.
 */
class ImageLoderActivity : AppCompatActivity(), OnPhotoSelectedListener {
    private lateinit var binding: ActivityPickerBinding

    private lateinit var mLayoutManager: StaggeredGridLayoutManager

    private lateinit var mAdapter: PhotoAdapter

    private lateinit var mViewModel: ImageLoaderViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPickerBinding.inflate(layoutInflater)

        setContentView(binding.root)
        // recycler view layout manager
        mLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        // recycler view adapter
        mAdapter = PhotoAdapter(this)
        mAdapter.setOnImageSelectedListener(this)
        // recycler view configuration
        binding.unsplashPickerRecyclerView.setHasFixedSize(true)
        binding.unsplashPickerRecyclerView.itemAnimator = null
        binding.unsplashPickerRecyclerView.layoutManager = mLayoutManager
        binding.unsplashPickerRecyclerView.adapter = mAdapter
        // click listeners
        binding.unsplashPickerBackImageView.setOnClickListener { onBackPressed() }

        mViewModel =
            ViewModelProvider(this,  Injector.createPickerViewModelFactory()).get(ImageLoaderViewModel::class.java)


        observeViewModel()
        mViewModel.bindSearch(binding.unsplashPickerEditText)
    }

    /**
     * Observes the live data in the view model.
     */
    private fun observeViewModel() {
        mViewModel.errorLiveData.observe(this, Observer {
            Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
        })
        mViewModel.messageLiveData.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
        mViewModel.loadingLiveData.observe(this, Observer {
            binding.unsplashPickerProgressBarLayout.visibility = if (it != null && it) View.VISIBLE else View.GONE
        })
        mViewModel.photosLiveData.observe(this, Observer {
            binding.unsplashPickerNoResultTextView.visibility =
                    if (it == null || it.isEmpty()) View.VISIBLE
                    else View.GONE
            mAdapter.submitList(it)
        })
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        // we want the recycler view to have 3 columns when in landscape and 2 in portrait
        mLayoutManager.spanCount =
                if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) 3
                else 2
        mAdapter.notifyDataSetChanged()
    }

    override fun onPhotoClickPress(imageView: ImageView, url: String) {
        /**
         * @param callingContext the calling context
         * @param url the url of the image to show
         */
        val intent = Intent(this, PhotoShowActivity::class.java)
        intent.putExtra(PhotoShowActivity.EXTRA_URL, url)
        startActivity(intent)
    }
}
