import java.awt.event.KeyEvent
import java.awt.event.KeyEvent.*
import java.awt.event.KeyListener
import kotlin.contracts.contract

class FlightKeyListener(val connection: TelloConnection) : KeyListener {


    val keysPressed = mutableSetOf<Int>()

    // Domain of this values is (-100, 100)
    val moveStep = 100
    val altStep = 50 //Don't put too much stress on these poor dudes
    val yawStep = 100

    var enabled = false

    override fun keyTyped(e: KeyEvent?) = Unit

    override fun keyPressed(e: KeyEvent) {
        keysPressed.add(e.keyCode)
        if (keysPressed.contains(VK_E)) {
            enabled = !enabled
            println("CONTROL ENABLED: $enabled")
        }
        if (keysPressed.contains(VK_SPACE)) {
            connection.send("takeoff")
        }

        if (keysPressed.contains(VK_ESCAPE)) {
            connection.send("land")
        }
    }

    override fun keyReleased(e: KeyEvent) {
        keysPressed.remove(e.keyCode)
    }

    fun getCommand(): RCCommand {
        var leftRight = 0
        var forwardBackward = 0
        var upDown = 0
        var yaw = 0

        with(keysPressed)
        {
            if (contains(VK_UP)) forwardBackward += moveStep
            if (contains(VK_DOWN)) forwardBackward -= moveStep
            if (contains(VK_LEFT)) leftRight -= moveStep
            if (contains(VK_RIGHT)) leftRight += moveStep

            if (contains(VK_W)) upDown += altStep
            if (contains(VK_S)) upDown -= altStep

            if (contains(VK_D)) yaw += yawStep
            if (contains(VK_A)) yaw -= yawStep
        }

        return RCCommand(leftRight, forwardBackward, upDown, yaw)
    }
}