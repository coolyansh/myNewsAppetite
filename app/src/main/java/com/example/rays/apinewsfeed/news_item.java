package com.example.rays.apinewsfeed;


public class news_item {
    String image_url,site_url,title,description;
    news_item(String img,String site,String ttl,String dscp){
        image_url=img;
        site_url=site;
        title=ttl;
        description=dscp;
    }

    String getImage_url(){
        return image_url;
    }

    String getSite_url(){
        return site_url;
    }

    String getTitle(){
        return title;
    }

    String getDescription(){
        return description;
    }
}
