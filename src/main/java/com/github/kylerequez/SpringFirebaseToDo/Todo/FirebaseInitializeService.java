package com.github.kylerequez.SpringFirebaseToDo.Todo;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;

import static com.github.kylerequez.SpringFirebaseToDo.Configuration.ConfigurationStrings.DB_URL;
import static com.github.kylerequez.SpringFirebaseToDo.Configuration.ConfigurationStrings.FILE_STREAM;

@Service
public class FirebaseInitializeService {

    @PostConstruct
    public void initialize()
    {
        try {
            FileInputStream serviceAccount =
                    new FileInputStream(FILE_STREAM);

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl(DB_URL)
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
