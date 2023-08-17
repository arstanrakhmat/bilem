package com.example.bilemonline.app.fragments.section.sectionType

import android.net.Uri
import android.os.Bundle
import com.google.android.exoplayer2.MediaItem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bilemonline.app.fragments.BaseFragment
import com.example.bilemonline.data.model.ContentX
import com.example.bilemonline.databinding.FragmentVideoBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.SimpleExoPlayer

class VideoFragment(private val videoContent: ContentX) : BaseFragment<FragmentVideoBinding>() {

    private var exoPlayer: ExoPlayer? = null
    private var simpleExoPlayer: SimpleExoPlayer? = null

    companion object {
        const val URL = "https://kinescope.io/embed/204770908"
        const val URL2 = "https://kinescope.io/204770908"
        const val videoUrl =
            "https://media.geeksforgeeks.org/wp-content/uploads/20201217192146/Screenrecorder-2020-12-17-19-17-36-828.mp4?_=1"
        const val URL3 = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
    }

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentVideoBinding {
        return FragmentVideoBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvHeader.text = videoContent.header
        preparePlayer4()
    }

    private fun preparePlayer2() {
        exoPlayer = ExoPlayer.Builder(requireContext()).build()
        binding.playerView.player = exoPlayer
        val uri = Uri.parse(URL)
        val mediaItem = MediaItem.fromUri(uri)
        exoPlayer?.addMediaItem(mediaItem)
        exoPlayer?.seekTo(0)
        exoPlayer?.playWhenReady = false
        exoPlayer?.prepare()

    }

    private fun preparePlayer4() {
        simpleExoPlayer = SimpleExoPlayer.Builder(requireContext()).build()
        binding.playerView.player = simpleExoPlayer
        val mediaItem = MediaItem.fromUri(URL3)
        simpleExoPlayer?.addMediaItem(mediaItem)
        simpleExoPlayer?.prepare()
        simpleExoPlayer?.playWhenReady = true
    }

}