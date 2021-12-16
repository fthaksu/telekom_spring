package com.works.restcontrollers;

import com.google.gson.Gson;
import com.works.entities.Note;
import com.works.props.NewsData;
import com.works.props.PostData;
import com.works.utils.REnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class NewsRestController {

    @GetMapping("/newsData")
    public Map<REnum, Object> newsData( ) {
        Map<REnum, Object> hm = new LinkedHashMap<>();

        String url = "https://newsapi.org/v2/top-headlines?country=tr&category=business&apiKey=38a9e086f10b445faabb4461c4aa71f8";
        RestTemplate template = new RestTemplate();
        String stData = template.getForObject(url, String.class);

        Gson gson = new Gson();
        NewsData data = gson.fromJson(stData, NewsData.class);
        /*
        data.getArticles().forEach( item -> {
            System.out.println( item.getTitle() );
        } );
         */

        String urlPost = "https://jsonplaceholder.typicode.com/posts/"+data.getTotalResults();
        String postSt = template.getForObject(urlPost, String.class);
        PostData postData = gson.fromJson(postSt, PostData.class);

        hm.put(REnum.message, postData);
        hm.put(REnum.result, data.getArticles());


        return hm;
    }


}
