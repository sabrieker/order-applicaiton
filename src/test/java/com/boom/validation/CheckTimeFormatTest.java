package com.boom.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class CheckTimeFormatTest {

	private CheckTimeFormatValidator validator = new CheckTimeFormatValidator	();

    @Nested
    class NoWhitespaceValidFlow {
        @Test
        void isValid_shouldReturnTrue_whenNoWhitespaces() {
            assertTrue(isValid("08:00"));
            assertTrue(isValid("19:00"));
            assertTrue(isValid("20:00"));
        }
    }

    @Nested
    class NoWhitespacesInvalidFlow {
        @Test
        void isValid_shouldReturnFalse_whenAtLeastOneWhitespace() {
            assertFalse(isValid(" "));
            assertFalse(isValid("08.00"));
            assertFalse(isValid("05:00"));
            assertFalse(isValid("22:00"));
        }
    }

    private boolean isValid(String value) {
        return validator.isValid(value, null);
    }

}
