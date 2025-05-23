package com.d4c;

import java.util.Scanner;

import java.util.Scanner;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ", 2);
            String cwd = parts[0];
            String path = parts[1];

            String simplifiedPath = simplifyPath(cwd, path);
            System.out.println(simplifiedPath);
        }

        scanner.close();
    }

    public static String simplifyPath(String cwd, String path) {
        // Handle cases where PATH starts with "/"
        if (path.startsWith("/")) {
            return simplifyAbsolutePath(path);
        }

        // Combine cwd and path and simplify
        String combinedPath = cwd + "/" + path;
        return simplifyAbsolutePath(combinedPath);
    }

    public static String simplifyAbsolutePath(String path) {
        String[] parts = path.split("/");
        StringBuilder result = new StringBuilder();

        for (String part : parts) {
            if (part.isEmpty() || part.equals(".")) {
                continue; // Skip empty parts or current directory markers
            } else if (part.equals("..")) {
                if (result.length() > 0) {
                    int lastIndex = result.lastIndexOf("/");
                    result.setLength(lastIndex); // Remove last component
                }
            } else {
                result.append("/").append(part); // Append valid directory
            }
        }

        if (result.length() == 0) {
            return "/"; // Edge case: return root directory
        } else {
            return result.toString();
        }
    }
}


