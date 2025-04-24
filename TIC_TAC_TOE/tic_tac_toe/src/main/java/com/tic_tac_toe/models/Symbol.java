package com.tic_tac_toe.models;

public class Symbol {
    private char symbolChar;

    private String avatarUrl;

    public Symbol(char symbolChar, String avatarUrl)
    {
        this.avatarUrl = avatarUrl;
        this.symbolChar = symbolChar;
    }
    public char getSymbolChar() {
        return symbolChar;
    }

    public void setSymbolChar(char symbolChar) {
        this.symbolChar = symbolChar;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
 
}
