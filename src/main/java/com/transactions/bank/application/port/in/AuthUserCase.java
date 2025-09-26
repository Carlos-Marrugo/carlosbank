package com.transactions.bank.application.port.in;

public interface AuthUserCase {
    String register(String username, String password);
    String authenticate(String username, String password);
}
