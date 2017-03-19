package ru.doktorov.controller;

import com.pi4j.io.gpio.*;
import java.util.BitSet;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.doktorov.rf.RCSwitch;

@RestController
public class LedController {
    private static GpioPinDigitalOutput mPin01;
    private static GpioPinDigitalOutput mPin07;

    @RequestMapping("/light")
    public String light() {
        if (mPin01 == null) {
            GpioController gpio = GpioFactory.getInstance();
            mPin01 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "MyLED", PinState.LOW);
        }

        mPin01.toggle();

        return "OK";
    }

    @RequestMapping("/switchOn")
    public String switchOn() {
        GpioController gpio = GpioFactory.getInstance();

        if (mPin07 == null) {
            mPin07 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_07, "MyLED", PinState.LOW);
        }

        BitSet address = RCSwitch.getSwitchGroupAddress("11111");

        RCSwitch transmitter = new RCSwitch(mPin07);
        transmitter.switchOn(address, 3);

        gpio.shutdown();

        return "switchOn";
    }

    @RequestMapping("/switchOff")
    public String switchOff() {
        GpioController gpio = GpioFactory.getInstance();

        if (mPin07 == null) {
            mPin07 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_07, "MyLED", PinState.LOW);
        }

        BitSet address = RCSwitch.getSwitchGroupAddress("11111");

        RCSwitch transmitter = new RCSwitch(mPin07);
        transmitter.switchOff(address, 3);

        gpio.shutdown();

        return "switchOff";
    }
}