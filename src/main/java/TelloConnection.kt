import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

class TelloConnection  {


    val socket : DatagramSocket = DatagramSocket(TELLO_PORT)


    fun connect() {
        if(!socket.isConnected)
            socket.connect(TELLO_INET_ADDR, TELLO_PORT)
        send("command")
        send("streamon")
        println("connected")
    }
    fun disconnect() {
        if(socket.isConnected)
            socket.disconnect()
    }


    fun send(data : String)
    {
        if(socket.isConnected.not() || data.isBlank())
            return
        val bytes = data.toByteArray()
        val packet = DatagramPacket(bytes, bytes.size, TELLO_INET_ADDR, TELLO_PORT)
        socket.send(packet)
    }

    fun receive() : String {
        val target = ByteArray(1024)
        val receivePacket = DatagramPacket(target, target.size)
        socket.receive(receivePacket)
        return String(receivePacket.data)
    }





    companion object {
        const val TELLO_IP = "192.168.10.1"
        val TELLO_INET_ADDR = InetAddress.getByName(TELLO_IP)!!
        const val TELLO_PORT = 8889
    }
}