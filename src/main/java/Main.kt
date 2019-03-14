import com.sun.jna.NativeLibrary
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import javax.swing.JFrame
import java.awt.SystemColor.text
import javafx.scene.input.KeyCode.getKeyCode
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent
import java.io.BufferedReader
import java.util.*


val step = 40

var lr = 0
var fb = 0
var ud = 0
var yaw = 0

fun main(args: Array<String>) {
    NativeLibrary.addSearchPath("libvlc", "C:/Software/VideoLAN/VLC/")
    //MediaPlayerFactory()
    val tello = TelloConnection()
    tello.connect()

    val keyListener = FlightKeyListener(tello)


    JFrame().apply {

        //contentPane = EmbeddedMediaPlayerComponent()
        setSize(800,600)
        isVisible = true
        addKeyListener(keyListener)
    }




    val thread = Thread{
        while(true) {
            Thread.sleep(100)
            if (keyListener.enabled) {
                val command = keyListener.getCommand().toString()
                tello.send(command)
                println("Send: $command")
            }

        }
    }
    thread.start()
    thread.join()







}


