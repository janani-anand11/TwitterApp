
function TweetInfo(responseData){
    timelineDiv = document.getElementById('tweet-container');
    timelineDiv.classList.add("timelineDiv");
    // to clear the div block
    while(timelineDiv.firstChild){
        timelineDiv.removeChild(timelineDiv.firstChild);
    }
    timelineDiv.innerHTML = "<div";
    for (let index = 0; index < responseData.length; index++) {
        let tweetInfo = document.createElement('div')
        tweetInfo.classList.add('block-' + index%2,'blocks');
        let text = "<div class='right'><span class='dp'> <img class='profile' src='" + responseData[index].profileImageUrl + "'></span>";
        text+="<span class='uname'>" + responseData[index].userName + "</span>";
        text+="<span class='handle'>" + responseData[index].twitterHandle + "</span></div>";
        text+=" <div class='left'> <span class='date'>" + responseData[index].createdAt + "</span>";
        text+="<span class='status'><a href='" + responseData[index].statusUrl + "' target='_blank' rel='noopener noreferrer'>" + responseData[index].message + "</a></span></div>";
        tweetInfo.innerHTML = text;
        document.getElementById("tweet-container").appendChild(tweetInfo); 
    }
}

function viewTimeline(){
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200){
        TweetInfo(JSON.parse(this.responseText));
        }
    };
    xhr.open('GET', 'http://localhost:8080/api/1.0/twitter/timeline');
    xhr.send();
}
