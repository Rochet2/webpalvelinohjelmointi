var url = contextRoot + "books/random"

// Implement the functionality to retrieve a random book here

var http = new XMLHttpRequest()

http.onreadystatechange = function () {
    if (this.readyState != 4 || this.status != 200) {
        return
    }

    response = JSON.parse(this.responseText)
    console.log(response)
    document.getElementById("author").innerHTML = response.author
    document.getElementById("pages").innerHTML = response.pages
    document.getElementById("title").innerHTML = response.title
}

function getRandomBook() {
    http.open("GET", url)
    http.send()
}