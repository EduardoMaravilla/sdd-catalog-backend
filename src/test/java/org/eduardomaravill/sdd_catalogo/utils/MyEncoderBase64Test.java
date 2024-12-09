package org.eduardomaravill.sdd_catalogo.utils;

import org.junit.jupiter.api.Test;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

class MyEncoderBase64Test {

    @Test
    void testEncoderDoubleToBase64_ValidDouble() {
        Double input = 123.45;
        String encoded = MyEncoderBase64.encoderDoubleToBase64(input);

        String expected = Base64.getEncoder().encodeToString("123.45".getBytes());
        assertEquals(expected, encoded, "El valor codificado no coincide con el esperado.");
    }

    @Test
    void testEncoderDoubleToBase64_NegativeDouble() {
        Double input = -56.78;
        String encoded = MyEncoderBase64.encoderDoubleToBase64(input);

        String expected = Base64.getEncoder().encodeToString("-56.78".getBytes());
        assertEquals(expected, encoded, "El valor codificado no coincide con el esperado.");
    }

    @Test
    void testEncoderDoubleToBase64_NullValue() {
        String encoded = MyEncoderBase64.encoderDoubleToBase64(null);
        assertEquals("MC4w", encoded, "El valor codificado para null debería ser 'MC4w'.");
    }

    @Test
    void testDecodeBase64ToDouble_ValidBase64() {
        String input = Base64.getEncoder().encodeToString("123.45".getBytes());
        Double decoded = MyEncoderBase64.decodeBase64ToDouble(input);

        assertEquals(123.45, decoded, 0.0001, "El valor decodificado no coincide con el esperado.");
    }

    @Test
    void testDecodeBase64ToDouble_EmptyString() {
        String input = "";
        Double decoded = MyEncoderBase64.decodeBase64ToDouble(input);

        assertEquals(0.0, decoded, "El valor decodificado para una cadena vacía debería ser 0.0.");
    }

    @Test
    void testDecodeBase64ToDouble_BlankString() {
        String input = "   "; // Cadena de espacios en blanco
        Double decoded = MyEncoderBase64.decodeBase64ToDouble(input);

        assertEquals(0.0, decoded, "El valor decodificado para una cadena en blanco debería ser 0.0.");
    }

    @Test
    void testDecodeBase64ToDouble_InvalidBase64() {
        String input = "invalidBase64";

        assertThrows(IllegalArgumentException.class, () -> {
            MyEncoderBase64.decodeBase64ToDouble(input);
        }, "Se esperaba una IllegalArgumentException para una cadena Base64 no válida.");
    }

}