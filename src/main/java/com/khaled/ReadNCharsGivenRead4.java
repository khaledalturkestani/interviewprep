package com.khaled;

/**
 * The API: int read4(char *buf) reads 4 characters at a time from a file.
 * The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.
 * By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
 * Note:
 * The read function may be called multiple times.
 *
 * https://leetcode.com/problems/read-n-characters-given-read4-ii-call-multiple-times/description/
 */
public class ReadNCharsGivenRead4 {

    public static class Reader4 {
        private String str;
        private int indx = 0;
        public Reader4(String str) {
            this.str = str;
        }
        public int read4(char[] buf) {
            int charsRead = 0;
            for (int i = 0; i < 4; i++) {
                if (indx+i < str.length()) {
                    buf[i] = str.charAt(indx+i);
                    charsRead++;
                }
            }
            indx += charsRead;
            return charsRead;
        }
    }

    public static class Reader extends Reader4 {

        // Holds any additional characters that were read before
        // but didn't fit in the buffer because max number of characters
        // to read was reached.

        private char[] remainderArr = new char[4];
        private int remainder = 0;

        public Reader(String str) {
            super(str);
        }

        public int read(char[] buf, int n) {
            if (n == 0)
                return 0;
            int charsRead = 0;
            int bufIndx = 0;
            int totalRead = 0;
            if (n <= remainder) {
                for (int i = 0; i < remainder; i++)
                    buf[i] = remainderArr[i];
                remainder -= n;
                for (int i = 0; i < remainder; i++)
                    remainderArr[i] = remainderArr[n+i];
                return n;
            } else if (remainder > 0) {
                charsRead = remainder;
                totalRead = remainder;
                int i;
                for (i = 0; i < remainder; i++)
                    buf[i] = remainderArr[i];
                bufIndx = i;
                remainder = 0;
            }

            char[] minBuf = new char[4];
            do {
                int len = computeMinLen(n, totalRead, buf.length, bufIndx);
                charsRead = read4(minBuf);
                if (charsRead > len) {
                    for (int i = 0; i < len; i++)
                        buf[bufIndx++] = minBuf[i];
                    for (int i = 0; i < charsRead-len; i++)
                        remainderArr[i] = minBuf[len+i];
                    remainder = charsRead - len;
                    totalRead += len;
                } else {
                    for (int i = 0; i < charsRead; i++)
                        buf[bufIndx++] = minBuf[i];
                    totalRead += charsRead;
                }
            } while (totalRead < n && charsRead > 0 && bufIndx < buf.length);
            return totalRead;
        }

        /**
         * Determine max characters to read depending on space left on the char buffer
         * and the user-requested max number of characters to read.
         *
         * @param n
         * @param totalRead
         * @param bufLen
         * @param bufIndx
         * @return
         */
        private int computeMinLen(int n, int totalRead, int bufLen, int bufIndx) {
            if (bufLen - bufIndx < 4 && n-totalRead < 4) {
                return (bufLen-bufIndx < n-totalRead) ? bufLen-bufIndx : n-totalRead;
            } else if (bufLen - bufIndx < 4) {
                return bufLen-bufIndx;
            } else if (n-totalRead < 4) {
                return n-totalRead;
            }
            return 4;
        }

    }

    public static void main(String[] args) {
        String file = "ab";
        Reader reader = new Reader(file);
        char[] buf = new char[10];
        int read = reader.read(buf, 1);
        for (int i = 0; i < read; i++)
            System.out.print(buf[i]);
        System.out.println();
        read = reader.read(buf, 2);
        for (int i = 0; i < read; i++)
            System.out.print(buf[i]);

    }
}
