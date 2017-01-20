package com.bugdeveloper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Nya on 20.01.2017.
 */
public class PostDownloader {

    private VKRequester vkr;
    private long pageId;

    private File quotes;
    private File pictures;

    private PrintWriter quotesWriter;
    private PrintWriter picturesWriter;
    int offset = 0;


    public PostDownloader(int pageId, String quotesFile, String picturesFile) throws IOException {
        this.pageId = pageId;
        vkr = new VKRequester();

        quotes = new File(quotesFile);
        pictures = new File(picturesFile);

        quotesWriter = new PrintWriter(new FileWriter(quotes, true));
        picturesWriter = new PrintWriter(new FileWriter(pictures, true));
    }

    public void downloadPosts() throws Exception {

        String[][] arr = JSONParser.getFields(vkr.sendGet("wall.get?owner_id=" + pageId + "&offset=" + offset + "&count=100"));

        while (!JSONParser.isEmpty()) {

            for (int i = 0; i < arr.length; i++) {
                if (!arr[i][0].equals("")) {
                    quotesWriter.println(arr[i][0]);
                } else {
                }
                if (arr[i][1] != null)
                    picturesWriter.println(arr[i][1]);
            }

            offset = offset + 100;
            arr = JSONParser.getFields(vkr.sendGet("wall.get?owner_id=" + pageId + "&offset=" + offset + "&count=100"));

            if (offset % 1000 == 0)
                System.out.println(offset + " posts downloaded.");
        }

        quotesWriter.close();
        picturesWriter.close();

        System.out.println("Done!" + offset + " posts downloaded.");
    }

}
