package hac.poll;

import hac.exceptions.AuthenticateException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PollSingleton {

    private static PollSingleton instance = null;
    private static PollManager pollManager;

    public PollSingleton() {
    }

    public final PollSingleton getInstance(String fileName) throws IOException, AuthenticateException {
        try {
            if (instance == null) {
                instance = new PollSingleton();
                BufferedReader file = new BufferedReader(new FileReader(fileName));
                String line;
                StringBuilder pollText = new StringBuilder();
                while ((line = file.readLine()) != null) {
                    if (!line.equals("")) {
                        pollText.append(line).append('\n');
                    }
                }
                pollManager = new PollManager(pollText.toString().split("\n"));

            }
        } catch (IOException | AuthenticateException e) {
            instance = null;
            throw e;
        }
        return instance;
    }

        public PollManager getPollManager () {
            return pollManager;
        }
    }
