# site-crawler
- Create a project
    - Java 선택 -> Next
    - Create project form template 에 Command Line App 체크 -> Next
    - Project name : site-crawler
    - Maven 프로젝트로 변경 : site-crawler 오른쪽 마우스 클릭 -> Add Framework Support... -> Maven 체크 -> OK
- site-crawler 동작
```
>> Enter the URL of the website you want to crawl :  
>> http://www.imagegallery.nyc/exhitions/
>> Connecting to http://www.imagegallery.nyc/exhitions/ ...
>> Success to Connecting or Fail to connecting  
>> Parsing to response body ...
>> Crawling images ...
>> file name 00% [#########           ] 3123/121233 
>> file name 00% [#########           ] 3123/121233
>> file name 00% [#########           ] 3123/121233
>> file name 00% [#########           ] 3123/121233
>> Done
>> ###################################################
```   

- classes
    - CrawlerDownloader
    - CrawlerDomParser
    - CrawlerConsoleBar 
    - CrwalerHttp
        ```
        XXX.post("http://httpbin.org/post")
          .header("accept", "application/json")
          .queryString("apiKey", "123")
          .field("parameter", "value")
          .field("foo", "bar")
        
        ```    
    


     

