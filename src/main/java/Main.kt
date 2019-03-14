import javax.swing.JFrame




fun main(args: Array<String>) {
    val tello = TelloConnection()
    tello.connect()
    val keyListener = FlightKeyListener(tello)

    JFrame().apply {
        setSize(800, 600)
        isVisible = true
        addKeyListener(keyListener)
        title = "This window catches key strokes, keep it in focus."
    }


    val thread = Thread {

        var lastCmd = ""

        while (true) {
            Thread.sleep(15)
            if (keyListener.enabled) {

                val command = keyListener.getCommand().toString()
                if (command != lastCmd) {
                    tello.send(command)
                    println("Send: $command")
                    lastCmd = command
                }

            }

        }
    }
    thread.start()
    thread.join()


}





