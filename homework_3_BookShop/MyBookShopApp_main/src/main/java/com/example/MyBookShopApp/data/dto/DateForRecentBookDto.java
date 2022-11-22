package com.example.MyBookShopApp.data.dto;

import java.time.LocalDate;
import java.util.Date;

public class DateForRecentBookDto {

    private LocalDate from;
    private LocalDate to;

    public DateForRecentBookDto(LocalDate from, LocalDate to) {
        this.from = from;
        this.to = to;
    }

    public DateForRecentBookDto() {
    }

    public LocalDate getFrom() {
        return from;
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public LocalDate getTo() {
        return to;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }
}
