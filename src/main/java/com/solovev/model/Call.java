package com.solovev.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Class represents a Call instance
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Call {
    @JsonIgnore
    private LocalDate dateFromFileName;
    @JsonProperty("№")
    private int Id;
    @JsonProperty("Дт/Вр звонка")
    private String dateString;
    @JsonProperty("Оператор")
    private String vendor;
    @JsonProperty("Номер абонента")
    private long number;
    @JsonProperty("Фрод")
    @JsonDeserialize(using = FraudBooleanDeSerializer.class)
    private Boolean isFraud;

    /**
     * Class to deserialize fraud value as boolean
     * field will be true if matches "FRAUD" otherwise;
     */
    static public class FraudBooleanDeSerializer extends JsonDeserializer<Boolean> {
        @Override
        public Boolean deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
            return jsonParser.getText().equals("FRAUD");
        }
    }

    /**
     * Class to serialize fraud value to the string
     * serializes filed as "FRAUD" if value is true "FALSE" otherwise
     */
    public class FraudBooleanSerializer extends JsonSerializer<Boolean> {
        @Override
        public void serialize(Boolean aBoolean, JsonGenerator jsonGenerator,
                              SerializerProvider serializerProvider)
                throws IOException, JsonProcessingException {

            if(aBoolean){
                jsonGenerator.writeString("FRAUD");
            } else {
                jsonGenerator.writeString("NO FRAUD");
            }
        }
    }

    public Call() {
    }

    public Call(LocalDate dateFromFileName, int id, String dateString, String vendor, long number, Boolean isFraud) {
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

    public Boolean getFraud() {
        return isFraud;
    }

    public void setFraud(Boolean fraud) {
        isFraud = fraud;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Call call = (Call) o;

        if (Id != call.Id) return false;
        if (number != call.number) return false;
        if (!Objects.equals(dateFromFileName, call.dateFromFileName))
            return false;
        if (!Objects.equals(dateString, call.dateString)) return false;
        if (!Objects.equals(vendor, call.vendor)) return false;
        return Objects.equals(isFraud, call.isFraud);
    }

    @Override
    public int hashCode() {
        int result = dateFromFileName != null ? dateFromFileName.hashCode() : 0;
        result = 31 * result + Id;
        result = 31 * result + (dateString != null ? dateString.hashCode() : 0);
        result = 31 * result + (vendor != null ? vendor.hashCode() : 0);
        result = 31 * result + (int) (number ^ (number >>> 32));
        result = 31 * result + (isFraud != null ? isFraud.hashCode() : 0);
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
