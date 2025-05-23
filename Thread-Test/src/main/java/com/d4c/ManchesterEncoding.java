package com.d4c;

public class ManchesterEncoding {

    public static String encode(String input) {
        String encodedString = "";


        // Start with the initial "10" for Manchester encoding
        encodedString += "10";

        // Loop through the input in chunks of 8 bits
        for (int i = 0; i < input.length(); i += 8) {
            String chunkEncoded = "";

            // Encode each 8-bit chunk
            for (int j = 0; j < 8; j++) {
                char bit = (i + j < input.length()) ? input.charAt(i + j) : '0'; // Assume '0' if out of range

                if (bit == '0') {
                    // For '0', encode as "10"
                    chunkEncoded += "10";
                } else {
                    // For '1', encode as "01"
                    chunkEncoded += "01";
                }
            }

            // Append the encoded chunk to the output string
            encodedString += chunkEncoded;
        }

        // End with "00" for Manchester encoding
        encodedString += "00";

        return encodedString;
    }

    public static void main(String[] args) {
        // Example usage
        String input = "01010101"; // Example 8-bit binary string
        String encoded = encode(input);
        System.out.println("Encoded Manchester code: " + encoded);
    }
}
