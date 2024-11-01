package com.hacker.thone.kook.ui.fragment

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.video.FileOutputOptions
import androidx.camera.video.Quality
import androidx.camera.video.QualitySelector
import androidx.camera.video.Recorder
import androidx.camera.video.Recording
import androidx.camera.video.VideoCapture
import androidx.camera.video.VideoRecordEvent
import androidx.camera.view.PreviewView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.hacker.thone.kook.R
import com.hacker.thone.kook.databinding.FragmentTakingVideoBinding
import com.hacker.thone.kook.ui.viewModel.PostViewModel
import com.hacker.thone.kook.util.BottomControllable
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale

class TakingVideoFragment : Fragment() {

    private var _binding: FragmentTakingVideoBinding? = null
    private val binding get() = _binding!!
    private lateinit var previewView: PreviewView
    private var videoCapture: VideoCapture<Recorder>? = null
    private var recording: Recording? = null
    private val CAMERA_PERMISSION_CODE = 999
    private val postViewModel by activityViewModels<PostViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTakingVideoBinding.inflate(inflater, container, false)
        (requireActivity() as BottomControllable).setBottomNavVisibility(false)
        previewView = binding.previewView
        checkingPermission()

        binding.startRecordingButton.setOnClickListener {
            if (recording != null) {
                stopRecording()
            } else {
                startRecording()
            }
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onPause() {
        super.onPause()
        (requireActivity() as BottomControllable).setBottomNavVisibility(true)
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()
            val preview = androidx.camera.core.Preview.Builder().build().also {
                it.setSurfaceProvider(previewView.surfaceProvider)
            }

            val recorder = Recorder.Builder()
                .setQualitySelector(QualitySelector.from(Quality.HIGHEST))
                .build()
            videoCapture = VideoCapture.withOutput(recorder)

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, videoCapture
                )
            } catch (e: Exception) {
                Log.e("CameraX", "Error starting camera: ${e.message}")
            }
        }, ContextCompat.getMainExecutor(requireContext()))
    }

    private fun startRecording() {
        Log.d("test", "in func")

        // DCIM 폴더에 파일 저장 경로 설정
        val dcimDirectory = File(requireContext().getExternalFilesDir(null)?.absolutePath + "/Pictures/kook")
        if (!dcimDirectory.exists()) {
            dcimDirectory.mkdirs()  // 폴더가 없으면 생성
        }

        val videoFile = File(
            dcimDirectory,
            SimpleDateFormat(
                "yyyy-MM-dd-HH-mm-ss-SSS",
                Locale.US
            ).format(System.currentTimeMillis()) + ".mp4"
        )

        val outputOptions = FileOutputOptions.Builder(videoFile).build()
        recording = videoCapture?.output
            ?.prepareRecording(requireContext(), outputOptions)
            ?.apply {
                if (ActivityCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.RECORD_AUDIO
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    Log.d("test", "link")
                    checkingPermission()
                    return
                }
                withAudioEnabled()
            }
            ?.start(ContextCompat.getMainExecutor(requireContext())) { recordEvent ->
                when (recordEvent) {
                    is VideoRecordEvent.Start -> {
                        binding.startRecordingButton.text = "Stop Recording"
                    }

                    is VideoRecordEvent.Finalize -> {
                        if (!recordEvent.hasError()) {
                            val msg = "영상 녹화 성공 : ${recordEvent.outputResults.outputUri}"
                            Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
                            recording = null

                            postViewModel.setUri(recordEvent.outputResults.outputUri)
                            findNavController().navigate(R.id.action_takingVideoFragment_to_editVideoFragment)

                            Log.d("CameraX", "Video saved: ${videoFile.absolutePath}")
                            Log.e("CameraX", msg)
                        } else {
                            recording?.close()
                            recording = null
                            Log.e("CameraX", "영상 녹화 에러 : ${recordEvent.error}")
                        }
                        binding.startRecordingButton.text = "Start Recording"
                    }
                }
            }
    }


    private fun stopRecording() {
        recording?.stop()
        recording = null
    }

    private fun checkingPermission() {
        val permissions = mutableListOf<String>()
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            permissions.add(Manifest.permission.CAMERA)
        }

        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.RECORD_AUDIO
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            permissions.add(Manifest.permission.RECORD_AUDIO)
        }

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) {
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
        }

        if (permissions.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                permissions.toTypedArray(),
                CAMERA_PERMISSION_CODE
            )
        } else {
            startCamera()
        }
    }
}