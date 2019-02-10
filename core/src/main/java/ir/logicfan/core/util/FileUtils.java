package ir.logicfan.core.util;

import android.content.Context;

import javax.inject.Singleton;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Singleton
public class FileUtils {

    public static String readTextFileFromAssets(Context context, String path) throws IOException {
        InputStream inputStream = context.getResources().getAssets().open(path);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder jsonString = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            jsonString.append(line);
        }
        inputStream.close();
        bufferedReader.close();
        return jsonString.toString();
    }
}
