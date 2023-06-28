package com.example.db;

import dev.samstevens.totp.exceptions.QrGenerationException;

public interface MfaTokenManager {
    String generateSecretKey();
    String getQRCode(final String secret) throws QrGenerationException;
    boolean verifyTotp(final String code, final String secret);
}