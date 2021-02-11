package com.jumia.exercise.domain;


public enum Country {

    CAMEROON("(237)", "\\(237\\)\\ ?[2368]\\d{7,8}$"),
    ETHIOPIA("(251)", "\\(251\\)\\ ?[1-59]\\d{8}$"),
    MOZAMBIQUE("(258)", "\\(258\\)\\ ?[28]\\d{7,8}$"),
    UGANDA("(256)", "\\(256\\)\\ ?\\d{9}$"),
    MOROCCO("(212)", "\\(212\\)\\ ?[5-9]\\d{8}$");

    private String code;
    private String regex;

    Country(String code, String regex) {
        this.code = code;
        this.regex = regex;
    }

    public String getCode() {
        return code;
    }

    public String getRegex() {
        return regex;
    }
}
