# KoTello
A project written in Kotlin allowing taking control of Ryze Tello drone.


# Tracking visual signal
The library automatically sends `streamon` and `command` commands during connection. For video feed simply install `ffmpeg` and issue following command.

```
./ffmpeg -i udp://192.168.10.1:11111 -f sdl "Tello Video Feed"
```

Video has delay that is related to the speed of your wifi. Using USB Wifi dongle expect something in the neighbourhood of half second delay.
