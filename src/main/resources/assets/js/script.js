
function viewTimeline(){
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200){
            document.getElementById('getTimeline').innerHTML = xhr.responseText;
        }
    };
    xhr.open('GET', 'http://localhost:8080/api/1.0/twitter/timeline');
    xhr.send();
}
