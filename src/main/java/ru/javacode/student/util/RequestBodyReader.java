package ru.javacode.student.util;

import java.io.BufferedReader;
import java.io.IOException;

public final class RequestBodyReader {

    public static String getBody(BufferedReader reader) throws IOException {
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line).append(System.lineSeparator());
        }
        return builder.toString();
    }
}
