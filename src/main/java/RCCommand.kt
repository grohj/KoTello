data class RCCommand(val leftRight : Int, val forwardBackward : Int, val upDown : Int, val yaw : Int )
{
    override fun toString(): String {
        return "rc $leftRight $forwardBackward $upDown $yaw"
    }
}