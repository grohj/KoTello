import javax.swing.JFrame




fun main(args: Array<String>) {
    val tello = TelloConnection()
    tello.connect()
    val keyListener = FlightKeyListener(tello)

    JFrame().apply {
        setSize(800,600)
        isVisible = true
        addKeyListener(keyListener)
        title = "This window catches key strokes, keep it in focus."
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


