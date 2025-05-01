package com.example.DATK_backend.service;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
@Component
public class Otp {
    public static class OtpInfo {
        String Otp;
        LocalDateTime expiryTime;
        public OtpInfo(String Otp, LocalDateTime expiryTime) {
            this.Otp = Otp;
            this.expiryTime = expiryTime;
        }
    }
    private final Map<String, OtpInfo> otpStorage = new HashMap<>();
    public String generateOtp () {
        String otp = String.format("%06d", new Random().nextInt(999999));
        LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(1);
        otpStorage.put(otp, new OtpInfo(otp, expiryTime));
        return otp;
    }
    public boolean validateOtp (String otp) {
        OtpInfo otpInfo = otpStorage.get(otp);
        if (otpInfo == null) {
            return false;
        }
        else if (LocalDateTime.now().isAfter(otpInfo.expiryTime)) {
            otpStorage.remove(otp);
            return false;
        }
        boolean isValid = otpInfo.Otp.equals(otp);
        if (isValid) {
            otpStorage.remove(otp);
        }
        return isValid;
    }
}