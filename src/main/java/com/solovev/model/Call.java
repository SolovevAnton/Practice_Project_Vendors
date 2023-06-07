package com.solovev.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Class represents a Call instance
 */
public class Call {
    private LocalDate dateFromFileName;
    private int Id;
    private String dateString;
    private String vendor;
    private long number;
    private boolean isFraud;

    public Call() {
    }

    public Call(LocalDate dateFromFileName, int id, String dateString, String vendor, long number, boolean isFraud) {
        this.dateFromFileName = dateFromFileName;
        Id = id;
        this.dateString = dateString;
        this.vendor = vendor;
        this.number = number;
        this.isFraud = isFraud;
    }

    public LocalDate getDateFromFileName() {
        return dateFromFileName;
    }

    public void setDateFromFileName(LocalDate dateFromFileName) {
        this.dateFromFileName = dateFromFileName;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public boolean getIsFraud() {
        return isFraud;
    }

    public void setFraud(boolean fraud) {
        isFraud = fraud;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Call call = (Call) o;

        if (Id != call.Id) return false;
        if (number != call.number) return false;
        if (isFraud != call.isFraud) return false;
        if (!Objects.equals(dateFromFileName, call.dateFromFileName))
            return false;
        if (!Objects.equals(dateString, call.dateString)) return false;
        return Objects.equals(vendor, call.vendor);
    }

    @Override
    public int hashCode() {
        int result = dateFromFileName != null ? dateFromFileName.hashCode() : 0;
        result = 31 * result + Id;
        result = 31 * result + (dateString != null ? dateString.hashCode() : 0);
        result = 31 * result + (vendor != null ? vendor.hashCode() : 0);
        result = 31 * result + (int) (number ^ (number >>> 32));
        result = 31 * result + (isFraud ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Call{" +
                "dateFromFileName=" + dateFromFileName +
                ", Id=" + Id +
                ", dateString='" + dateString + '\'' +
                ", vendor='" + vendor + '\'' +
                ", number=" + number +
                ", isFraud=" + isFraud +
                '}';
    }
}
