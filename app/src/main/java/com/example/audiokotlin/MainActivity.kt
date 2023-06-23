package com.example.audiokotlin

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat


class MainActivity : AppCompatActivity() {

    var startPoint = 1
    var endPoint = 100
    var startPoint2 = 1
    var endPoint2 = 100
    var startPoint3 = 1
    var endPoint3 = 100
    var startPointleft = 1
    var endPointleft = 100
    var startPointright = 1
    var endPointright = 100
    var media1 : Uri? = null
    var mediaPlayer1 : MediaPlayer? = null
    var media2 : Uri? = null
    var mediaPlayer2 : MediaPlayer? = null
    var media3 : Uri? = null
    var mediaPlayer3 : MediaPlayer? = null

    private lateinit var btnPlay1 : Button
    private lateinit var btnStop1 : Button
    private lateinit var add1 : ImageButton
    private lateinit var btnReset1 : ImageButton
    private var booleanPlay1 : Boolean = false
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var mute1 : SwitchCompat
    private lateinit var gain1 : SeekBar
    private lateinit var volume1 : TextView
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var pfl1 : SwitchCompat
    var vol1 : Float = 0.5f

    private lateinit var btnPlay2 : Button
    private lateinit var btnStop2 : Button
    private lateinit var add2 : ImageButton
    private lateinit var btnReset2 : ImageButton
    private var booleanPlay2 : Boolean = false
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var mute2 : SwitchCompat
    private lateinit var gain2 : SeekBar
    private lateinit var volume2 : TextView
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var pfl2 : SwitchCompat
    var vol2 : Float = 0.5f

    private lateinit var btnPlay3 : Button
    private lateinit var btnStop3 : Button
    private lateinit var add3 : ImageButton
    private lateinit var btnReset3 : ImageButton
    private var booleanPlay3 : Boolean = false
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var mute3 : SwitchCompat
    private lateinit var gain3 : SeekBar
    private lateinit var volume3 : TextView
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var pfl3 : SwitchCompat
    var vol3 : Float = 0.5f

    private lateinit var info : ImageButton
    private lateinit var btnPlay : Button
    private lateinit var btnStop : Button
    private lateinit var btnReset : ImageButton
    private var booleanPlay : Boolean = false
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var gainleft : SeekBar
    private lateinit var gainright : SeekBar
    private lateinit var volumeleft : TextView
    private lateinit var volumeright : TextView
    var volleft : Float = 0.75f
    var volright : Float = 0.75f


    @SuppressLint("MissingInflatedId")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnPlay1 = findViewById(R.id.btnPlay1)
        btnStop1 = findViewById(R.id.btnStop1)
        add1 = findViewById(R.id.add1)
        btnReset1 = findViewById(R.id.btnReset1)
        mute1 = findViewById(R.id.mute1)
        gain1 = findViewById(R.id.gain1)
        volume1 = findViewById(R.id.volume1)
        pfl1 = findViewById(R.id.pfl1)

        btnPlay2 = findViewById(R.id.btnPlay2)
        btnStop2 = findViewById(R.id.btnStop2)
        add2 = findViewById(R.id.add2)
        btnReset2 = findViewById(R.id.btnReset2)
        mute2 = findViewById(R.id.mute2)
        gain2 = findViewById(R.id.gain2)
        volume2 = findViewById(R.id.volume2)
        pfl2 = findViewById(R.id.pfl2)

        btnPlay3 = findViewById(R.id.btnPlay3)
        btnStop3 = findViewById(R.id.btnStop3)
        add3 = findViewById(R.id.add3)
        btnReset3 = findViewById(R.id.btnReset3)
        mute3 = findViewById(R.id.mute3)
        gain3 = findViewById(R.id.gain3)
        volume3 = findViewById(R.id.volume3)
        pfl3 = findViewById(R.id.pfl3)

        info = findViewById(R.id.info)
        btnPlay = findViewById(R.id.btnPlay)
        btnStop = findViewById(R.id.btnStop)
        btnReset = findViewById(R.id.btnReset)
        gainleft = findViewById(R.id.gainleft)
        gainright = findViewById(R.id.gainright)
        volumeleft = findViewById(R.id.volumeleft)
        volumeright = findViewById(R.id.volumeright)

        // FUNCIONALITY SOUND 1
        add1.setOnClickListener {
            val intent = Intent()
                .setType("audio/*")
                .setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(Intent.createChooser(intent, "Select an audio file"), 1)
        }
        btnPlay1.setOnClickListener{
            booleanPlay1 = true
            if ((!booleanPlay) and (!booleanPlay2) and (!booleanPlay3)) {
                stopAudio1()
                playAudio1()
                mediaPlayer1?.setVolume(vol1, vol1)
            }
        }
        btnStop1.setOnClickListener{
            stopAudio1()
            booleanPlay1 = false
        }
        btnReset1.setOnClickListener{
            gain1.progress = 50
            media1 = null
            if (mute1.isChecked){
                mute1.isChecked = false
            }
        }
        mute1.setOnClickListener {
            if (mediaPlayer1?.isPlaying == true) {
                if (mute1.isChecked){
                    mediaPlayer1?.setVolume(0F,0F)
                } else {
                    mediaPlayer1?.setVolume(vol1,vol1)
                }
            }
        }
        gain1.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                volume1.text = progress.toString()
                vol1 = (progress * 0.01).toFloat()
                if (booleanPlay) {
                    mediaPlayer1?.setVolume((volleft*vol1),(volright*vol1))
                }else {
                    mediaPlayer1?.setVolume(vol1, vol1)
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                if (seekBar != null) {
                    startPoint = seekBar.progress
                }
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if (seekBar != null) {
                    endPoint = seekBar.progress
                }
                if (seekBar != null) {
                    Toast.makeText(this@MainActivity, "Ganancia 1: " + seekBar.progress, Toast.LENGTH_SHORT).show()
                }
            }
        })

        // FUNCIONALITY SOUND 2
        add2.setOnClickListener {
            val intent = Intent()
                .setType("audio/*")
                .setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(Intent.createChooser(intent, "Select an audio file"), 2)
        }
        btnPlay2.setOnClickListener{
            booleanPlay2 = true
            if ((!booleanPlay) and (!booleanPlay1) and (!booleanPlay3)) {
                stopAudio2()
                playAudio2()
                mediaPlayer2?.setVolume(vol2, vol2)
            }
        }
        btnStop2.setOnClickListener{
            stopAudio2()
            booleanPlay2 = false
        }
        btnReset2.setOnClickListener{
            gain2.progress = 50
            media2 = null
            if (mute2.isChecked){
                mute2.isChecked = false
            }
        }
        mute2.setOnClickListener {
            if (mediaPlayer2?.isPlaying == true) {
                if (mute2.isChecked){
                    mediaPlayer2?.setVolume(0F,0F)
                } else {
                    mediaPlayer2?.setVolume(vol2,vol2)
                }
            }
        }
        gain2.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                volume2.text = progress.toString()
                vol2 = (progress * 0.01).toFloat()
                mediaPlayer2?.setVolume(vol2,vol2)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                if (seekBar != null) {
                    startPoint2 = seekBar.progress
                }
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if (seekBar != null) {
                    endPoint2 = seekBar.progress
                }
                if (seekBar != null) {
                    Toast.makeText(this@MainActivity, "Ganancia 2: " + seekBar.progress, Toast.LENGTH_SHORT).show()
                }
            }
        })

        // FUNCIONALITY SOUND 3
        add3.setOnClickListener {
            val intent = Intent()
                .setType("audio/*")
                .setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(Intent.createChooser(intent, "Select an audio file"), 3)
        }
        btnPlay3.setOnClickListener{
            booleanPlay3 = true
            if ((!booleanPlay) and (!booleanPlay1) and (!booleanPlay2)) {
                stopAudio3()
                playAudio3()
                mediaPlayer3?.setVolume(vol3, vol3)
            }
        }
        btnStop3.setOnClickListener{
            stopAudio3()
            booleanPlay3 = false
        }
        btnReset3.setOnClickListener{
            gain3.progress = 50
            media3 = null
            if (mute3.isChecked){
                mute3.isChecked = false
            }
        }
        mute3.setOnClickListener {
            if (mediaPlayer3?.isPlaying == true) {
                if (mute3.isChecked){
                    mediaPlayer3?.setVolume(0F,0F)
                } else {
                    mediaPlayer3?.setVolume(vol3,vol3)
                }
            }
        }
        gain3.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                volume3.text = progress.toString()
                vol3 = (progress * 0.01).toFloat()
                mediaPlayer3?.setVolume(vol3,vol3)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                if (seekBar != null) {
                    startPoint3 = seekBar.progress
                }
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if (seekBar != null) {
                    endPoint3 = seekBar.progress
                }
                if (seekBar != null) {
                    Toast.makeText(this@MainActivity, "Ganancia 3: " + seekBar.progress, Toast.LENGTH_SHORT).show()
                }
            }
        })

        // MAIN
        info.setOnClickListener{
            val dialog = AlertDialog.Builder(this)

            dialog.setTitle("INFORMACIÓN")
                .setMessage("¡Gracias por usar esta aplicación!\n\n" +
                        "Es recomendable el uso de la aplicación con auriculares.\n\n" +
                        "Existen tres pistas, todas de ellas independientes.\n\n" +
                        "- Se puede introducir un audio manualmente, o pulsar PLAY sin seleccionar nada para obtener una pista por defecto.\n" +
                        "- El botón 'play' se reproducirá el audio.\n" +
                        "- El botón 'stop' para la pista.\n" +
                        "- El botón 'reset' pondrá la ganancia de la pista como al inciar la app.\n" +
                        "- El toggle 'main' habilitar la reproducción de la pista en la salida MASTER\n" +
                        "- La salida MASTER es estéreo, funcionará a su vez con las ganancias individuales.\n" +
                        "- El botón 'play' activará las pistas que tuvieran el main activado de base.\n" +
                        "- El botón 'stop' parará la reproducción.\n" +
                        "- El botón 'reset' cambiará las ganancia MASTER como al iniciar la app.")
                .setPositiveButton("ACEPTAR") { dialog, whichButton ->

                }
            dialog.show()
        }
        btnPlay.setOnClickListener{
            booleanPlay = true
            if ((!booleanPlay1) and (!booleanPlay2) and (!booleanPlay3)) {
                if (pfl1.isChecked) {
                    stopAudio1()
                    playAudio1()
                    mediaPlayer1?.setVolume(vol1, vol1)
                }
                if (pfl2.isChecked) {
                    stopAudio2()
                    playAudio2()
                    mediaPlayer2?.setVolume(vol2, vol2)
                }
                if (pfl3.isChecked) {
                    stopAudio3()
                    playAudio3()
                    mediaPlayer3?.setVolume(vol3, vol3)
                }
            }
        }
        btnStop.setOnClickListener{
            if (booleanPlay) {
                stopAudio1()
                stopAudio2()
                stopAudio3()
            }
            booleanPlay = false
        }
        btnReset.setOnClickListener{
            gainleft.progress = 75
            gainright.progress = 75
        }

        gainleft.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                volumeleft.text = progress.toString()
                volleft = (progress * 0.01).toFloat()
                if (booleanPlay) {
                    mediaPlayer1?.setVolume((volleft*vol1),(volright*vol1))
                    mediaPlayer2?.setVolume((volleft*vol2),(volright*vol2))
                    mediaPlayer3?.setVolume((volleft*vol3),(volright*vol3))
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                if (seekBar != null) {
                    startPointleft = seekBar.progress
                }
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if (seekBar != null) {
                    endPointleft = seekBar.progress
                }
            }
        })

        gainright.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                volumeright.text = progress.toString()
                volright = (progress * 0.01).toFloat()
                if (booleanPlay) {
                    mediaPlayer1?.setVolume((volleft*vol1),(volright*vol1))
                    mediaPlayer2?.setVolume((volleft*vol2),(volright*vol2))
                    mediaPlayer3?.setVolume((volleft*vol3),(volright*vol3))
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                if (seekBar != null) {
                    startPointright = seekBar.progress
                }
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if (seekBar != null) {
                    endPointright = seekBar.progress
                }
            }
        })
    }

    private fun playAudio1(){
        if (media1 != null) {
            mediaPlayer1 = MediaPlayer.create(this, media1)
        }else{
            mediaPlayer1 = MediaPlayer.create(this, R.raw.piano)
        }
        mediaPlayer1!!.isLooping = true
        mediaPlayer1!!.start()
    }
    private fun stopAudio1(){
        if (mediaPlayer1?.isPlaying == true) {
            mediaPlayer1!!.stop()
            mediaPlayer1 = null
        }
    }
    private fun playAudio2(){
        if (media2 != null) {
            mediaPlayer2 = MediaPlayer.create(this, media2)
        }else{
            mediaPlayer2 = MediaPlayer.create(this, R.raw.bateria)
        }
        mediaPlayer2!!.isLooping = true
        mediaPlayer2!!.start()
    }
    private fun stopAudio2(){
        if (mediaPlayer2?.isPlaying == true) {
            mediaPlayer2!!.stop()
            mediaPlayer2 = null
        }
    }
    private fun playAudio3(){
        if (media3 != null) {
            mediaPlayer3 = MediaPlayer.create(this, media3)
        }else{
            mediaPlayer3 = MediaPlayer.create(this, R.raw.bajo)
        }
        mediaPlayer3!!.isLooping = true
        mediaPlayer3!!.start()
    }
    private fun stopAudio3(){
        if (mediaPlayer3?.isPlaying == true) {
            mediaPlayer3!!.stop()
            mediaPlayer3 = null
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == RESULT_OK) {
            media1 = data?.data // The URI with the location of the file
        }
        if (requestCode == 2 && resultCode == RESULT_OK) {
            media2 = data?.data // The URI with the location of the file
        }
        if (requestCode == 3 && resultCode == RESULT_OK) {
            media3 = data?.data // The URI with the location of the file
        }
    }

}


