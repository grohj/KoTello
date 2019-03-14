# KoTello
A project written in Kotlin allowing taking control of Ryze Tello drone.

# Quick info
Firstly, if using this program will result in your drone smashing into wall, animal, person or nuclear reactor causing the next catastrophe of this century, bear in mind I don't take any responsibility for it.

If you decide to use this code and run it without any changes, here is how it works.

It expects you to be connected to the drone wifi before running. After execution blank white screen will show, this window captures your keystrokes, so keep focus on that one.

Use SPACE to lift off and escape to LAND. Press E after takeoff to control the drone using keyboard.

Layout: 
  - W and S control altitude
  - A and D control yaw
  - Arrow keys control forward, backward, left, right, just how you would expect

To close connection simply close the white window.

# Tello camera feed
The library automatically sends `streamon` and `command` commands during connection. For video feed simply install `ffmpeg` and issue following command.

```
./ffmpeg -i udp://192.168.10.1:11111 -f sdl "Tello Video Feed"
```

Video has delay that is related to the speed of your wifi. Using USB Wifi dongle expect something in the neighbourhood of half second delay.
