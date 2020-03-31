package org.basecampcodingacademy.reflections.exception;

import java.time.LocalDate;

class DateErrorMessageReflection extends Exception {
    public LocalDate date;
    public DateErrorMessageReflection(LocalDate date) {
        this.date = date;
    }
}