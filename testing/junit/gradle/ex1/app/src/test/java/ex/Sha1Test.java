package ex;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Sha1Test
{
    @Test void message_digest()
    {
        final String[] strings = {
            "Secure Hash Algorithm 1",
            "a cryptographic hash function"
        };

        var actual = Arrays.stream(strings)
        .map(Sha1::hex_string)
        .collect(Collectors.toList())
        ;

        assertEquals(
            "3f6a5801706dc3541933a270f0048cd3ec26efa1",
            actual.get(0)
        );
        assertEquals(
            "8a5aee2ab7be7d81f4e3f65d5f34b40d4dcc50d1",
            actual.get(1)
        );
    }    
}
