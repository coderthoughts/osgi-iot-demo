package client;

import org.coderthoughts.iotdemo.BlinkingDevice;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

@Component(immediate=true)
public class BlinkingDeviceClient implements Runnable {
    private volatile boolean keepRunning = true;

    @Reference
    private BlinkingDevice blinkDevice;

    @Activate
    public void activate() {
        new Thread(this).start();
    }

    @Deactivate
    public void deactivate() {
        keepRunning = false;
    }

    @Override
    public void run() {
        while (keepRunning) {
            blinkDevice.blinkGreenLight();
            try { Thread.sleep(500); } catch (Exception ex) {}
        }
    }
}
