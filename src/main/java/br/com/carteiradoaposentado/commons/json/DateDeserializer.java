package br.com.carteiradoaposentado.commons.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateDeserializer extends StdDeserializer<LocalDate> {

    private static final long serialVersionUID = 7465296789854669077L;

    public DateDeserializer() {
        this((Class) null);
    }

    private DateDeserializer(final Class<LocalDate> vc) {
        super(vc);
    }

    public LocalDate deserialize(final JsonParser jsonParser, final DeserializationContext ctxt) throws IOException {
        return jsonParser != null && StringUtils.isNotBlank(jsonParser.getText()) ? LocalDate.parse(jsonParser.getText(), DateTimeFormatter.ISO_DATE) : null;
    }
}
