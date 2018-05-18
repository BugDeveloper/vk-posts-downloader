# vk-posts-downloader
Simple parser and downloader for VK's profile/public wall content. Only text and image links for now. Easy to extend if you need something else.

## Usage

```
int somePageId = 1;
String quotesFile = "textContent.txt";
String picturesFile = "pictureContent.txt";
PostDownloader pd = new PostDownloader(somePageId, quotesFile, picturesFile);
pd.downloadPosts();
```

## Variables

**somePageId** - id of the page you want to parse.

**quotesFile** - full filename in string where text content of the page will be saved.

**picturesFile** - full filename in string where links to the images will be saved.
